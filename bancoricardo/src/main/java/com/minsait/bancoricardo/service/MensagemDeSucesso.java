package com.minsait.bancoricardo.service;

public class MensagemDeSucesso {
	
	String mensagem;
	
	MensagemDeSucesso(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
