package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TelaResultados extends JPanel {
	
	private JTabbedPane motherPane;
	String resultado = "";

	/**
	 * Create the panel.
	 */
	public TelaResultados(JTabbedPane tabbedPane, String result) {
		motherPane = tabbedPane;
		resultado = result;
		setLayout(null);
		
		JButton btnClose = new JButton("Fechar");
		btnClose.setBounds(585, 454, 89, 23);
		add(btnClose);
		btnClose.addActionListener(new BtnCloseListener(this));
		
		JButton btnNewButton = new JButton("Exportar");
		btnNewButton.setBounds(486, 454, 89, 23);
		add(btnNewButton);
		btnNewButton.addActionListener(new BtnExportListener());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 664, 432);
		add(scrollPane);
		
		char ap = '"';
		result = "<html>"
				+ "<style>table, th, td {border: 1px solid black;}</style>"
				+ "<table width=" + ap + "644" + ap + ">" + resultado + "</table>"
				+ "</html>";
		
		JLabel lblResultado = new JLabel(result);
		scrollPane.setViewportView(lblResultado);

	}
	
	private class BtnCloseListener implements ActionListener {
		private JPanel panel;
		public BtnCloseListener(JPanel jpanel){
			panel = jpanel;
		}
		public void actionPerformed(ActionEvent event){
			motherPane.remove(panel);
		}
	}
	private class BtnExportListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			try {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("HTML Files", "html");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showSaveDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File file = new File(chooser.getSelectedFile().getAbsolutePath() + ".html");
					file.createNewFile();
					FileWriter out = new FileWriter(file);
					BufferedWriter bw = new BufferedWriter(out);
					char ap = '"';
					String output = "<html>"
							+ "<style>table, th, td {border: 1px solid black;}</style>"
							+ "<table>" + resultado + "</table>"
							+ "</html>";
					bw.write(output);
					bw.close();
					out.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
