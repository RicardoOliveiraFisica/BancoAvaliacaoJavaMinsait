package com.minsait.bancoricardo.enums;

import java.math.BigDecimal;
import java.math.MathContext;

public enum Relacionamento {
	
	BRONZE(1) {
		@Override
		public BigDecimal calculaValorFinal(BigDecimal valorInicial) {
			BigDecimal fatorMultiplicador = new BigDecimal("1.5");
			return valorInicial.multiply(fatorMultiplicador, MathContext.DECIMAL32);
		}
	},
	
	PRATA(2) {
		@Override
		public BigDecimal calculaValorFinal(BigDecimal valorInicial) {
			BigDecimal fatorMultiplicador = new BigDecimal("0.5");
			return valorInicial.multiply(fatorMultiplicador, MathContext.DECIMAL32);
		}
	},
	
	OURO(3) {
		@Override
		public BigDecimal calculaValorFinal(BigDecimal valorInicial) {
			BigDecimal fatorMultiplicador = new BigDecimal("1.0");
			return valorInicial.multiply(fatorMultiplicador, MathContext.DECIMAL32);
		}
	};
	
	private int codigo;	
	private Relacionamento(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return this.codigo;
	}

	public abstract BigDecimal calculaValorFinal(BigDecimal valorInicial);

}
