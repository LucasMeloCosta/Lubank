package br.com.lubank.banco.modelo;

import java.math.BigDecimal;

public interface Taxas {	
	BigDecimal taxaSaque();
	BigDecimal taxaTransferencia();
}
