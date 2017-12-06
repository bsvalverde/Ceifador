package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TelaFiltro extends JFrame {

	private TelaConsultas mother;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 */
	public TelaFiltro(TelaConsultas motherpane) {
		mother = motherpane;
		this.setTitle("Filtro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 240, 130);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAnoIncio = new JLabel("Ano in\u00EDcio:");
		lblAnoIncio.setBounds(10, 11, 83, 14);
		contentPane.add(lblAnoIncio);
		
		JLabel lblAnoFim = new JLabel("Ano fim:");
		lblAnoFim.setBounds(10, 36, 83, 14);
		contentPane.add(lblAnoFim);
		
		textField = new JTextField();
		textField.setBounds(103, 8, 111, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText("" + (Calendar.getInstance().get(Calendar.YEAR) - 3));
		
		textField_1 = new JTextField();
		textField_1.setBounds(103, 33, 111, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText("" + Calendar.getInstance().get(Calendar.YEAR));
		
		JButton btnAplicar = new JButton("Aplicar");
		btnAplicar.setBounds(20, 61, 89, 23);
		contentPane.add(btnAplicar);
		btnAplicar.addActionListener(new BtnApplyListener(this));
		
		JButton btnDesativar = new JButton("Desativar");
		btnDesativar.setBounds(113, 61, 89, 23);
		contentPane.add(btnDesativar);
		btnDesativar.addActionListener(new BtnDeListener(this));
	}
	
	private class BtnApplyListener implements ActionListener{
		
		JFrame frame;
		
		public BtnApplyListener(JFrame motherframe){
			frame = motherframe;
		}
		
		public void actionPerformed(ActionEvent event){
			int p1 = Calendar.getInstance().get(Calendar.YEAR) - 50;
			int p2 = Calendar.getInstance().get(Calendar.YEAR);
			try{
				p1 = Integer.parseInt(textField.getText());
			} catch(Exception e) { }
			try{
				p2 = Integer.parseInt(textField_1.getText());
			} catch(Exception e) { }
			mother.setFiltro(p1, p2);
			frame.setVisible(false);
		}
	}
	
	private class BtnDeListener implements ActionListener{
		
		JFrame frame;
		
		public BtnDeListener(JFrame motherframe){
			frame = motherframe;
		}
		
		public void actionPerformed(ActionEvent event){
			mother.deSetFiltro();
			frame.setVisible(false);
		}
	}
}
