package br.com.testes.rafael.telas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import br.com.testes.rafael.TesteLerArquivoTxt;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class JifInserirTxt extends JInternalFrame {
	private JTextField JtfCaminho;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JifInserirTxt frame = new JifInserirTxt();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JifInserirTxt() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 450, 300);
		
		JLabel lblCaminhoDoTxt = new JLabel("Caminho do Txt:");
		
		JtfCaminho = new JTextField();
		JtfCaminho.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JtfCaminho.setText("");
			}
		});
		JtfCaminho.setText("Ex: C:\\Users\\Rafa\\Documents\\31-01-2015.txt");
		
		JButton btnIr = new JButton("Ir");
		btnIr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome = JtfCaminho.getText();
				String dia = null;
				String mes = null;
				String ano = null;
				String hora = null;
				String matricula = null;
				String data = null;
				int cont = 0;
				try {
					TesteLerArquivoTxt.LerArquivo(nome, dia, mes, ano, hora, matricula, data, cont);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(157)
							.addComponent(lblCaminhoDoTxt))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(53)
							.addComponent(JtfCaminho, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(146)
							.addComponent(btnIr, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(78, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCaminhoDoTxt)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(JtfCaminho, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnIr)
					.addContainerGap(178, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
