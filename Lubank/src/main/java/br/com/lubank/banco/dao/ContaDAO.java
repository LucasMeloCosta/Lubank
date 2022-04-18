package br.com.lubank.banco.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.RestController;

import br.com.lubank.banco.conexoes.ConnectionFactory;
import br.com.lubank.banco.modelo.ContaCorrente;
import br.com.lubank.banco.modelo.ContaPoupanca;

/**
 * @author Lucas Melo Costa
 * @serial 1L
 */
@RestController
public class ContaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Metodo que salva uma Conta Corrente no banco de dados(Nome, Saldo, Data de
	 * Cadastro, Numero, Limite, Data de Atualiza��o e Tipo de Conta).
	 * 
	 * @param contato = Conta Corrente.
	 */
	public void saveContaCorrente(ContaCorrente contato) {

		String sql = "INSERT INTO contas(nome, saldo, datacadastro, numero, limite, DataAtualizacao, TipoDeConta) VALUES (?, ?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Criar um conexao com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			// Criamos um PreparedStatement para executar um query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			// Adicionar os valores que sao esperados pela query
			pstm.setString(1, contato.getNome());
			pstm.setBigDecimal(2, contato.getSaldo());
			pstm.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
			pstm.setInt(4, contato.getNumero());
			pstm.setBigDecimal(5, contato.getLimite());
			pstm.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
			pstm.setString(7, contato.getTipoDeConta());

			// Executar a query
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Fechar as conexoes
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Metodo que salva uma Conta Poupan�a no banco de dados(Nome, Saldo, Data de
	 * Cadastro, Numero, Limite, Data de Atualiza��o e Tipo de Conta).
	 * 
	 * @param contato = Conta Poupan�a.
	 */
	public void saveContaPoupanca(ContaPoupanca contato) {

		String sql = "INSERT INTO contas(nome, saldo, datacadastro, numero, limite, DataAtualizacao, TipoDeConta) VALUES (?, ?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Criar um conexao com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			// Criamos um PreparedStatement para executar um query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			// Adicionar os valores que sao esperados pela query
			pstm.setString(1, contato.getNome());
			pstm.setBigDecimal(2, contato.getSaldo());
			pstm.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
			pstm.setInt(4, contato.getNumero());
			pstm.setBigDecimal(5, contato.getLimite());
			pstm.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
			pstm.setString(7, contato.getTipoDeConta());

			// Executar a query
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Fechar as conexoes
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Metodo que atualiza o Saldo e a Data de Atualiza��o. Gera contato com o banco
	 * de dados atraves do numero da conta.
	 * 
	 * @param contato = Conta Corrente.
	 */
	public void updateContaCorrente(ContaCorrente contato) {

		String sql = "UPDATE contas SET saldo = ?, DataAtualizacao = ? WHERE numero = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = (PreparedStatement) conn.prepareStatement(sql);

			pstm.setBigDecimal(1, contato.getSaldo());
			pstm.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
			pstm.setInt(3, contato.getNumero());

			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Metodo que atualiza o Saldo e a Data de Atualiza��o. Gera contato com o banco
	 * de dados atraves do numero da conta.
	 * 
	 * @param contato = Conta Poupan�a.
	 */
	public void updateContaPoupanca(ContaPoupanca contato) {

		String sql = "UPDATE contas SET saldo = ?, DataAtualizacao = ? WHERE numero = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = (PreparedStatement) conn.prepareStatement(sql);

			pstm.setBigDecimal(1, contato.getSaldo());
			pstm.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
			pstm.setInt(3, contato.getNumero());

			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Deleta uma conta atrav�s do numero.
	 * 
	 * @param numero
	 */
	public void deleteByID(int numero) {

		String sql = "DELETE FROM contas WHERE numero = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = (PreparedStatement) conn.prepareStatement(sql);

			pstm.setInt(1, numero);

			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Metodo que devolve uma Conta Corrente especifica.
	 * 
	 * @param numero
	 * @return Retorna um List de Conta Corrente com a conta do numero inserido.
	 */
	public List<ContaCorrente> getContatoCorrente(int numero) {

		String sql = "SELECT * FROM contas";

		List<ContaCorrente> contatos = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstm = null;
		// Classe que vai recuperar os dados do banco. ***SELECT***
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();

			while (rset.next()) {
				ContaCorrente contato = new ContaCorrente();

				// Recuperar o Tipo de conta
				contato.setTipoDeConta(rset.getString("TipoDeConta"));
				// Recuperar o Numero
				contato.setNumero(rset.getInt("Numero"));
				// Recuperar o Nome
				contato.setNome(rset.getString("Nome"));
				// Recuperar o saldo
				contato.setSaldo(rset.getBigDecimal("Saldo"));
				// Recuperar a data de cadastro
//				pstm.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
				contato.setDataCadastro(rset.getDate("DataCadastro").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
				// Recuperar a data de Atualizacao
				contato.setDataAtualizacao(rset.getTimestamp("DataAtualizacao").toLocalDateTime());
				// Recuperar o Limite
				contato.setLimite(rset.getBigDecimal("Limite"));

				if (contato.getNumeroBoolean(numero)) {
					contatos.add((ContaCorrente) contato);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return contatos;
	}

	/**
	 * Metodo que devolve uma Conta Poupan�a especifica.
	 * 
	 * @param numero
	 * @return Retorna um List de Conta Poupan�a com a conta do numero inserido.
	 */
	public List<ContaPoupanca> getContatoPoupanca(int numero) {

		String sql = "SELECT * FROM contas";

		List<ContaPoupanca> contatos = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstm = null;
		// Classe que vai recuperar os dados do banco. ***SELECT***
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();

			while (rset.next()) {
				ContaPoupanca contato = new ContaPoupanca();

				// Recuperar o Tipo de conta
				contato.setTipoDeConta(rset.getString("TipoDeConta"));
				// Recuperar o Numero
				contato.setNumero(rset.getInt("Numero"));
				// Recuperar o Nome
				contato.setNome(rset.getString("Nome"));
				// Recuperar o saldo
				contato.setSaldo(rset.getBigDecimal("Saldo"));
				// Recuperar a data de cadastro
				contato.setDataCadastro(rset.getDate("DataCadastro").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
				// Recuperar a data de Atualizacao
				contato.setDataAtualizacao(rset.getTimestamp("DataAtualizacao").toLocalDateTime());
				// Recuperar o Limite
				contato.setLimite(rset.getBigDecimal("Limite"));

				if (contato.getNumeroBoolean(numero)) {
					contatos.add((ContaPoupanca) contato);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return contatos;
	}

	/**
	 * Metodo que gera um novo numero aleatorio != dos numeros do banco de dados.
	 * 
	 * @return Retorna um numero Integer de nove digitos.
	 */
	public Integer geraNumero() {

		String sql = "SELECT * FROM contas";

		HashSet<Integer> contatos = new HashSet<Integer>();
		List<Integer> contatos2 = new ArrayList<Integer>();

		Random random = new Random();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = (PreparedStatement) conn.prepareStatement(sql);

			rset = pstm.executeQuery();

			while (rset.next()) {
				ContaCorrente contato = new ContaCorrente();
				// Recuperar o Numero
				contato.setNumero(rset.getInt("Numero"));
				// Adiciona o numero na lista
				contatos.add(contato.getNumero());
			}
			for (int i = 0; i < 1; i++) {
				int add = (int) random.nextInt(999999999); // Int que estou adicionando
				while (contatos.contains(add)) { // While o int for igual a algum numero da lista contatos
					add = (int) random.nextInt(999999999); // gera um novo numero, s� que desta vez ser� um numero
															// diferente da lista
				}
				contatos2.add(add);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return contatos2.get(0);
	}

	/**
	 * Metodo que devolve o Tipo de Conta do numero inserido.
	 * 
	 * @param numero
	 * @return Retorna um List Integer com o Tipo de Conta do numero na posi��o 0.
	 *         Tipos de Conta= 001(Conta Corrente), 013(Conta Poupan�a) e 35(Tipo de
	 *         Conta n�o encontrada))
	 */	
	public Integer getTipoDeConta(int numero) {
		


		String sql = "SELECT * FROM contas";

		List<Integer> contatos = new ArrayList<Integer>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = (PreparedStatement) conn.prepareStatement(sql);

			rset = pstm.executeQuery();

			while (rset.next()) {
				ContaCorrente contato = new ContaCorrente();

				contato.setTipoDeConta(rset.getString("TipoDeConta"));
				contato.setNumero(rset.getInt("Numero"));

				if (contato.getNumeroBoolean(numero)) {
					if (contato.getTipoDeConta().equals("Conta Corrente")) {
						contatos.add(0, 001);
					} else if (contato.getTipoDeConta().equals("Conta Poupan�a")) {
						contatos.add(0, 013);
					}
				} else {
					contatos.add(35);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return contatos.get(0);
	}
}
