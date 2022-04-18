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

public class DepositoContaPoupanca {

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
					DepositoContaPoupanca window = new DepositoContaPoupanca(numero);
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
	public DepositoContaPoupanca(int numeroDaConta) {
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
		
		JLabel lblValorADepositar = new JLabel("Valor a depositar: ");
		lblValorADepositar.setForeground(Color.WHITE);
		lblValorADepositar.setBounds(10, 97, 103, 16);
		frame.getContentPane().add(lblValorADepositar);
		
		
		textValor = new JTextField();
		textValor.setBounds(12, 125, 207, 20);
		frame.getContentPane().add(textValor);
		textValor.setColumns(10);
		
// *************************************************************************************************************************************************		
		JButton btnDepositar = new JButton("Depositar");
		btnDepositar.setForeground(Color.WHITE);
		btnDepositar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDepositar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(153, 50, 204), new Color(153, 50, 204),
						new Color(153, 50, 204), new Color(153, 50, 204)));
		btnDepositar.setBackground(new Color(153, 50, 204));
		btnDepositar.setBounds(276, 213, 153, 36);
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				ContaPoupanca minhaContaPoupanca = new ContaPoupanca();
				BigDecimal valor = new BigDecimal(textValor.getText());
				
				if (minhaContaPoupanca.verificaLimite(numeroDaConta, valor) == true) {
					minhaContaPoupanca.deposita(numeroDaConta, valor);
					JOptionPane.showMessageDialog(null, "Deposito concluido!", "Parab�ns", 0, logoLubank);
					frame.dispose();
					TelaMenu telaMenu = new TelaMenu(numeroDaConta);
					telaMenu.newScreen(numeroDaConta);
				
				}else {
					JOptionPane.showMessageDialog(null, "Limite ultrapassado!", "Erro", JOptionPane.ERROR_MESSAGE);
					
				}
					
			}
		});
		frame.getContentPane().add(btnDepositar);
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
