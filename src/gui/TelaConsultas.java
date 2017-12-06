package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.xml.xquery.XQException;

public class TelaConsultas extends JPanel {
	private JButton btnFiltro;
	private JTabbedPane motherPane;
	private JTextField textField;
	private boolean filtroAtivado = false;
	private int p1 = 0;
	private int p2 = 9999;

	public TelaConsultas(JTabbedPane tabbedPane) {
		motherPane = tabbedPane;
		setLayout(null);
		
		btnFiltro = new JButton("Aplicar filtro temporal");
		btnFiltro.setBounds(10, 11, 240, 23);
		add(btnFiltro);
		btnFiltro.addActionListener(new BtnFiltro(this));
		
		JLabel lblNewLabel = new JLabel("Listas Simples");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(275, 28, 150, 35);
		add(lblNewLabel);
		
		JButton button = new JButton("1");
		button.setBounds(10, 65, 48, 23);
		add(button);
		button.addActionListener(new BtnListener(1));
		
		JLabel lblArtigosPublicados = new JLabel("Professores");
		lblArtigosPublicados.setBounds(65, 69, 260, 14);
		add(lblArtigosPublicados);
		
		JButton btnNewButton = new JButton("2");
		btnNewButton.setBounds(350, 65, 48, 23);
		add(btnNewButton);
		btnNewButton.addActionListener(new BtnListener(2));
		
		JLabel lblreasDeAtuao = new JLabel("<html>\u00C1reas de atua\u00E7\u00E3o e quantidade de docentes que atua  em cada uma delas</html>");
		lblreasDeAtuao.setVerticalAlignment(SwingConstants.TOP);
		lblreasDeAtuao.setBounds(405, 60, 260, 33);
		add(lblreasDeAtuao);
		
		JLabel lblParaCadaProfessor = new JLabel("Para cada Professor - Individualmente");
		lblParaCadaProfessor.setHorizontalAlignment(SwingConstants.CENTER);
		lblParaCadaProfessor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblParaCadaProfessor.setBounds(135, 99, 430, 35);
		add(lblParaCadaProfessor);
		
		JButton btnNewButton_1 = new JButton("3");
		btnNewButton_1.setBounds(10, 136, 48, 23);
		add(btnNewButton_1);
		btnNewButton_1.addActionListener(new BtnListener(3));
		
		JLabel lblArtigosPublicados_1 = new JLabel("Artigos Publicados");
		lblArtigosPublicados_1.setVerticalAlignment(SwingConstants.TOP);
		lblArtigosPublicados_1.setBounds(65, 140, 260, 17);
		add(lblArtigosPublicados_1);
		
		JButton btnNewButton_2 = new JButton("4");
		btnNewButton_2.setBounds(350, 136, 48, 23);
		add(btnNewButton_2);
		btnNewButton_2.addActionListener(new BtnListener(4));
		
		JLabel lblCaptulosDeLivros = new JLabel("Cap\u00EDtulos de Livros Publicados");
		lblCaptulosDeLivros.setVerticalAlignment(SwingConstants.TOP);
		lblCaptulosDeLivros.setBounds(405, 140, 260, 17);
		add(lblCaptulosDeLivros);
		
		JButton button_1 = new JButton("5");
		button_1.setBounds(10, 165, 48, 23);
		add(button_1);
		button_1.addActionListener(new BtnListener(5));
		
		JLabel lblDetalhamentoDosProjetos = new JLabel("<html>Participantes e Financiadores<br>dos Projetos de Pesquisa</html>");
		lblDetalhamentoDosProjetos.setVerticalAlignment(SwingConstants.TOP);
		lblDetalhamentoDosProjetos.setBounds(65, 160, 260, 33);
		add(lblDetalhamentoDosProjetos);
		
		JButton button_2 = new JButton("6");
		button_2.setBounds(350, 165, 48, 23);
		add(button_2);
		button_2.addActionListener(new BtnListener(6));
		
		JLabel lblRevisoDePeridicos = new JLabel("Revis\u00F5es de Peri\u00F3dicos");
		lblRevisoDePeridicos.setBounds(405, 169, 260, 14);
		add(lblRevisoDePeridicos);
		
		JButton button_3 = new JButton("7");
		button_3.setBounds(10, 194, 48, 23);
		add(button_3);
		button_3.addActionListener(new BtnListener(7));
		
		JLabel lblOrganizaesDeEventos = new JLabel("Organiza\u00E7\u00F5es de Eventos");
		lblOrganizaesDeEventos.setVerticalAlignment(SwingConstants.TOP);
		lblOrganizaesDeEventos.setBounds(65, 198, 260, 17);
		add(lblOrganizaesDeEventos);
		
		JButton button_4 = new JButton("8");
		button_4.setBounds(350, 194, 48, 23);
		add(button_4);
		button_4.addActionListener(new BtnListener(8));
		
		JLabel lblListaDePremiaes = new JLabel("Premia\u00E7\u00F5es");
		lblListaDePremiaes.setVerticalAlignment(SwingConstants.TOP);
		lblListaDePremiaes.setBounds(405, 198, 260, 17);
		add(lblListaDePremiaes);
		
		JLabel lblParaCadaProfessor_1 = new JLabel("Para cada Professor - Alunos");
		lblParaCadaProfessor_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblParaCadaProfessor_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblParaCadaProfessor_1.setBounds(135, 228, 430, 35);
		add(lblParaCadaProfessor_1);
		
		JButton button_5 = new JButton("9");
		button_5.setBounds(10, 265, 48, 23);
		add(button_5);
		button_5.addActionListener(new BtnListener(9));
		
		JLabel lblAlunosDeIniciao = new JLabel("De Inicia\u00E7\u00E3o Cient\u00EDfica");
		lblAlunosDeIniciao.setVerticalAlignment(SwingConstants.TOP);
		lblAlunosDeIniciao.setBounds(65, 269, 260, 17);
		add(lblAlunosDeIniciao);
		
		JButton button_6 = new JButton("10");
		button_6.setBounds(350, 265, 48, 23);
		add(button_6);
		button_6.addActionListener(new BtnListener(10));
		
		JLabel lblalunosQueForam = new JLabel("<html>Que foram orientandos de TCC e<br>tamb\u00E9m de Mestrado ou Doutorado</html>");
		lblalunosQueForam.setVerticalAlignment(SwingConstants.TOP);
		lblalunosQueForam.setBounds(405, 260, 260, 33);
		add(lblalunosQueForam);
		
		JButton button_7 = new JButton("11");
		button_7.setBounds(10, 294, 48, 23);
		add(button_7);
		button_7.addActionListener(new BtnListener(11));
		
		JLabel lblAlunosDeMestrado = new JLabel("De Mestrado Conclu\u00EDdo");
		lblAlunosDeMestrado.setBounds(65, 298, 260, 14);
		add(lblAlunosDeMestrado);
		
		JButton button_8 = new JButton("12");
		button_8.setBounds(350, 294, 48, 23);
		add(button_8);
		button_8.addActionListener(new BtnListener(12));
		
		JLabel lblAlunosDeMestrado_1 = new JLabel("De Mestrado, coautores em Artigos");
		lblAlunosDeMestrado_1.setVerticalAlignment(SwingConstants.TOP);
		lblAlunosDeMestrado_1.setBounds(405, 298, 260, 17);
		add(lblAlunosDeMestrado_1);
		
		JButton button_9 = new JButton("13");
		button_9.setBounds(10, 323, 48, 23);
		add(button_9);
		button_9.addActionListener(new BtnListener(13));
		
		JLabel lblDeDoutoradoConcludo = new JLabel("De Doutorado Conclu\u00EDdo");
		lblDeDoutoradoConcludo.setBounds(65, 327, 260, 14);
		add(lblDeDoutoradoConcludo);
		
		JButton button_10 = new JButton("14");
		button_10.setBounds(350, 323, 48, 23);
		add(button_10);
		button_10.addActionListener(new BtnListener(14));
		
		JLabel lblDeDoutoradoCoautores = new JLabel("De Doutorado, coautores em Artigos");
		lblDeDoutoradoCoautores.setVerticalAlignment(SwingConstants.TOP);
		lblDeDoutoradoCoautores.setBounds(405, 327, 260, 17);
		add(lblDeDoutoradoCoautores);
		
		JLabel lblParaCadaProfessor_2 = new JLabel("Para cada Professor - Demais Professores");
		lblParaCadaProfessor_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblParaCadaProfessor_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblParaCadaProfessor_2.setBounds(135, 357, 430, 35);
		add(lblParaCadaProfessor_2);
		
		JButton button_11 = new JButton("15");
		button_11.setBounds(10, 394, 48, 23);
		add(button_11);
		
		JLabel lblCoautoresDeAno = new JLabel("Coautores em Artigos");
		lblCoautoresDeAno.setBounds(65, 398, 210, 14);
		add(lblCoautoresDeAno);
		button_11.addActionListener(new BtnListener(15));
		
		JButton button_12 = new JButton("16");
		button_12.setBounds(350, 394, 48, 23);
		add(button_12);
		button_12.addActionListener(new BtnListener(16));
		
		JLabel lblcolaboradoresEmProjetos = new JLabel("<html>Colaboradores em Projetos de Pesquisa do mesmo Programa de P\u00F3s-Gradua\u00E7\u00E3o</html>");
		lblcolaboradoresEmProjetos.setVerticalAlignment(SwingConstants.TOP);
		lblcolaboradoresEmProjetos.setBounds(405, 389, 260, 33);
		add(lblcolaboradoresEmProjetos);
		
		JButton button_13 = new JButton("17");
		button_13.setBounds(10, 435, 48, 23);
		add(button_13);
		
		JLabel lblNProfessoresDo = new JLabel("<html>Os N Professores do mesmo Programa de P\u00F3s-Gradua\u00E7\u00E3o com quem mais colabora na Publica\u00E7\u00E3o de Artigos, assim como o n\u00BA de Artigos Publicados em conjunto<br>\r\nN =</html>");
		lblNProfessoresDo.setVerticalAlignment(SwingConstants.TOP);
		lblNProfessoresDo.setBounds(65, 423, 531, 60);
		add(lblNProfessoresDo);
		
		textField = new JTextField();
		textField.setText("5");
		textField.setColumns(10);
		textField.setBounds(87, 454, 20, 21);
		add(textField);
		button_13.addActionListener(new BtnListener(17));
	}
	
