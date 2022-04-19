package br.com.lubank.banco.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe que representa a moldura de uma conta. Valores que podem ser
 * definidos: Numero, Nome, Saldo, Limite, DataCadastro, DataAtualiza��o, Tipo
 * de conta(Conta Corrente(001) ou Conta Poupanca�a(013)).
 * 
 * @author Lucas Melo Costa
 * @serial 1L
 */
@Entity
public abstract class Conta implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int numero;
	private String nome;
	protected BigDecimal saldo = new BigDecimal("0");
	protected BigDecimal limite = new BigDecimal("0");
	private LocalDateTime dataCadastro = LocalDateTime.now();
	private LocalDateTime dataAtualizacao = LocalDateTime.now();
	private String tipoDeConta;
	@SuppressWarnings("unused")
	private static int total = 0;

	protected Conta(int tipoDeConta) {
		Conta.total++;
		if (tipoDeConta == 001) {
			String tipoDeContaString = "ContaCorrente";
			this.tipoDeConta = tipoDeContaString;
		} else {
			if (tipoDeConta == 013) {
				String tipoDeContaString = "ContaPoupanca";
				this.tipoDeConta = tipoDeContaString;
			} else {
				String tipoDeContaString = "Tipo de conta não encontrada.";
				this.tipoDeConta = tipoDeContaString;
			}
		}
	}	

	protected Conta(int numero, String nome, BigDecimal saldo, BigDecimal limite, int tipoDeConta) {
		super();
		this.numero = numero;
		this.nome = nome;
		this.saldo = saldo;
		this.limite = limite;
		if (tipoDeConta == 001) {
			String tipoDeContaString = "ContaCorrente";
			this.tipoDeConta = tipoDeContaString;
		} else {
			if (tipoDeConta == 013) {
				String tipoDeContaString = "ContaPoupanca";
				this.tipoDeConta = tipoDeContaString;
			} else {
				String tipoDeContaString = "Tipo de conta não encontrada.";
				this.tipoDeConta = tipoDeContaString;
			}
		}
	}

	public long getId() {
		return id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public String getTipoDeConta() {
		return this.tipoDeConta;
	}

	public void setTipoDeConta(String tipoDeConta) {
		this.tipoDeConta = tipoDeConta;
	}

	/**
	 * Retorna o nome e o numero da conta.
	 */
	@Override
	public String toString() {
		return "\nNome: " + this.nome + ", Numero: " + this.numero;
	}

	/**
	 * Defini os dados de uma conta: Nome, Numero, Limite(Sempre maior do que o
	 * saldo!), Saldo.
	 * 
	 * @param nome
	 * @param numero
	 * @param Limite
	 * @param saldo
	 */
	public void inserirDados(String nome, int numero, BigDecimal Limite, BigDecimal saldo) {
		if (Limite.compareTo(saldo) == -1) {
			System.out.println("Limite excedido, conta não cadastrada.");
		} else {
			this.nome = nome;
			this.numero = numero;
			this.limite = Limite;
			this.saldo = saldo;
			System.out.println("Sucesso! O numero da sua conta é: " + this.numero);
		}
	}

	/**
	 * Se o numero inserido for diferente do numero da conta ele retorna false se o
	 * numero for igual ao numero da conta ele retorna true.
	 * 
	 * @param numero
	 * @return
	 */
	public boolean getNumeroBoolean(int numero) {
		if (this.numero != numero) {
			return false;
		}
		return true;
	}

}
