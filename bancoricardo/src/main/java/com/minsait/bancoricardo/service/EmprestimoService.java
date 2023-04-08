package com.minsait.bancoricardo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minsait.bancoricardo.dto.EmprestimoDTO;
import com.minsait.bancoricardo.entity.Cliente;
import com.minsait.bancoricardo.entity.Emprestimo;
import com.minsait.bancoricardo.exception.ClienteNaoEncontradoException;
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

	
	public EmprestimoDTO cadastrarEmprestimo(String cpfCliente, EmprestimoDTO emprestimoDTO) throws ClienteNaoEncontradoException {
		if (this.clienteRepository.existsByCpf(cpfCliente)) {
			Cliente cliente = this.clienteRepository.findByCpf(cpfCliente).get();
			Emprestimo emprestimo = EmprestimoDTO.retornaEmprestimo(emprestimoDTO, cliente);
			this.emprestimoRepository.save(emprestimo);
			EmprestimoDTO emprestimoDTOcadastrado = EmprestimoDTO.retornaEmprestimo(emprestimo);
			//emprestimo.setCpfCliente(cliente);
			//return this.emprestimoRepository.save(emprestimo);
			//emprestimoDTO.setCpfCliente(cliente.getCpf());
			return emprestimoDTOcadastrado;
		}
		throw new ClienteNaoEncontradoException(cpfCliente);
		//emprestimoDTO.setCpfCliente(cpfCliente);
	//	return emprestimoDTO;
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
