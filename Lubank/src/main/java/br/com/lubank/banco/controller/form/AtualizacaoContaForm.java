package br.com.lubank.banco.controller.form;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.lubank.banco.modelo.Conta;
import br.com.lubank.banco.repository.ContaRepositoryAtualizacao;

public class AtualizacaoContaForm {

	protected BigDecimal saldo = new BigDecimal("0");
	private LocalDateTime dataAtualizacao = LocalDateTime.now();

	public BigDecimal getSaldo() {
		return saldo;
	}

	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}

	public Conta atualizar(int numero, ContaRepositoryAtualizacao contaRepository) {
		Conta conta = contaRepository.findByNumero(numero);

		conta.setSaldo(saldo);
		conta.setDataAtualizacao(dataAtualizacao);

		return conta;
	}

}
