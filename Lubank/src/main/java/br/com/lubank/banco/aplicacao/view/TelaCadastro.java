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

public class TelaCadastro {

	private JFrame frame;
	ImageIcon logoLubank = new ImageIcon(getClass().getResource("LogoLubank.png"));

	/**
	 * Launch the application.
	 */
	public static void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro window = new TelaCadastro();
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
	public TelaCadastro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Cadastrar");
		frame.getContentPane().setBackground(new Color(94, 9, 139));
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblLogo = new JLabel(logoLubank);
		lblLogo.setBounds(0, 0, 434, 57);
		frame.getContentPane().add(lblLogo);
//*************************************************************************************************************************************************
		JButton btnContaCorrente = new JButton("Conta Corrente");
		btnContaCorrente.setForeground(new Color(255, 255, 255));
		btnContaCorrente.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnContaCorrente.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(153, 50, 204),
				new Color(153, 50, 204), new Color(153, 50, 204), new Color(153, 50, 204)));
		btnContaCorrente.setBackground(new Color(153, 50, 204));
		btnContaCorrente.setBounds(10, 101, 153, 36);
		btnContaCorrente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CadastroContaCorrente.newScreen();				
			}
		});
		frame.getContentPane().add(btnContaCorrente);
//*************************************************************************************************************************************************
		JButton btnContaPoupanca = new JButton("Conta Poupan\u00E7a");
		btnContaPoupanca.setForeground(Color.WHITE);
		btnContaPoupanca.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnContaPoupanca
				.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(153, 50, 204), new Color(153, 50, 204),

						new Color(153, 50, 204), new Color(153, 50, 204)));
		btnContaPoupanca.setBackground(new Color(153, 50, 204));
		btnContaPoupanca.setBounds(10, 150, 153, 36);
		btnContaPoupanca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CadastroContaPoupanca.newScreen();				
			}
		});
		frame.getContentPane().add(btnContaPoupanca);
//*************************************************************************************************************************************************
		JButton btnQualEscolher = new JButton("Qual escolher?");
		btnQualEscolher.setForeground(Color.WHITE);
		btnQualEscolher.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnQualEscolher
				.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(153, 50, 204), new Color(153, 50, 204),

						new Color(153, 50, 204), new Color(153, 50, 204)));
		btnQualEscolher.setBackground(new Color(153, 50, 204));
		btnQualEscolher.setBounds(10, 199, 153, 36);
		btnQualEscolher.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "\nConta Corrente as taxas de saque e transfer�ncias s�o significativamente baixas." + "\nConta Poupan�a seu dinheiro rende por minuto 0,5% mas as taxas de saque e transfer�ncias s�o significativamente altas.","Diferen�as", 0, logoLubank);
		}
	});
		frame.getContentPane().add(btnQualEscolher);
//*************************************************************************************************************************************************
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVoltar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(153, 50, 204), new Color(153, 50, 204),

				new Color(153, 50, 204), new Color(153, 50, 204)));
		btnVoltar.setBackground(new Color(153, 50, 204));
		btnVoltar.setBounds(269, 199, 153, 36);
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
