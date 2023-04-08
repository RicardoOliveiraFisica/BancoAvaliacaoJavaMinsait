package com.minsait.bancoricardo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.minsait.bancoricardo.dto.ClienteDTO;
import com.minsait.bancoricardo.exception.ClienteNaoEncontradoException;
import com.minsait.bancoricardo.exception.CpfJaCadastradoException;
import com.minsait.bancoricardo.service.ClienteService;
import com.minsait.bancoricardo.service.MensagemDeSucesso;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/bancoricardo/clientes")
public class ClienteController {
	private ClienteService clienteService;
	
	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteDTO cadastrarCliente(@Valid @RequestBody ClienteDTO clienteDTO) throws CpfJaCadastradoException {
		return this.clienteService.cadastrarCliente(clienteDTO);
	}	
	
	@GetMapping
	public List<ClienteDTO> retornarTodosOsClientes() {
		return this.clienteService.retornarTodosOsClientes();
	}
	
	@GetMapping("/{cpf}")
	public ClienteDTO retornarCliente(@PathVariable String cpf) throws ClienteNaoEncontradoException  {
		return this.clienteService.retornarCliente(cpf);
	}
	
	@DeleteMapping("/{cpf}")
	public MensagemDeSucesso deletarCliente(@PathVariable String cpf) throws ClienteNaoEncontradoException {
		return this.clienteService.deletarCliente(cpf);		
	}
	
	@PutMapping("/{cpf}")
	public ClienteDTO atualizarCliente( @PathVariable String cpf, @Valid @RequestBody ClienteDTO clienteDTO) throws ClienteNaoEncontradoException {
		return this.clienteService.atualizarCliente(cpf, clienteDTO);
	}

}
