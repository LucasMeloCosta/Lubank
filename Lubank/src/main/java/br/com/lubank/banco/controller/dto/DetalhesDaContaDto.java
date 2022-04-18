package br.com.lubank.banco.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.lubank.banco.modelo.Conta;

public class DetalhesDaContaDto {

	private long id;
	private int numero;
	private String nome;
	protected BigDecimal saldo = new BigDecimal("0");
	protected BigDecimal limite = new BigDecimal("0");
	private LocalDateTime dataCadastro = LocalDateTime.now();
	private LocalDateTime dataAtualizacao = LocalDateTime.now();
	private String tipoDeConta;
	
	public DetalhesDaContaDto(Conta conta) {
		this.id = conta.getId();
		this.numero = conta.getNumero();
		this.nome = conta.getNome();
		this.saldo = conta.getSaldo();
		this.limite = conta.getLimite();
		this.dataCadastro = conta.getDataCadastro();
		this.dataAtualizacao = conta.getDataAtualizacao();
		this.tipoDeConta = conta.getTipoDeConta();
	}
	public long getId() {
		return id;
	}
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
		return dataCadastro;
	}
	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}
	public String getTipoDeConta() {
		return tipoDeConta;
	}
	
	
	
}
