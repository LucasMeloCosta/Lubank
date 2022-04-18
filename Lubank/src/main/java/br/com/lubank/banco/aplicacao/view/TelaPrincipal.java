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
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class TelaPrincipal {

	private JFrame frmBemVindo;
	ImageIcon logoLubank = new ImageIcon(getClass().getResource("LogoLubank.png"));

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frmBemVindo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frmBemVindo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBemVindo = new JFrame();
		frmBemVindo.getContentPane().setBackground(new Color(94, 9, 139));
		frmBemVindo.getContentPane().setLayout(null);
		frmBemVindo.setTitle("Bem Vindo!");
		frmBemVindo.setBounds(100, 100, 450, 300);
		frmBemVindo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblLogo = new JLabel(logoLubank);
		lblLogo.setBounds(0, 0, 434, 57);
		frmBemVindo.getContentPane().add(lblLogo);
		
//*************************************************************************************************************************************************
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setForeground(new Color(255, 255, 255));
		btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEntrar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(153, 50, 204), new Color(153, 50, 204),
				new Color(153, 50, 204), new Color(153, 50, 204)));
		btnEntrar.setBackground(new Color(153, 50, 204));
		btnEntrar.setBounds(12, 115, 153, 36);
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmBemVindo.dispose();
				TelaEntrar.newScreen();
			}
		});
		frmBemVindo.getContentPane().add(btnEntrar);
//*************************************************************************************************************************************************
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCadastrar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(153, 50, 204),
				new Color(153, 50, 204), new Color(153, 50, 204), new Color(153, 50, 204)));
		btnCadastrar.setBackground(new Color(153, 50, 204));
		btnCadastrar.setBounds(12, 163, 153, 36);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmBemVindo.dispose();
				TelaCadastro.newScreen();
			}
		});
		frmBemVindo.getContentPane().add(btnCadastrar);
//*************************************************************************************************************************************************
	}
	
}
