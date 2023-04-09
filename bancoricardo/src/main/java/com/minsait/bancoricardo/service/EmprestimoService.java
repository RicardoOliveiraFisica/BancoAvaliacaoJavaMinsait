package com.minsait.bancoricardo.service;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.minsait.bancoricardo.dto.EmprestimoDTO;
import com.minsait.bancoricardo.entity.Cliente;
import com.minsait.bancoricardo.entity.Emprestimo;
import com.minsait.bancoricardo.exception.ClienteNaoEncontradoException;
import com.minsait.bancoricardo.exception.EmprestimoNaoEncontradoException;
import com.minsait.bancoricardo.exception.ExcedidoValorLimiteEmprestimosException;
import com.minsait.bancoricardo.repository.ClienteRepository;
import com.minsait.bancoricardo.repository.EmprestimoRepository;

@Service
public class EmprestimoService {
	private EmprestimoRepository emprestimoRepository;
	private ClienteRepository clienteRepository;
	
	@Autowired
	public EmprestimoService(EmprestimoRepository emprestimoRepository, ClienteRepository clienteRepository) {
		this.emprestimoRepository = emprestimoRepository;
		this.clienteRepository = clienteRepository;
	}

	
	public EmprestimoDTO cadastrarEmprestimo(String cpfCliente, EmprestimoDTO emprestimoDTO) throws ClienteNaoEncontradoException, ExcedidoValorLimiteEmprestimosException {
		if (this.clienteRepository.existsByCpf(cpfCliente)) {
			Cliente cliente = this.clienteRepository.findByCpf(cpfCliente).get();
			Emprestimo emprestimo = EmprestimoDTO.retornaEmprestimo(emprestimoDTO, cliente);
			List<Emprestimo> emprestimos = cliente.getEmprestimos();
			BigDecimal valorTotal = emprestimo.getValorInicial();
			for (Emprestimo emprestimoAnterior : emprestimos) {
				valorTotal = valorTotal.add(emprestimoAnterior.getValorInicial());
			}
			if (valorTotal.compareTo(cliente.getRendimentoMensal().multiply(new BigDecimal("10.0"))) != 1) {
				this.emprestimoRepository.save(emprestimo);
				EmprestimoDTO emprestimoDTOcadastrado = EmprestimoDTO.retornaEmprestimo(emprestimo);
				return emprestimoDTOcadastrado;
			}
			throw new ExcedidoValorLimiteEmprestimosException(cpfCliente);
		}
		throw new ClienteNaoEncontradoException(cpfCliente);
	}
	
	
	public MensagemDeSucesso deletarEmprestimo(String cpf, Long id) throws ClienteNaoEncontradoException, EmprestimoNaoEncontradoException {
		if (this.clienteRepository.existsByCpf(cpf)) {
			if (this.emprestimoRepository.existsById(id)) {
				Emprestimo emprestimo = this.emprestimoRepository.findById(id).get();
				if (cpf.compareTo(emprestimo.getCliente().getCpf()) == 0 ) {
				this.emprestimoRepository.deleteById(id);
					MensagemDeSucesso mensagem = new MensagemDeSucesso("Emprestimo deletado com sucesso");				
					return mensagem;
				}
				throw new EmprestimoNaoEncontradoException(id, cpf);
			}
			throw new EmprestimoNaoEncontradoException(id);
		}
		throw new ClienteNaoEncontradoException(cpf);
	}
	
	public EmprestimoDTO retornarEmprestimo(String cpf, Long id) throws EmprestimoNaoEncontradoException, ClienteNaoEncontradoException {
		if (this.clienteRepository.existsByCpf(cpf)) {
			if (this.emprestimoRepository.existsById(id)) {
				Emprestimo emprestimo = this.emprestimoRepository.findById(id).get();
				if (cpf.compareTo(emprestimo.getCliente().getCpf()) == 0 ) {
					EmprestimoDTO emprestimoDTO = EmprestimoDTO.retornaEmprestimo(emprestimo);
					return emprestimoDTO;
				}
				throw new EmprestimoNaoEncontradoException(id, cpf);
			}
			throw new EmprestimoNaoEncontradoException(id);
		}
		throw new ClienteNaoEncontradoException(cpf);
	}
	
	public EmprestimoRepository getEmprestimoRepository() {
		return emprestimoRepository;
	}

	public void setEmprestimoRepository(EmprestimoRepository emprestimoRepository) {
		this.emprestimoRepository = emprestimoRepository;
	}

	public ClienteRepository getClienteRepository() {
		return clienteRepository;
	}


	public void setClienteRepository(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}	
	
}
