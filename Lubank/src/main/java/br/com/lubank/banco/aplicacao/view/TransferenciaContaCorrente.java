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

import br.com.lubank.banco.modelo.ContaCorrente;

public class TransferenciaContaCorrente {

	private JFrame frame;
	ImageIcon logoLubank = new ImageIcon(getClass().getResource("LogoLubank.png"));
	private JTextField textValor;
	private JTextField textNumeroContaDeDestino;
	/**
	 * Launch the application.
	 * @param numero 
	 */
	public void newScreen(int numero) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransferenciaContaCorrente window = new TransferenciaContaCorrente(numero);
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
	public TransferenciaContaCorrente(int numeroDaConta) {
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
		
		JLabel lblValorATransferir = new JLabel("Valor a transferir: ");
		lblValorATransferir.setBounds(10, 97, 103, 16);
		lblValorATransferir.setForeground(Color.WHITE);
		frame.getContentPane().add(lblValorATransferir);
		
		
		textValor = new JTextField();
		textValor.setBounds(10, 125, 207, 20);
		frame.getContentPane().add(textValor);
		textValor.setColumns(10);
		
		JLabel lblNumeroContaDestino = new JLabel("Numero da conta de destino:");
		lblNumeroContaDestino.setForeground(Color.WHITE);
		lblNumeroContaDestino.setBounds(12, 157, 162, 16);
		frame.getContentPane().add(lblNumeroContaDestino);
		
		textNumeroContaDeDestino = new JTextField();
		textNumeroContaDeDestino.setColumns(10);
		textNumeroContaDeDestino.setBounds(10, 185, 207, 20);
		frame.getContentPane().add(textNumeroContaDeDestino);
// *************************************************************************************************************************************************		
		JButton btnTransferir = new JButton("Transferir");
		btnTransferir.setBounds(276, 213, 153, 36);
		btnTransferir.setForeground(Color.WHITE);
		btnTransferir.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTransferir.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(153, 50, 204), new Color(153, 50, 204),
						new Color(153, 50, 204), new Color(153, 50, 204)));
		btnTransferir.setBackground(new Color(153, 50, 204));
		btnTransferir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				ContaCorrente minhaContaCorrente = new ContaCorrente();
				BigDecimal valor = new BigDecimal(textValor.getText());
				int contaDestino = Integer.parseInt(textNumeroContaDeDestino.getText());
				if (minhaContaCorrente.transfereCompara(numeroDaConta, contaDestino,valor) == 1) {
					minhaContaCorrente.transfereString(numeroDaConta, contaDestino,valor);
					JOptionPane.showMessageDialog(null, "Dinheiro sacado!", "Parab�ns", 0, logoLubank);
					frame.dispose();
					TelaMenu telaMenu = new TelaMenu(numeroDaConta);
					telaMenu.newScreen(numeroDaConta);
				
				}else if (minhaContaCorrente.transfereCompara(numeroDaConta, contaDestino,valor) == 0){
					JOptionPane.showMessageDialog(null, minhaContaCorrente.transfereString(numeroDaConta, contaDestino,valor), "Erro", JOptionPane.ERROR_MESSAGE);
					
				}else if (minhaContaCorrente.transfereCompara(numeroDaConta, contaDestino,valor) == -1) {
					JOptionPane.showMessageDialog(null, minhaContaCorrente.transfereString(numeroDaConta, contaDestino,valor), "Erro", JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, minhaContaCorrente.transfereString(numeroDaConta, contaDestino,valor), "Erro", JOptionPane.ERROR_MESSAGE);
				}
					
			}
		});
		frame.getContentPane().add(btnTransferir);
// *************************************************************************************************************************************************
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(276, 261, 153, 36);
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVoltar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(153, 50, 204), new Color(153, 50, 204),
						new Color(153, 50, 204), new Color(153, 50, 204)));
		btnVoltar.setBackground(new Color(153, 50, 204));
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
