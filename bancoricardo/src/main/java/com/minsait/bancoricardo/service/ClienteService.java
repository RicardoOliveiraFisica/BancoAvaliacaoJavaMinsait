package com.minsait.bancoricardo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public Cliente cadastrarCliente(Cliente cliente) throws CpfJaCadastradoException {
		if (this.clienteRepository.existsByCpf(cliente.getCpf()) == false)
			return this.clienteRepository.save(cliente);
		throw new CpfJaCadastradoException(cliente.getCpf());
	}
	
	public List<Cliente> retornarTodosOsClientes() {
		return this.clienteRepository.findAll();
	}
	
	public Cliente retornarCliente(String cpf) throws ClienteNaoEncontradoException {
		if (this.clienteRepository.existsByCpf(cpf))
			return this.clienteRepository.findByCpf(cpf).get();
		throw new ClienteNaoEncontradoException(cpf);
	}
	

}
