package br.senai.sp.jandira.ui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.senai.sp.jandira.model.Periodo;

public class FrameCadastroAlunos extends JFrame {

	private JPanel contentPane;
	private JTextField txtMatricula;
	private JTextField txtNome;

	public FrameCadastroAlunos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMatricula = new JLabel("Matr\u00EDcula:");
		lblMatricula.setBounds(10, 34, 59, 14);
		contentPane.add(lblMatricula);
		
		txtMatricula = new JTextField();
		txtMatricula.setBounds(66, 31, 102, 20);
		contentPane.add(txtMatricula);
		txtMatricula.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 72, 59, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(66, 69, 139, 20);
		contentPane.add(txtNome);
		
		JLabel lblPeriodo = new JLabel("Per\u00EDodo:");
		lblPeriodo.setBounds(10, 107, 59, 14);
		contentPane.add(lblPeriodo);
		
		JComboBox comboPeriodo = new JComboBox();
		comboPeriodo.setModel(new DefaultComboBoxModel(Periodo.values()));
		comboPeriodo.setBounds(66, 103, 85, 22);
		contentPane.add(comboPeriodo);
		
		JButton btnSalvarAluno = new JButton("Salvar Aluno");
		btnSalvarAluno.setBounds(62, 151, 152, 23);
		contentPane.add(btnSalvarAluno);
		
		JLabel lblListaDeAlunos = new JLabel("Lista de alunos:");
		lblListaDeAlunos.setBounds(254, 34, 95, 14);
		contentPane.add(lblListaDeAlunos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(254, 58, 159, 192);
		contentPane.add(scrollPane);
		
		JList listAlunos = new JList();
		scrollPane.setViewportView(listAlunos);
	}
}
