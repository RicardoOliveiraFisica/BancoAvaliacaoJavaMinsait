package com.minsait.bancoricardo.entity;

import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.minsait.bancoricardo.enums.Relacionamento;

@Entity
public class Emprestimo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name="cliente_cpf")
	private Cliente cliente;
	
	private BigDecimal valorInicial;
	private BigDecimal valorFinal;
	private Relacionamento relacionamento;
	private Date dataInicial;
	private Date dataFinal;
	
	public Emprestimo() {}
		
	public Emprestimo(Cliente cliente, BigDecimal valorInicial, Relacionamento relacionamento, Date dataInicial, Date dataFinal) {
		super();
		this.cliente = cliente;
		this.valorInicial = valorInicial;
		this.relacionamento = relacionamento;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.setValorFinal();
	}


	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
	
	public void setValorFinal() {
		this.valorFinal = this.relacionamento.calculaValorFinal(this);
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
