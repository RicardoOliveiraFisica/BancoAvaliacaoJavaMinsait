package com.minsait.bancoricardo.service;

import java.util.ArrayList;
import java.util.List;

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
	

}
