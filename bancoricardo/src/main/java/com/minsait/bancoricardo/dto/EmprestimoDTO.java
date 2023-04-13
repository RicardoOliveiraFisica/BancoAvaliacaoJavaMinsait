package com.minsait.bancoricardo.dto;

import java.math.BigDecimal;
import java.sql.Date;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.minsait.bancoricardo.entity.Cliente;
import com.minsait.bancoricardo.entity.Emprestimo;
import com.minsait.bancoricardo.enums.Relacionamento;

public class EmprestimoDTO {
	private String cpfCliente;		

	@DecimalMin(value = "0.0", inclusive = false, message="Valor de empréstimo inválido")
	private BigDecimal valorInicial;
	
	private BigDecimal valorFinal;
	
	@NotNull(message = "O tipo de RELACIONAMENTO é obrigatório")
	//@EnumNamePattern(regexp = "BRONZE|PRATA|OURO")
	private Relacionamento relacionamento;

	@NotNull(message = "A data inicial é obrigatória")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dataInicial;
	
	@NotNull(message = "A data final é obrigatória")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dataFinal;
	
	public EmprestimoDTO() {}
	
	public EmprestimoDTO(String cpfCliente, BigDecimal valorInicial, Relacionamento relacionamento, Date dataInicial, Date dataFinal) {
		super();
		this.cpfCliente = cpfCliente;
		this.valorInicial = valorInicial;
		this.relacionamento = relacionamento;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
	}

	public EmprestimoDTO(Emprestimo emprestimo) {
		super();
		this.cpfCliente = emprestimo.getCliente().getCpf();
		this.valorInicial = emprestimo.getValorInicial();
		this.valorFinal = emprestimo.getValorFinal();
		this.relacionamento = emprestimo.getRelacionamento();
		this.dataInicial = emprestimo.getDataInicial();
		this.dataFinal = emprestimo.getDataFinal();
	}
	
	
	public static Emprestimo retornaEmprestimo(EmprestimoDTO emprestimoDTO, Cliente cliente) {
		Emprestimo emprestimo = new Emprestimo(cliente, emprestimoDTO.getValorInicial(), emprestimoDTO.getRelacionamento(), emprestimoDTO.getDataInicial(), emprestimoDTO.getDataFinal());
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
		
	public BigDecimal getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(BigDecimal valorFinal) {
		this.valorFinal = valorFinal;
	}

	public Relacionamento getRelacionamento() {
		return relacionamento;
	}

	public void setRelacionamento(Relacionamento relacionamento) {
		this.relacionamento = relacionamento;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
}
