package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dbmanager.DBConfig;

public class TelaConfig extends JFrame {

	private JPanel contentPane;
	private JTextField txtHost;
	private JTextField txtPort;
	private JTextField txtUser;
	private JPasswordField pwdPassword;
	private JTextField txtBd;
	private JTabbedPane mother;

	/**
	 * Create the frame.
	 */
	public TelaConfig(JTabbedPane motherpane) {
		mother = motherpane;
		
		this.setTitle("Configurações");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 180, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConfiguraes = new JLabel("Configura\u00E7\u00F5es");
		lblConfiguraes.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfiguraes.setBounds(10, 11, 160, 14);
		contentPane.add(lblConfiguraes);
		
		JLabel lblServername = new JLabel("Host");
		lblServername.setBounds(10, 36, 46, 14);
		contentPane.add(lblServername);
		
		txtHost = new JTextField();
		txtHost.setText(DBConfig.getHost());
		txtHost.setBounds(66, 33, 86, 20);
		contentPane.add(txtHost);
		txtHost.setColumns(10);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setBounds(10, 61, 46, 14);
		contentPane.add(lblPort);
		
		txtPort = new JTextField();
		txtPort.setText(DBConfig.getPort());
		txtPort.setBounds(66, 58, 86, 20);
		contentPane.add(txtPort);
		txtPort.setColumns(10);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio");
		lblUsurio.setBounds(10, 86, 46, 14);
		contentPane.add(lblUsurio);
		
		txtUser = new JTextField();
		txtUser.setText(DBConfig.getUser());
		txtUser.setBounds(66, 83, 86, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(10, 111, 46, 14);
		contentPane.add(lblSenha);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setText(DBConfig.getPassword());
		pwdPassword.setBounds(66, 108, 86, 20);
		contentPane.add(pwdPassword);
		
		JLabel lblBd = new JLabel("BD");
		lblBd.setBounds(10, 136, 46, 14);
		contentPane.add(lblBd);
		
		txtBd = new JTextField();
		txtBd.setText(DBConfig.getDBName());
		txtBd.setBounds(66, 133, 86, 20);
		contentPane.add(txtBd);
		txtBd.setColumns(10);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.setBounds(40, 161, 90, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new BtnSaveListener(this));
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBounds(40, 186, 90, 23);
		contentPane.add(btnFechar);
		btnFechar.addActionListener(new BtnCloseListener(this));
	}
	
	private class BtnSaveListener implements ActionListener {

		private JFrame frame;
		
		public BtnSaveListener(JFrame framepane){
			frame = framepane;
		}
		public void actionPerformed(ActionEvent event){
			DBConfig.setHost(txtHost.getText());
			DBConfig.setPort(txtPort.getText());
			DBConfig.setUser(txtUser.getText());
			DBConfig.setPassword(String.valueOf(pwdPassword.getPassword()));
			DBConfig.setDbName(txtBd.getText());
			int i = mother.getSelectedIndex();
			mother.remove(1);
			mother.insertTab("Base de Dados", null, new TelaDB(mother), null, 1);
			mother.setSelectedIndex(i);
			frame.setVisible(false);
			
		}
		
	}
	
	private class BtnCloseListener implements ActionListener {
		
		private JFrame frame;
		
		public BtnCloseListener(JFrame framepane){
			frame = framepane;
		}
		
		public void actionPerformed(ActionEvent event){
			frame.setVisible(false);
		}
		
	}
	
}
