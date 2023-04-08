package com.minsait.bancoricardo.dto;

import java.math.BigDecimal;

import com.minsait.bancoricardo.entity.Cliente;
import com.minsait.bancoricardo.entity.Emprestimo;

public class EmprestimoDTO {
	String cpfCliente;		
	BigDecimal valorInicial;
	
	public EmprestimoDTO() {}
	

	public EmprestimoDTO(String cpfCliente, BigDecimal valorInicial) {
		super();
		this.cpfCliente = cpfCliente;
		this.valorInicial = valorInicial;
	}

	public EmprestimoDTO(Emprestimo emprestimo) {
		super();
		this.cpfCliente = emprestimo.getCliente().getCpf();
		this.valorInicial = emprestimo.getValorInicial();
	}
	
	public static Emprestimo retornaEmprestimo(EmprestimoDTO emprestimoDTO, Cliente cliente) {
		Emprestimo emprestimo = new Emprestimo(cliente, emprestimoDTO.getValorInicial());
		return emprestimo;
	}
	
	public static EmprestimoDTO retornaEmprestimo(Emprestimo emprestimo) {
		EmprestimoDTO emprestimoDTO = new EmprestimoDTO(emprestimo);
		return emprestimoDTO;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public BigDecimal getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(BigDecimal valorInicial) {
		this.valorInicial = valorInicial;
	}
	
}
