package com.minsait.bancoricardo.enums;

import java.math.BigDecimal;
import java.math.MathContext;

import com.minsait.bancoricardo.entity.Cliente;
import com.minsait.bancoricardo.entity.Emprestimo;

public enum Relacionamento {
	
	BRONZE(1) {
		@Override
		public BigDecimal calculaValorFinal(Emprestimo emprestimo) {
			BigDecimal fatorMultiplicador = new BigDecimal("1.80");
			return emprestimo.getValorInicial().multiply(fatorMultiplicador, MathContext.DECIMAL32);
		}
	},
	
	PRATA(2) {
		@Override
		public BigDecimal calculaValorFinal(Emprestimo emprestimo) {
			BigDecimal fatorMultiplicador;
			if (emprestimo.getValorInicial().compareTo(new BigDecimal("5000.00")) == 1) {
				fatorMultiplicador = new BigDecimal("1.40");
			}
			else {
				fatorMultiplicador = new BigDecimal("1.60");
			}			
			return emprestimo.getValorInicial().multiply(fatorMultiplicador, MathContext.DECIMAL32);
		}
	},
	
	OURO(3) {
		@Override
		public BigDecimal calculaValorFinal(Emprestimo emprestimo) {
			BigDecimal fatorMultiplicador;
			Cliente cliente = emprestimo.getCliente();
			if (cliente.getEmprestimos().size() == 0) {
				fatorMultiplicador = new BigDecimal("1.20");
			}
			else {
				fatorMultiplicador = new BigDecimal("1.30");
			}			
			return emprestimo.getValorInicial().multiply(fatorMultiplicador, MathContext.DECIMAL32);
		}
	};
	
	private int codigo;	
	private Relacionamento(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return this.codigo;
	}

	public abstract BigDecimal calculaValorFinal(Emprestimo emprestimo);

}
