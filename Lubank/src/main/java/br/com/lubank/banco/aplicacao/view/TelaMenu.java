package br.com.lubank.banco.aplicacao.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import br.com.lubank.banco.dao.ContaDAO;
import br.com.lubank.banco.modelo.ContaCorrente;
import br.com.lubank.banco.modelo.ContaPoupanca;

public class TelaMenu {

	private JFrame frame;
	ImageIcon logoLubank = new ImageIcon(getClass().getResource("LogoLubank.png"));

	/**
	 * Launch the application.
	 * 
	 * @param numero
	 */
	public void newScreen(int numero) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMenu window = new TelaMenu(numero);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaMenu(int numeroDaConta) {
		initialize(numeroDaConta);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int numeroDaConta) {
		frame = new JFrame();
		frame.setTitle("Cadastrar");
		frame.getContentPane().setBackground(new Color(94, 9, 139));
		frame.setBounds(100, 100, 477, 357);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblLogo = new JLabel(logoLubank);
		lblLogo.setBounds(0, 0, 434, 57);
		frame.getContentPane().add(lblLogo);

		JLabel lblNewLabel = new JLabel("Conta: " + numeroDaConta);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(293, 56, 156, 20);
		frame.getContentPane().add(lblNewLabel);

//*************************************************************************************************************************************************
		JButton btnDadosDaConta = new JButton("Dados da conta");
		btnDadosDaConta.setForeground(Color.WHITE);
		btnDadosDaConta.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDadosDaConta.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(153, 50, 204),
				new Color(153, 50, 204), new Color(153, 50, 204), new Color(153, 50, 204)));
		btnDadosDaConta.setBackground(new Color(153, 50, 204));
		btnDadosDaConta.setBounds(10, 69, 199, 36);
		btnDadosDaConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContaDAO contaDao = new ContaDAO();
				ContaCorrente minhaContaCorrente = new ContaCorrente();
				ContaPoupanca minhaContaPoupanca = new ContaPoupanca();

				if (contaDao.getTipoDeConta(numeroDaConta) == 001) {
					JOptionPane.showMessageDialog(null, minhaContaCorrente.mostrarDadosString(numeroDaConta), "Dados",
							0, logoLubank);
				} else {
					if (contaDao.getTipoDeConta(numeroDaConta) == 013) {
						JOptionPane.showMessageDialog(null, minhaContaPoupanca.mostrarDadosString(numeroDaConta),
								"Dados", 0, logoLubank);
					} else {
						JOptionPane.showMessageDialog(null, "Conta n�o cadastrada!", "Erro", JOptionPane.ERROR_MESSAGE);
						frame.dispose();
						TelaPrincipal.newScreen();
					}
				}
			}
		});
		frame.getContentPane().add(btnDadosDaConta);
//*************************************************************************************************************************************************
		JButton btnDeposito = new JButton("Deposito");
		btnDeposito.setForeground(Color.WHITE);
		btnDeposito.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDeposito.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(153, 50, 204), new Color(153, 50, 204),
				new Color(153, 50, 204), new Color(153, 50, 204)));
		btnDeposito.setBackground(new Color(153, 50, 204));
		btnDeposito.setBounds(10, 117, 199, 36);
		btnDeposito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContaDAO contaDao = new ContaDAO();

				if (contaDao.getTipoDeConta(numeroDaConta).equals(001)) {
					frame.dispose();
					DepositoContaCorrente depositoContaCorrente = new DepositoContaCorrente(numeroDaConta);
					depositoContaCorrente.newScreen(numeroDaConta);

				} else if (contaDao.getTipoDeConta(numeroDaConta).equals(013)) {
					frame.dispose();
					DepositoContaPoupanca depositoContaPoupanca = new DepositoContaPoupanca(numeroDaConta);
					depositoContaPoupanca.newScreen(numeroDaConta);

				} else {
					JOptionPane.showMessageDialog(null, "Conta n�o cadastrada!", "Erro", JOptionPane.ERROR_MESSAGE);

				}

			}
		});
		frame.getContentPane().add(btnDeposito);
