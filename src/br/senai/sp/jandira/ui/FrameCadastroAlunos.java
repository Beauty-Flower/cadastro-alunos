package br.senai.sp.jandira.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.senai.sp.jandira.model.Aluno;
import br.senai.sp.jandira.model.Periodo;
import br.senai.sp.jandira.repository.AlunoRepository;

public class FrameCadastroAlunos extends JFrame {

	private JPanel contentPane;
	private JTextField txtMatricula;
	private JTextField txtNome;
	
	private int posicao = 0;

	public FrameCadastroAlunos() {
		
		//Jeito diferente de fazer vetor
		String[] diasDaSemana = {
				"Domingo", "Segunda", "Ter�a", "Quarta", "Quinta", "Sexta", "S�bado"
		};
		
		//Jeito diferente de fazer o for
		/*for (String dia : diasDaSemana) {
			System.out.println(dia);		
		}*/
		
		setTitle("Cadastro de Alunos");
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
		DefaultComboBoxModel<String> modelPeriodo = 
				new DefaultComboBoxModel<String>();
		for (Periodo p : Periodo.values()) {
			modelPeriodo.addElement(p.getDescricao());
		}
		comboPeriodo.setModel(modelPeriodo);
		comboPeriodo.setBounds(66, 103, 102, 22);
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
		DefaultListModel<String> listaModel = new DefaultListModel<String>();
		listAlunos.setModel(listaModel);
		scrollPane.setViewportView(listAlunos);
		
		JButton btnExibirAlunos = new JButton("Exibir Alunos");
		btnExibirAlunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExibirAlunos.setBounds(90, 205, 104, 23);
		contentPane.add(btnExibirAlunos);
		
		AlunoRepository turma = new AlunoRepository(3);
		
		btnSalvarAluno.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Aluno aluno = new Aluno();
				aluno.setMatricula(txtMatricula.getText());
				aluno.setNome(txtNome.getText());
				
				//Definir o hor�rio que o aluno estuda
				aluno.setPeriodo(determinarPeriodo(comboPeriodo.getSelectedIndex()));
			    
				turma.gravar(aluno, posicao);
				
				posicao++;
				
				//Adicionando o nome do aluno ao model da lista
				listaModel.addElement(aluno.getNome());
				
				if (posicao == turma.getTamanho()) {
					btnSalvarAluno.setEnabled(false);
					JOptionPane.showMessageDialog(null, "A turma j� est� cheia!!", "Turma cheia!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		btnExibirAlunos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Aluno aluno : turma.listarTodos()) {
					System.out.println(aluno.getMatricula());
					System.out.println(aluno.getNome());
					System.out.println(aluno.getPeriodo().getDescricao());
					System.out.println("----------------------");
				}
				
			}
		});
		
		listAlunos.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Aluno alunoSelecionado = turma.listarAluno(listAlunos.getSelectedIndex());
				txtMatricula.setText(alunoSelecionado.getMatricula());
				txtNome.setText(alunoSelecionado.getNome());
				comboPeriodo.setSelectedIndex(alunoSelecionado.getPeriodo().ordinal());
			}
		});
	
		}
	
	    private Periodo determinarPeriodo(int periodoSelecionado) {
		    if (periodoSelecionado == 0) {
		    	return Periodo.MANHA;
		    } else if (periodoSelecionado == 1) {
		    	return Periodo.TARDE;
		    } else {
		    	return Periodo.NOITE;
		    }
	    }
	}