	private class BtnFiltro implements ActionListener {
		
		TelaConsultas mother;
		
		public BtnFiltro(TelaConsultas motherpane){
			mother = motherpane;
		}
		
		public void actionPerformed(ActionEvent event){
			TelaFiltro frame = new TelaFiltro(mother);
			frame.setVisible(true);
		}
		
	}
	
	private class BtnListener implements ActionListener {
		
		private int btnN;
		
		public BtnListener(int number){
			btnN = number;
		}
		
		public void actionPerformed(ActionEvent event){
			int param1 = Calendar.getInstance().get(Calendar.YEAR) - 50;
			int param2 = Calendar.getInstance().get(Calendar.YEAR);
			if(filtroAtivado){
				param1 = p1;
				param2 = p2;
			}
			if(btnN == 17){
				try{
					param1 = Integer.parseInt(textField.getText());
				} catch (Exception e) { }
			}
			try{
				String retorno = dbmanager.DBManager.consultar(btnN, param1, param2);
				JPanel resultado = new TelaResultados(motherPane, retorno);
				String name = "" + btnN;
				if(filtroAtivado && btnN != 1 && btnN != 2 && btnN != 17)
					name += "(" + btnFiltro.getText() + ")";
				motherPane.addTab(name, null, resultado, null);
				motherPane.setSelectedIndex(motherPane.getTabCount()-1);
			} catch (XQException e){
				JOptionPane.showConfirmDialog(null, "Erro na comunicação com o banco", "Erro", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null);
			}
		}
		
	}
	
	public void setFiltro(int ai, int af){
		this.filtroAtivado = true;
		if(ai > Calendar.getInstance().get(Calendar.YEAR))
			ai = Calendar.getInstance().get(Calendar.YEAR);
		p1 = ai;
		String text = "" + p1;
		if (af < ai)
			af = Calendar.getInstance().get(Calendar.YEAR);
		p2 = af;
		if(ai != af)
			text += "-" + af;
		btnFiltro.setText(text);
		
	}
	
	public void deSetFiltro(){
		this.filtroAtivado = false;
		btnFiltro.setText("Aplicar filtro temporal");
	}
	
}
