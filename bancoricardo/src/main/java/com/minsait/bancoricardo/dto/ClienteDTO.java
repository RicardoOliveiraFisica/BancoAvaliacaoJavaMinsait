package com.minsait.bancoricardo.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.minsait.bancoricardo.entity.Cliente;
import com.minsait.bancoricardo.entity.Endereco;

public class ClienteDTO {
	@NotBlank(message = "O nome é obrigatório")
	@Length(min = 3, max = 100, message = "O nome deverá ter de {min} a {max} caracteres")	
	private String nome;
	
	@NotBlank(message = "O CPF é obrigatório")
	@CPF(message = "CPF inválido")
	private String cpf;
	
	@NotBlank(message = "Um número de telefone é obrigatório")
	@Length(min = 8, max = 11, message = "Número de telefone inválido")	
	private String telefone;
	
	@NotBlank(message = "O nome da rua é obrigatório")
	private String rua;
	
	@NotNull(message = "O número da residência é obrigatório")
	private Integer numero;
	
	@NotBlank(message = "O CEP é obrigatório")
	private String cep;
	
	@NotNull(message = "O rendimento mensal é obrigatório")
	@DecimalMin(value = "0.0", inclusive = false, message="Valor de rendimento mensal inválido")
	private BigDecimal rendimentoMensal;
	
	public ClienteDTO() {}

	public ClienteDTO(String nome, String cpf, String telefone, String rua, Integer numero, String cep,
			BigDecimal rendimentoMensal) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.rua = rua;
		this.numero = numero;
		this.cep = cep;
		this.rendimentoMensal = rendimentoMensal;
	}
	
	public ClienteDTO(Cliente cliente) {
		super();
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.telefone = cliente.getTelefone();
		this.rua = cliente.getEndereco().getRua();
		this.numero = cliente.getEndereco().getNumero();
		this.cep = cliente.getEndereco().getCep();
		this.rendimentoMensal = cliente.getRendimentoMensal();
	}
	
	public static Cliente retornaCliente(ClienteDTO clienteDTO) {
		Endereco endereco = new Endereco(clienteDTO.getRua(), clienteDTO.getNumero(), clienteDTO.getCep());
		Cliente cliente = new Cliente(clienteDTO.getNome(),  clienteDTO.getCpf(), clienteDTO.getTelefone(), endereco, clienteDTO.getRendimentoMensal());
		return cliente;
	}
	
	public static ClienteDTO retornaCliente(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO(cliente);
		return clienteDTO;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public BigDecimal getRendimentoMensal() {
		return rendimentoMensal;
	}

	public void setRendimentoMensal(BigDecimal rendimentoMensal) {
		this.rendimentoMensal = rendimentoMensal;
	}	
	
}
