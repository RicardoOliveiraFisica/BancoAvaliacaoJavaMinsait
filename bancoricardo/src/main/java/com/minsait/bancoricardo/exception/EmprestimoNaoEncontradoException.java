package com.minsait.bancoricardo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmprestimoNaoEncontradoException extends Exception {
	private static final long serialVersionUID = 1L;

	public EmprestimoNaoEncontradoException(Long id) {
		super(String.format("O emprestimo de id %s não foi encontrado", id));		
	}

	public EmprestimoNaoEncontradoException(Long id, String cpf) {
		super(String.format("O emprestimo de id %s não pertence ao cpf %s", id, cpf));		
	}
}
