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
import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import br.com.lubank.banco.dao.ContaDAO;

public class TelaEntrar {

	private JFrame frame;
	ImageIcon logoLubank = new ImageIcon(getClass().getResource("LogoLubank.png"));
	private JPasswordField passwordNumero;

	/**
	 * Launch the application.
	 */
	public static void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEntrar window = new TelaEntrar();
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
	public TelaEntrar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Cadastrar");
		frame.getContentPane().setBackground(new Color(94, 9, 139));
		frame.setBounds(100, 100, 477, 357);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblLogo = new JLabel(logoLubank);
		lblLogo.setBounds(0, 0, 434, 57);
		frame.getContentPane().add(lblLogo);

		JLabel lblNumeroDaConta = new JLabel("Numero da conta: ");
		lblNumeroDaConta.setForeground(new Color(255, 255, 255));
		lblNumeroDaConta.setBounds(12, 95, 103, 16);
		frame.getContentPane().add(lblNumeroDaConta);

		passwordNumero = new JPasswordField();
		passwordNumero.setBounds(12, 124, 203, 20);
		frame.getContentPane().add(passwordNumero);
		
//*************************************************************************************************************************************************
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setForeground(Color.WHITE);
		btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEntrar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(153, 50, 204), new Color(153, 50, 204),
				new Color(153, 50, 204), new Color(153, 50, 204)));
		btnEntrar.setBackground(new Color(153, 50, 204));
		btnEntrar.setBounds(281, 212, 153, 36);
		btnEntrar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				ContaDAO contaDao = new ContaDAO();
				
				int numero = Integer.parseInt(passwordNumero.getText());
				
				if (contaDao.getTipoDeConta(numero) == 001) {
					frame.dispose();
					TelaMenu telaMenu = new TelaMenu(numero);
					telaMenu.newScreen(numero);
				} else {
					if (contaDao.getTipoDeConta(numero) == 013) {
						frame.dispose();
						TelaMenu telaMenu = new TelaMenu(numero);
						telaMenu.newScreen(numero);
					} else {
						JOptionPane.showMessageDialog(null, "Conta n�o cadastrada!", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		frame.getContentPane().add(btnEntrar);
// *************************************************************************************************************************************************
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
// *************************************************************************************************************************************************
	}
}
