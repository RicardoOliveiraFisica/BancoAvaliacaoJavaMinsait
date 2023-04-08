package com.minsait.bancoricardo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.minsait.bancoricardo.dto.EmprestimoDTO;
import com.minsait.bancoricardo.exception.ClienteNaoEncontradoException;
import com.minsait.bancoricardo.service.EmprestimoService;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/bancoricardo/clientes")
public class EmprestimoController {
	private EmprestimoService emprestimoService;
	


	@Autowired
	public EmprestimoController(EmprestimoService emprestimoService) {
		this.emprestimoService = emprestimoService;
	}
	
	@PostMapping("/{cpf}/emprestimos")
	@ResponseStatus(HttpStatus.CREATED)
	public EmprestimoDTO cadastrarEmprestimo(@PathVariable String cpf, @Valid @RequestBody EmprestimoDTO emprestimoDTO) throws ClienteNaoEncontradoException {
		return this.emprestimoService.cadastrarEmprestimo(cpf, emprestimoDTO);
	}
	
	public EmprestimoService getEmprestimoService() {
		return emprestimoService;
	}

	public void setEmprestimoService(EmprestimoService emprestimoService) {
		this.emprestimoService = emprestimoService;
	}

}
