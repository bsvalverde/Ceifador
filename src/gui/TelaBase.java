package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class TelaBase extends JFrame {

	private JPanel contentPane;

	public TelaBase() {
		this.setTitle("Ceifador");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 624);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ceifador");
		lblNewLabel.setFont(new Font("Segoe Script", Font.PLAIN, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(100, 21, 400, 40);
		contentPane.add(lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 72, 684, 513);
		contentPane.add(tabbedPane);
		
		JPanel panel = new TelaConsultas(tabbedPane);
		tabbedPane.addTab("Consultas", null, panel, null);
		
		JPanel panel_1 = new TelaDB(tabbedPane);
		tabbedPane.addTab("Base de Dados", null, panel_1, null);
		
		JButton btnNewButton = new JButton("Configurar");
		btnNewButton.setBounds(574, 11, 100, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new BtnConfigListener(tabbedPane));

	}
	
	private class BtnConfigListener implements ActionListener {
		
		private JTabbedPane panel;
		
		public BtnConfigListener(JTabbedPane motherpane){
			panel = motherpane;
		}
		
		public void actionPerformed(ActionEvent event){
			TelaConfig frame = new TelaConfig(panel);
			frame.setVisible(true);
		}
		
	}
	
}
