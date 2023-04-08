package com.minsait.bancoricardo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minsait.bancoricardo.dto.ClienteDTO;
import com.minsait.bancoricardo.entity.Cliente;
import com.minsait.bancoricardo.exception.ClienteNaoEncontradoException;
import com.minsait.bancoricardo.exception.CpfJaCadastradoException;
import com.minsait.bancoricardo.repository.ClienteRepository;

@Service
public class ClienteService {
	
	private ClienteRepository clienteRepository;
	
	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	public ClienteDTO cadastrarCliente(ClienteDTO clienteDTO) throws CpfJaCadastradoException {
		if (this.clienteRepository.existsByCpf(clienteDTO.getCpf()) == false) {
			Cliente clienteParaCadastrar = ClienteDTO.retornaCliente(clienteDTO);
			Cliente clienteCadastrado = this.clienteRepository.save(clienteParaCadastrar);
			ClienteDTO clienteDTOCadastrado = ClienteDTO.retornaCliente(clienteCadastrado);
			return clienteDTOCadastrado;
		}
		
		throw new CpfJaCadastradoException(clienteDTO.getCpf());
	}
	
	public List<ClienteDTO> retornarTodosOsClientes() {
		List<Cliente> clientes = this.clienteRepository.findAll();
		List<ClienteDTO> clientesDTO = new ArrayList<>();
		for (Cliente cliente : clientes) {
			ClienteDTO clienteDTO = ClienteDTO.retornaCliente(cliente);
			clientesDTO.add(clienteDTO);
		}
		return clientesDTO;
	}
	
	public ClienteDTO retornarCliente(String cpf) throws ClienteNaoEncontradoException {
		if (this.clienteRepository.existsByCpf(cpf)) {
			Cliente cliente = this.clienteRepository.findByCpf(cpf).get();
			ClienteDTO clienteDTO = ClienteDTO.retornaCliente(cliente);
			return clienteDTO;
		}
		throw new ClienteNaoEncontradoException(cpf);
	}	

	@Transactional
	public MensagemDeSucesso deletarCliente(String cpf) throws ClienteNaoEncontradoException {
		if (this.clienteRepository.existsByCpf(cpf)) {
			this.clienteRepository.deleteByCpf(cpf);
			MensagemDeSucesso mensagem = new MensagemDeSucesso("Deletado com sucesso");
			return mensagem;
		}		
		throw new ClienteNaoEncontradoException(cpf);
	}
	
	public ClienteDTO atualizarCliente(String cpf, ClienteDTO clienteDTO) throws CpfJaCadastradoException, ClienteNaoEncontradoException {
		if (this.clienteRepository.existsByCpf(cpf)) {
			String cpfAtualizado = clienteDTO.getCpf();
			Cliente cliente = this.clienteRepository.findByCpf(cpf).get();
			if (cpfAtualizado == null || cpfAtualizado.equals("")) {
				cpfAtualizado = cliente.getCpf();
				clienteDTO.setCpf(cpfAtualizado);
			}			
			if (cpfAtualizado.equals(cpf) || !this.clienteRepository.existsByCpf(cpfAtualizado)) {						
				if (clienteDTO.getNome() == null) {
					clienteDTO.setNome(cliente.getNome());
				}
				if (clienteDTO.getTelefone() == null) {
					clienteDTO.setTelefone(cliente.getTelefone());
				}
				if (clienteDTO.getRua() == null) {
					clienteDTO.setRua(cliente.getEndereco().getRua());
				}
				if (clienteDTO.getNumero() == null) {
					clienteDTO.setNumero(cliente.getEndereco().getNumero());
				}

				if (clienteDTO.getCep() == null) {
					clienteDTO.setCep(cliente.getEndereco().getCep());
				}
				if (clienteDTO.getRendimentoMensal() == null) {
					clienteDTO.setRendimentoMensal(cliente.getRendimentoMensal());
				}
				Cliente clienteParaAtualizar = ClienteDTO.retornaCliente(clienteDTO);
				clienteParaAtualizar.setId(cliente.getId());
				clienteParaAtualizar.getEndereco().setId(cliente.getEndereco().getId());
				Cliente clienteAtualizado = this.clienteRepository.save(clienteParaAtualizar);
				ClienteDTO clienteDTOAtualizado = ClienteDTO.retornaCliente(clienteAtualizado);
				return clienteDTOAtualizado;
			}
			throw new CpfJaCadastradoException(cpfAtualizado);
		}
		throw new ClienteNaoEncontradoException(cpf);
	}
}
