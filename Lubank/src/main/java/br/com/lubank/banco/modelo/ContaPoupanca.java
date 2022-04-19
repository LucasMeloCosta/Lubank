package br.com.lubank.banco.modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.Entity;

import br.com.lubank.banco.dao.ContaDAO;

/**
 * Classe que gera um Conta Poupan�a. Seu dinheiro rende por minuto 0,5% mas as
 * taxas de saque e transfer�ncias s�o significativamente altas. Tipo de Conta =
 * 013
 * 
 * @author Lucas Melo Costa
 * @serial 1L
 */
@Entity
public class ContaPoupanca extends Conta implements Taxas {

	private static final long serialVersionUID = 1L;
	private static int tipoDeConta = 013;

	public ContaPoupanca() {
		super(tipoDeConta);
	}
	public ContaPoupanca(int numero, String nome, BigDecimal saldo, BigDecimal limite) {
		super(numero, nome, saldo, limite, tipoDeConta);
	}

	/**
	 * Taxa de Saque deste tipo de conta � 50.50
	 */
	@Override
	public BigDecimal taxaSaque() {
		BigDecimal tS = new BigDecimal("50.50");
		return tS;
	}

	/**
	 * Taxa de transferencia deste tipo de conta � 40.20
	 */
	@Override
	public BigDecimal taxaTransferencia() {
		return new BigDecimal("40.20");
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public boolean getNumeroBoolean(int numero) {
		return super.getNumeroBoolean(numero);
	}

	/**
	 * Subtrai o 'saldo da conta + rendimento da poupan�a' com o 'valor + a taxa de
	 * saque'. Gera contato com o banco de dados atraves do numero.
	 * 
	 * @param numero
	 * @param valor
	 * @return Se o saldo for menor que o valor a opera��o ira retornar false, mas
	 *         se for maior ela ir� retornar true e o saldo e a data de atualiza��o
	 *         v�o ser atualizados no banco de dados.
	 */
	public boolean saca(int numero, BigDecimal valor) {
		ContaDAO contaDao = new ContaDAO();
		for (ContaPoupanca c : contaDao.getContatoPoupanca(numero)) {
			LocalDateTime hojecompara = LocalDateTime.now();
			LocalDateTime hoje = c.getDataAtualizacao();
			Duration hoje2 = Duration.between(hoje, hojecompara);
			Float v1 = c.getSaldo().multiply(new BigDecimal(hoje2.toMinutes() * 0.005)).add(c.getSaldo())
					.setScale(2, RoundingMode.HALF_EVEN).floatValue();
			Float v2 = valor.add(this.taxaSaque()).floatValue();
			if (v1 < v2) {
				System.out.println("Saldo insuficiente.");
				return false;
			} else {
				c.saldo = c.saldo.multiply(new BigDecimal(hoje2.toMinutes() * 0.005)).add(c.getSaldo())
						.setScale(2, RoundingMode.HALF_EVEN).subtract(valor.add(c.taxaSaque()));
				System.out.println(
						"Transa�ao concluida!\n{O saldo da conta de '" + c.getNome() + "' �: " + c.saldo + "}");
				contaDao.updateContaPoupanca(c);
				return true;
			}
		}
		return true;
	}

	/**
	 * Soma o 'saldo da conta + rendimento da poupan�a' com o valor inserido. Gera
	 * contato com o banco de dados atraves do numero.
	 * 
	 * @param numero
	 * @param valor
	 * @return Se o limite da conta for menor que a soma a opera��o ir� retornar
	 *         false, mas se o limite for maior ir� retornar true e o saldo e a data
	 *         de atualiza��o v�o ser atualizados no banco de dados.
	 */
	public Boolean deposita(int numero, BigDecimal valor) {
		ContaDAO contaDao = new ContaDAO();
		for (ContaPoupanca c : contaDao.getContatoPoupanca(numero)) {
			LocalDateTime hojecompara = LocalDateTime.now();
			LocalDateTime hoje = c.getDataAtualizacao();
			Duration hoje2 = Duration.between(hoje, hojecompara);
			Float v1 = valor.floatValue();
			Float v2 = c.getLimite().floatValue();
			if (v1 > v2 - c.getSaldo().multiply(new BigDecimal(hoje2.toMinutes() * 0.005)).add(c.getSaldo())
					.setScale(2, RoundingMode.HALF_EVEN).doubleValue()) {
				System.out.println("Limite excedido, transa��o n�o concluida.");
				return false;
			} else {
				c.saldo = c.saldo.multiply(new BigDecimal(hoje2.toMinutes() * 0.005)).add(c.getSaldo())
						.setScale(2, RoundingMode.HALF_EVEN).add(valor);
				System.out
						.println("Deposito para conta de '" + c.getNome() + "' no valor de $" + valor + " concluido!");
				contaDao.updateContaPoupanca(c);
			}
		}
		return true;
	}

	/**
	 * Gera contato com o banco de dados atraves do numero.
	 * 
	 * @param numero
	 * @return Retorna uma string com o 'saldo + rendimento da poupan�a' e o limite
	 *         da conta.
	 */
	public void mostrarSaldo(int numero) {
		ContaDAO contaDao = new ContaDAO();
		for (ContaPoupanca c : contaDao.getContatoPoupanca(numero)) {
			LocalDateTime hojecompara = LocalDateTime.now();
			LocalDateTime hoje = c.getDataAtualizacao();
			Duration hoje2 = Duration.between(hoje, hojecompara);
			System.out.println("\nSaldo: " + c.getSaldo().multiply(new BigDecimal(hoje2.toMinutes() * 0.005))
					.add(c.getSaldo()).setScale(2, RoundingMode.HALF_EVEN) + "," + " Limite: " + c.getLimite() + ".");
			System.out.println("{Saldo: "
					+ c.getSaldo() + ", Rendimento Poupan�a: " + c.getSaldo()
							.multiply(new BigDecimal(hoje2.toMinutes() * 0.005)).setScale(2, RoundingMode.HALF_EVEN)
					+ "}");
		}
	}

	/**
	 * Gera contato com o banco de dados atraves do numero.
	 * 
	 * @param numero
	 * @return Retorna uma string com: Tipo da conta, Nome, Numero, Saldo +
	 *         rendimento da poupan�a, Limite e Data de Atualiza��o.
	 */
	public void mostrarDados(int numero) {
		ContaDAO contaDao = new ContaDAO();
		for (ContaPoupanca c : contaDao.getContatoPoupanca(numero)) {
			LocalDateTime hojecompara = LocalDateTime.now();
			LocalDateTime hoje = c.getDataAtualizacao();
			Duration hoje2 = Duration.between(hoje, hojecompara);
			System.out.println(
					"[" + c.getTipoDeConta() + "] Conta: " + c.getNome() + "\nNumero: " + c.getNumero() + "\nSaldo: "
							+ c.getSaldo().multiply(new BigDecimal(hoje2.toMinutes() * 0.005)).add(c.getSaldo())
									.setScale(2, RoundingMode.HALF_EVEN)
							+ "\nLimite: " + c.getLimite() + "\nData de Atualiza��o: " + c.getDataAtualizacao());
		}
	}

	/**
	 * Indicada para devolver informa��es da conta para usuarios que n�o s�o donos
	 * da conta. Gera contato com o banco de dados atraves do numero.
	 * 
	 * @param numero
	 * @return Retorna uma string com o Nome e o Numero da conta.
	 */
	public void mostrarDadosDestinatario(int numero) {
		ContaDAO contaDao = new ContaDAO();
		for (ContaPoupanca c : contaDao.getContatoPoupanca(numero)) {
			System.out.println(
					"Nome destinat�rio: " + c.getNome() + "." + "\nNumero destinat�rio: " + c.getNumero() + ".");
		}
	}

	/**
	 * Verifica se o 'saldo da conta + rendimento da poupan�a' � maior que o valor
	 * inserido. Gera contato com o banco de dados atraves do numero.
	 * 
	 * @param numero
	 * @param valor
	 * @return Se o saldo for menor que o valor a opera��o ir� retornar false, mas
	 *         se for maior ela ir� retornar true. *
	 */
	public boolean verificaSaldo(int numero, BigDecimal valor) {
		ContaDAO contaDao = new ContaDAO();
		for (ContaPoupanca c : contaDao.getContatoPoupanca(numero)) {
			LocalDateTime hojecompara = LocalDateTime.now();
			LocalDateTime hoje = c.getDataAtualizacao();
			Duration hoje2 = Duration.between(hoje, hojecompara);
			Float v1 = c.getSaldo().multiply(new BigDecimal(hoje2.toMinutes() * 0.005)).add(c.getSaldo())
					.setScale(2, RoundingMode.HALF_EVEN).floatValue();
			Float v2 = valor.floatValue();
			if (v1 < v2) {
				return false;
			} else {
				return true;
			}
		}
		return true;
	}
	
	public boolean verificaSaldoSaque(int numero, BigDecimal valor) {
		ContaDAO contaDao = new ContaDAO();
		for (ContaPoupanca c : contaDao.getContatoPoupanca(numero)) {
			LocalDateTime hojecompara = LocalDateTime.now();
			LocalDateTime hoje = c.getDataAtualizacao();
			Duration hoje2 = Duration.between(hoje, hojecompara);
			Float v1 = c.getSaldo().multiply(new BigDecimal(hoje2.toMinutes() * 0.005)).add(c.getSaldo())
					.setScale(2, RoundingMode.HALF_EVEN).floatValue();
			Float v2 = valor.add(this.taxaSaque()).floatValue();
			if (v1 < v2) {
				return false;
			} else {
				return true;
			}
		}
		return true;
	}
	
	public boolean verificaSaldoTransfere(int numero, BigDecimal valor) {
		ContaDAO contaDao = new ContaDAO();
		for (ContaPoupanca c : contaDao.getContatoPoupanca(numero)) {
			LocalDateTime hojecompara = LocalDateTime.now();
			LocalDateTime hoje = c.getDataAtualizacao();
			Duration hoje2 = Duration.between(hoje, hojecompara);
			Float v1 = c.getSaldo().multiply(new BigDecimal(hoje2.toMinutes() * 0.005)).add(c.getSaldo())
					.setScale(2, RoundingMode.HALF_EVEN).floatValue();
			Float v2 = valor.add(this.taxaTransferencia()).floatValue();
			if (v1 < v2) {
				return false;
			} else {
				return true;
			}
		}
		return true;
	}

	/**
	 * Verifica se o valor inserido � menor que o 'limite - saldo da conta'. Gera
	 * contato com o banco de dados atraves do numero.
	 * 
	 * @param numero
	 * @param valor
	 * @return Se o valor for maior que o limite ir� retornar false, mas se for
	 *         menor ir� retornar true.
	 */
	public Boolean verificaLimite(int numero, BigDecimal valor) {
		ContaDAO contaDao = new ContaDAO();
		for (ContaPoupanca c : contaDao.getContatoPoupanca(numero)) {
			LocalDateTime hojecompara = LocalDateTime.now();
			LocalDateTime hoje = c.getDataAtualizacao();
			Duration hoje2 = Duration.between(hoje, hojecompara);
			Float v1 = valor.floatValue();
			Float v2 = c.getLimite().floatValue();
			if (v1 > v2 - c.getSaldo().multiply(new BigDecimal(hoje2.toMinutes() * 0.005)).add(c.getSaldo())
					.setScale(2, RoundingMode.HALF_EVEN).doubleValue()) {
				return false;
			} else {
				return true;
			}
		}
		return true;
	}

	/**
	 * Transfere um valor especifico do saldo de uma conta para outra conta. Gera
	 * contato com o banco de dados atraves do numero.
	 * 
	 * @param contaSaque
	 * @param contaDestino
	 * @param valor
	 * @return Se a conta do saque possuir saldo suficiente e a conta de destino
	 *         possuir limite a transaferencia ser� concluida. Se algum desses
	 *         parametros n�o forem atendidos a opera��o ira retornar uma string
	 *         especificando qual parametro n�o foi atendido.
	 */
	public void transfere(int contaSaque, int contaDestino, BigDecimal valor) {
		ContaDAO contaDaoSaque = new ContaDAO();
		ContaDAO contaDaoDestino = new ContaDAO();
		for (ContaPoupanca cS : contaDaoSaque.getContatoPoupanca(contaSaque)) {
			for (ContaPoupanca cD : contaDaoDestino.getContatoPoupanca(contaDestino)) {
				if (cS.verificaSaldoTransfere(contaSaque, valor) && cD.verificaLimite(contaDestino, valor) == true) {
					cS.saca(contaSaque, valor.subtract(new BigDecimal("10.30")));
					cD.deposita(contaDestino, valor);
				} else {
					if (cD.verificaLimite(contaDestino, valor) == true) {
						System.out.println("Saldo de '" + cS.getNome() + "' insuficiente, transa��o n�o concluida.");
					} else {
						System.out.println("Limite de '" + cD.getNome() + "' excedido, transa��o n�o concluida.");
					}
				}
			}
		}
	}

	/**
	 * Retorna o nome e o tipo da conta. Gera contato com o banco de dados atraves
	 * do numero.
	 * 
	 * @param numero
	 */
	public void getTipoDeConta(int numero) {
		ContaDAO contaDao = new ContaDAO();
		if (contaDao.getTipoDeConta(numero) == 001) {
			for (ContaCorrente c : contaDao.getContatoCorrente(numero)) {
				System.out.println("Nome destinat�rio: " + c.getNome() + "." + "\nConta do tipo Conta Corrente(001).");
			}
		} else {
			if (contaDao.getTipoDeConta(numero) == 013) {
				for (ContaCorrente c : contaDao.getContatoCorrente(numero)) {
					System.out.println(
							"Nome destinat�rio: " + c.getNome() + "." + "\nConta do tipo Conta Poupan�a(013).");
				}
			} else {
				System.out.println("Tipo de conta n�o encontrada.");
			}
		}
	}

	public void inserirDados(String nome, int numero, BigDecimal limite, BigDecimal saldo) {
		super.inserirDados(nome, numero, limite, saldo);
	}

	public String mostrarDadosString(int numeroDaConta) {
		ContaDAO contaDao = new ContaDAO();
		String nome = null;
		String numero = null;
		String saldo = null;
		String limite = null;
		String dataCadastro = null;
		for (ContaPoupanca c : contaDao.getContatoPoupanca(numeroDaConta)) {
			LocalDateTime hojecompara = LocalDateTime.now();
			LocalDateTime hoje = c.getDataAtualizacao();
			Duration hoje2 = Duration.between(hoje, hojecompara);
			nome = c.getNome();
			numero = String.valueOf(c.getNumero());
			saldo = c.getSaldo().multiply(new BigDecimal(hoje2.toMinutes() * 0.005)).add(c.getSaldo())
					.setScale(2, RoundingMode.HALF_EVEN).toString();
			limite = c.getLimite().toString();
			dataCadastro = c.getDataCadastro().toString();

		}
		return "Nome: " + nome + "\nNumero: " + numero + "\nSaldo: " + saldo + "\nLimite" + limite
				+ "\nData de cadastro: " + dataCadastro;
	}
	
	public String mostrarSaldoString(int numero) {
		ContaDAO contaDao = new ContaDAO();
		String saldo = null;
		String limite = null;
		String rendimento = null;
		for (ContaPoupanca c : contaDao.getContatoPoupanca(numero)) {
			LocalDateTime hojecompara = LocalDateTime.now();
			LocalDateTime hoje = c.getDataAtualizacao();
			Duration hoje2 = Duration.between(hoje, hojecompara);
			saldo = c.getSaldo().multiply(new BigDecimal(hoje2.toMinutes() * 0.005))
					.add(c.getSaldo()).setScale(2, RoundingMode.HALF_EVEN).toString();
			limite = c.getLimite().toString();
			rendimento = c.getSaldo().multiply(new BigDecimal(hoje2.toMinutes() * 0.005)).setScale(2, RoundingMode.HALF_EVEN).toString();
		}
		return "Saldo: " + saldo + "\nLimite: " + limite + "\nRendimento da poupança: " + rendimento;

	}
	
	public int transfereCompara(int contaSaque, int contaDestino, BigDecimal valor) {
		ContaDAO contaDaoSaque = new ContaDAO();
		ContaDAO contaDaoDestino = new ContaDAO();
		for (ContaPoupanca cS : contaDaoSaque.getContatoPoupanca(contaSaque)) {
			for (ContaCorrente cD : contaDaoDestino.getContatoCorrente(contaDestino)) {
				valor.add(this.taxaTransferencia());
				if (cS.verificaSaldoTransfere(contaSaque, valor) && cD.verificaLimite(contaDestino, valor) == true) {
					return 1;
				} else {
					if (cD.verificaLimite(contaDestino, valor) == true) {
						return 0;
					} else {
						return -1;
					}
				}
			}
		}
		return 2;
	}
	
	public String transfereString(int contaSaque, int contaDestino, BigDecimal valor) {
		ContaDAO contaDaoSaque = new ContaDAO();
		ContaDAO contaDaoDestino = new ContaDAO();
		for (ContaPoupanca cS : contaDaoSaque.getContatoPoupanca(contaSaque)) {
			for (ContaCorrente cD : contaDaoDestino.getContatoCorrente(contaDestino)) {
				valor.add(this.taxaTransferencia());
				
				if (cS.verificaSaldoTransfere(contaSaque, valor) && cD.verificaLimite(contaDestino, valor) == true) {
					cS.saca(contaSaque, valor.subtract(new BigDecimal("10.3")));
					cD.deposita(contaDestino, valor);
					return "Transação concluida!";
				} else {
					if (cD.verificaLimite(contaDestino, valor) == true) {
						System.out.println("Saldo de '" + cS.getNome() + "' insuficiente, transação não concluida.");
						return "Saldo de '" + cS.getNome() + "' insuficiente, transação não concluida.";
					} else {
						System.out.println("Limite de '" + cD.getNome() + "' excedido, transação não concluida.");
						return "Limite de '" + cD.getNome() + "' excedido, transação não concluida.";
					}
				}
			}
		}
		return "Erro não encontrado";
	}
}
