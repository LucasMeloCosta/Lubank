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

	/**
	 * Converte os dados {nome, saldo, limite} em uma conta corrente.
	 * Gera um numero aleatorio mas diferente de todos registrados no banco de dados.
	 * 
	 * @param numero
	 * @param nome
	 * @param saldo
	 * @param limite
	 * @return Retorna uma conta corrente.
	 */
	public ContaCorrente converterContaCorrente() {
		return new ContaCorrente(numero, nome, saldo, limite);
	}
	
	/**
	 * Converte os dados {nome, saldo, limite} em uma conta poupança.
	 * Gera um numero aleatorio mas diferente de todos registrados no banco de dados.
	 * 
	 * @param numero
	 * @param nome
	 * @param saldo
	 * @param limite
	 * @return Retorna uma conta poupança.
	 */
	public ContaPoupanca converterContaPoupanca() {
		return new ContaPoupanca(numero, nome, saldo, limite);
	}
	
}
