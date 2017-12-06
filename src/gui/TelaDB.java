package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.xml.xquery.XQException;

import dbmanager.DBManager;

public class TelaDB extends JPanel {
	private JTextField textField;
	private String fileName;
	private JTabbedPane motherPane;
	private JTable table;
	private ArrayList<String> pathnames = new ArrayList<String>();

	/**
	 * Create the panel.
	 */
	public TelaDB(JTabbedPane pane) {
		motherPane = pane;
		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 10, 470, 24);
		add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		
		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.setBounds(480, 10, 95, 23);
		add(btnSelecionar);
		btnSelecionar.addActionListener(new BtnSelectListener());
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(585, 10, 89, 23);
		add(btnAdicionar);
		btnAdicionar.addActionListener(new BtnAddListener(this));
		try{
			ArrayList<String> retorno = dbmanager.DBManager.getDadosDocumentos();
			DefaultTableModel model = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) {
					//all cells false
					return false;
				}
			};
			model.addColumn("Professor");
			model.addColumn("Data de Atualização");
			for(int i = 0; i < retorno.size(); i +=3){
				String x = retorno.get(i+1);
				x = x.substring(0, 2) + "." + x.substring(2, 4) + "." + x.substring(4);
				model.addRow(new Object[]{retorno.get(i), x});
				pathnames.add(retorno.get(i+2));
				}
				
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 54, 664, 389);
			add(scrollPane);
			
			table = new JTable(model);
			scrollPane.setViewportView(table);
			} catch(XQException e){
				JOptionPane.showConfirmDialog(null, "Erro na comunicação com o banco", "Erro", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null);
		}
		
		JButton btnRemover = new JButton("Remover curr\u00EDculo selecionado");
		btnRemover.setBounds(459, 454, 215, 23);
		add(btnRemover);
		btnRemover.addActionListener(new BtnRemoveListener(this));

	}
	
	private class BtnSelectListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			try {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("XML Files", "xml");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					textField.setText(chooser.getSelectedFile().getAbsolutePath());
					fileName = chooser.getSelectedFile().getName();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private class BtnAddListener implements ActionListener {
		private JPanel panel;
		public BtnAddListener(JPanel jpanel){
			panel = jpanel;
		}
		public void actionPerformed(ActionEvent event){
			DBManager.adicionarArquivo(textField.getText(), fileName);
			motherPane.remove(panel);
			motherPane.insertTab("Base de Dados", null, new TelaDB(motherPane), null, 1);
			motherPane.setSelectedIndex(1);
		}
	}
	

	
	private class BtnRemoveListener implements ActionListener {
		private JPanel panel;
		public BtnRemoveListener(JPanel jpanel){
			panel = jpanel;
		}
		public void actionPerformed(ActionEvent event){
			DBManager.deletarArquivo(pathnames.get(table.getSelectedRow()));
			motherPane.remove(panel);
			motherPane.insertTab("Base de Dados", null, new TelaDB(motherPane), null, 1);
			motherPane.setSelectedIndex(1);
		}
	}

}
