package com.minsait.bancoricardo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class ExcedidoValorLimiteEmprestimosException extends Exception{
	static final long serialVersionUID = 1L;

	public ExcedidoValorLimiteEmprestimosException(String cpf) {
		super(String.format("Empréstimo negado para o CPF %s, pois ultrapassa o limite do cliente", cpf));
	}
}
