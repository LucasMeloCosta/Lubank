package br.com.lubank.banco.aplicacao.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import br.com.lubank.banco.dao.ContaDAO;
import br.com.lubank.banco.modelo.ContaPoupanca;

public class CadastroContaPoupanca {

	private JFrame frame;
	ImageIcon logoLubank = new ImageIcon(getClass().getResource("LogoLubank.png"));
	private JTextField textNome;
	private JTextField textLimite;
	private JTextField textSaldo;

	/**
	 * Launch the application.
	 */
	public static void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroContaPoupanca window = new CadastroContaPoupanca();
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
	public CadastroContaPoupanca() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Cadatro Conta Corrente");
		frame.getContentPane().setBackground(new Color(94, 9, 139));
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 450, 368);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblLogo = new JLabel(logoLubank);
		lblLogo.setBounds(0, 0, 434, 57);
		frame.getContentPane().add(lblLogo);
		
//*************************************************************************************************************************************************				
				JLabel lblNome = new JLabel("Nome completo:");
				lblNome.setIcon(null);
				lblNome.setForeground(new Color(255, 255, 255));
				lblNome.setBounds(10, 69, 100, 16);
				frame.getContentPane().add(lblNome);
				
				textNome = new JTextField();
				textNome.setBounds(12, 97, 345, 20);
				frame.getContentPane().add(textNome);
				textNome.setColumns(10);
				
				JLabel lblLimite = new JLabel("Limite da conta:");
				lblLimite.setForeground(Color.WHITE);
				lblLimite.setBounds(10, 129, 100, 16);
				frame.getContentPane().add(lblLimite);
				
				textLimite = new JTextField();
				textLimite.setColumns(10);
				textLimite.setBounds(12, 157, 345, 20);
				frame.getContentPane().add(textLimite);
				
				JLabel lblSaldo = new JLabel("Saldo da conta:");
				lblSaldo.setForeground(Color.WHITE);
				lblSaldo.setBounds(10, 189, 100, 16);
				frame.getContentPane().add(lblSaldo);
				
				textSaldo = new JTextField();
				textSaldo.setColumns(10);
				textSaldo.setBounds(12, 217, 345, 20);
				frame.getContentPane().add(textSaldo);
				
				JLabel lblObservacao = new JLabel("Obs: Saldo deve ser menor que o limite");
				lblObservacao.setForeground(new Color(255, 255, 255));
				lblObservacao.setBounds(136, 238, 221, 16);
				frame.getContentPane().add(lblObservacao);
//*************************************************************************************************************************************************	
				JButton btnCadastrarConta = new JButton("Cadastrar");
				btnCadastrarConta.setBounds(269, 280, 153, 36);
				btnCadastrarConta.setForeground(new Color(255, 255, 255));
				btnCadastrarConta.setFont(new Font("Tahoma", Font.BOLD, 16));
				btnCadastrarConta.setBorder(new LineBorder(new Color(0, 0, 0)));
				btnCadastrarConta.setBackground(new Color(112, 128, 144));
				btnCadastrarConta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						ContaDAO contaDao = new ContaDAO();
						ContaPoupanca minhaContaPoupanca = new ContaPoupanca();
						
						String nome = textNome.getText();
						
						int numero = contaDao.geraNumero();
						
						BigDecimal limite = new BigDecimal(textLimite.getText());
						
						BigDecimal saldo = new BigDecimal(textSaldo.getText());
						
						if (limite.compareTo(saldo) == -1) {
							JOptionPane.showMessageDialog(null, "Limite excedido, conta n�o cadastrada.", "Erro", JOptionPane.ERROR_MESSAGE);
						
						} else {
							minhaContaPoupanca.inserirDados(nome, numero, limite, saldo);
							contaDao.saveContaPoupanca(minhaContaPoupanca);	
							JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso!" + "\nO numero da sua conta �: " + numero, "Parab�ns!", 0,logoLubank);
							frame.dispose();
							TelaPrincipal.newScreen();
						}
					}
				});
				frame.getContentPane().add(btnCadastrarConta);
//*************************************************************************************************************************************************		
		
	}
}
