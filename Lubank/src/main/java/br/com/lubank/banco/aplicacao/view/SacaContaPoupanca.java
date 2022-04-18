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
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import br.com.lubank.banco.modelo.ContaPoupanca;

public class SacaContaPoupanca {

	private JFrame frame;
	ImageIcon logoLubank = new ImageIcon(getClass().getResource("LogoLubank.png"));
	private JTextField textValor;
	/**
	 * Launch the application.
	 * @param numero 
	 */
	public void newScreen(int numero) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SacaContaPoupanca window = new SacaContaPoupanca(numero);
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
	public SacaContaPoupanca(int numeroDaConta) {
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
		
		JLabel lblNumeroConta = new JLabel("Conta: " + numeroDaConta);
		lblNumeroConta.setBounds(293, 56, 156, 20);
		lblNumeroConta.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNumeroConta.setForeground(new Color(255, 255, 255));
		frame.getContentPane().add(lblNumeroConta);
		
		JLabel lblValorASacar = new JLabel("Valor a sacar: ");
		lblValorASacar.setForeground(Color.WHITE);
		lblValorASacar.setBounds(10, 97, 103, 16);
		frame.getContentPane().add(lblValorASacar);
		
		
		textValor = new JTextField();
		textValor.setBounds(12, 125, 207, 20);
		frame.getContentPane().add(textValor);
		textValor.setColumns(10);
		
// *************************************************************************************************************************************************		
		JButton btnSacar = new JButton("Sacar");
		btnSacar.setForeground(Color.WHITE);
		btnSacar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSacar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(153, 50, 204), new Color(153, 50, 204),
						new Color(153, 50, 204), new Color(153, 50, 204)));
		btnSacar.setBackground(new Color(153, 50, 204));
		btnSacar.setBounds(276, 213, 153, 36);
		btnSacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				ContaPoupanca minhaContaPoupanca = new ContaPoupanca();
				BigDecimal valor = new BigDecimal(textValor.getText());
				
				if (minhaContaPoupanca.saca(numeroDaConta, valor) == true) {
					JOptionPane.showMessageDialog(null, "Dinheiro sacado!", "Parab�ns", 0, logoLubank);
					frame.dispose();
					TelaMenu telaMenu = new TelaMenu(numeroDaConta);
					telaMenu.newScreen(numeroDaConta);
				
				}else {
					JOptionPane.showMessageDialog(null, "Saldo Insuficiente!", "Erro", JOptionPane.ERROR_MESSAGE);
					
				}
					
			}
		});
		frame.getContentPane().add(btnSacar);
// *************************************************************************************************************************************************
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVoltar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(153, 50, 204), new Color(153, 50, 204),
						new Color(153, 50, 204), new Color(153, 50, 204)));
		btnVoltar.setBackground(new Color(153, 50, 204));
		btnVoltar.setBounds(276, 261, 153, 36);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				TelaMenu telaMenu = new TelaMenu(numeroDaConta);
				telaMenu.newScreen(numeroDaConta);
			}
		});
		frame.getContentPane().add(btnVoltar);
// *************************************************************************************************************************************************
	}
}
