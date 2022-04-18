package br.com.lubank.banco.controller.form;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.lubank.banco.dao.ContaDAO;
import br.com.lubank.banco.modelo.ContaCorrente;
import br.com.lubank.banco.modelo.ContaPoupanca;

public class ContaForm {
	ContaDAO contaDao = new ContaDAO();
	
	@NotNull 
	private int numero = contaDao.geraNumero();
	@NotNull @NotEmpty @Length(min = 5)
	private String nome;
	protected BigDecimal saldo = new BigDecimal("0");
	protected BigDecimal limite = new BigDecimal("0");
	private LocalDateTime dataAtualizacao = LocalDateTime.now();
	
	public int getNumero() {
		return numero;
	}
	public String getNome() {
		return nome;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public BigDecimal getLimite() {
		return limite;
	}
	public LocalDateTime getDataCadastro() {
		return dataAtualizacao;
	}

	public ContaCorrente converterContaCorrente() {
		return new ContaCorrente(numero, nome, saldo, limite);
	}
	
	public ContaPoupanca converterContaPoupanca() {
		return new ContaPoupanca(numero, nome, saldo, limite);
	}
	
}
