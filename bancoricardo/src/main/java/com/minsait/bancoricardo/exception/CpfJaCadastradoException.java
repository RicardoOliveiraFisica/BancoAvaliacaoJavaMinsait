package com.minsait.bancoricardo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class CpfJaCadastradoException extends Exception {
	private static final long serialVersionUID = 1L;

	public CpfJaCadastradoException(String cpf) {
		super(String.format("O CPF %s já está cadastrado", cpf));
	}

}
