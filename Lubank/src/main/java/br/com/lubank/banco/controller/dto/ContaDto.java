package br.com.lubank.banco.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.lubank.banco.modelo.Conta;

public class ContaDto {

	private long id;
	private int numero;
	private String nome;
	private String tipoDeConta;
	private LocalDateTime dataAtualizacao;
	
	public ContaDto(Conta conta) {
		this.id = conta.getId();
		this.numero = conta.getNumero();
		this.nome = conta.getNome();
		this.tipoDeConta = conta.getTipoDeConta();
		this.dataAtualizacao = conta.getDataAtualizacao();
	}

	public long getId() {
		return id;
	}
	
	public int getNumero() {
		return numero;
	}

	public String getTitulo() {
		return nome;
	}

	public String getMensagem() {
		return tipoDeConta;
	}

	public LocalDateTime getDataCriacao() {
		return dataAtualizacao;
	}

	/**
	 * Converte um Page Conta em um Page ContaDto.
	 * 
	 * @param contas
	 * @return Retorna um Page de ContaDto.
	 */
	public static Page<ContaDto> converter(Page<Conta> contas) {
		return contas.map(ContaDto::new);
	}

}