//*************************************************************************************************************************************************		
		JButton btnSaldo = new JButton("Saldo");
		btnSaldo.setForeground(Color.WHITE);
		btnSaldo.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSaldo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(153, 50, 204), new Color(153, 50, 204),
				new Color(153, 50, 204), new Color(153, 50, 204)));
		btnSaldo.setBackground(new Color(153, 50, 204));
		btnSaldo.setBounds(10, 165, 199, 36);
		btnSaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContaDAO contaDao = new ContaDAO();
				ContaCorrente minhaContaCorrente = new ContaCorrente();
				ContaPoupanca minhaContaPoupanca = new ContaPoupanca();

				if (contaDao.getTipoDeConta(numeroDaConta) == 001) {
					JOptionPane.showMessageDialog(null, minhaContaCorrente.mostrarSaldoString(numeroDaConta), "Dados",
							0, logoLubank);
				} else {
					if (contaDao.getTipoDeConta(numeroDaConta) == 013) {
						JOptionPane.showMessageDialog(null, minhaContaPoupanca.mostrarSaldoString(numeroDaConta),
								"Dados", 0, logoLubank);
					} else {
						JOptionPane.showMessageDialog(null, "Conta n�o cadastrada!", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		frame.getContentPane().add(btnSaldo);
//*************************************************************************************************************************************************
		JButton btnSacar = new JButton("Sacar");
		btnSacar.setForeground(Color.WHITE);
		btnSacar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSacar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(153, 50, 204), new Color(153, 50, 204),
				new Color(153, 50, 204), new Color(153, 50, 204)));
		btnSacar.setBackground(new Color(153, 50, 204));
		btnSacar.setBounds(10, 213, 199, 36);
		btnSacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContaDAO contaDao = new ContaDAO();

				if (contaDao.getTipoDeConta(numeroDaConta).equals(001)) {
					frame.dispose();
					SacaContaCorrente sacaContaCorrente = new SacaContaCorrente(numeroDaConta);
					sacaContaCorrente.newScreen(numeroDaConta);

				} else if (contaDao.getTipoDeConta(numeroDaConta).equals(013)) {
					frame.dispose();
					SacaContaPoupanca sacaContaPoupanca = new SacaContaPoupanca(numeroDaConta);
					sacaContaPoupanca.newScreen(numeroDaConta);

				} else {
					JOptionPane.showMessageDialog(null, "Conta n�o cadastrada!", "Erro", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		frame.getContentPane().add(btnSacar);
//*************************************************************************************************************************************************		
		JButton btnTransferencia = new JButton("Transfer\u00EAncia banc\u00E1ria");
		btnTransferencia.setForeground(Color.WHITE);
		btnTransferencia.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTransferencia.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(153, 50, 204),
				new Color(153, 50, 204), new Color(153, 50, 204), new Color(153, 50, 204)));
		btnTransferencia.setBackground(new Color(153, 50, 204));
		btnTransferencia.setBounds(10, 261, 199, 36);
		btnTransferencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContaDAO contaDao = new ContaDAO();

				if (contaDao.getTipoDeConta(numeroDaConta).equals(001)) {
					frame.dispose();
					TransferenciaContaCorrente transferenciaContaCorrente = new TransferenciaContaCorrente(numeroDaConta);
					transferenciaContaCorrente.newScreen(numeroDaConta);

				} else if (contaDao.getTipoDeConta(numeroDaConta).equals(013)) {
					frame.dispose();
					TransferenciaContaPoupanca transferenciaContaPoupanca = new TransferenciaContaPoupanca(numeroDaConta);
					transferenciaContaPoupanca.newScreen(numeroDaConta);

				} else {
					JOptionPane.showMessageDialog(null, "Conta n�o cadastrada!", "Erro", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		frame.getContentPane().add(btnTransferencia);
//*************************************************************************************************************************************************
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVoltar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(153, 50, 204), new Color(153, 50, 204),
				new Color(153, 50, 204), new Color(153, 50, 204)));
		btnVoltar.setBackground(new Color(153, 50, 204));
		btnVoltar.setBounds(281, 261, 153, 36);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				TelaPrincipal.newScreen();
			}
		});
		frame.getContentPane().add(btnVoltar);
//*************************************************************************************************************************************************

	}

}
