package vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.SeguidorController;
import modelo.Carrera;
import modelo.DataDummy;
import modelo.Materia;
import modelo.Ubicacion;
import viewmodel.SeguidorDeCarreraViewModel;
import javax.swing.ListSelectionModel;

public class SeguidorDeCarreras extends JFrame {

	private JPanel contentPane;
	private SeguidorController controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeguidorDeCarreras frame = new SeguidorDeCarreras();

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
	public SeguidorDeCarreras() {
		crearController();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Seguidor de carreras");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 30));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);

		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);

		JPanel panelMaterias = new JPanel();
		splitPane.setLeftComponent(panelMaterias);
		panelMaterias.setLayout(new BoxLayout(panelMaterias, BoxLayout.Y_AXIS));

		JLabel lblMaterias = new JLabel("Materias");
		panelMaterias.add(lblMaterias);

		JList<Materia> listaDeMaterias = new JList<Materia>();
		listaDeMaterias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaDeMaterias.setCellRenderer(new MateriaCell());
		listaDeMaterias.setModel(controller.getMaterias());
		listaDeMaterias.setSelectedIndex(0);
		panelMaterias.add(listaDeMaterias);

		JButton botonNuevaMateria = new JButton("Nueva materia");
		panelMaterias.add(botonNuevaMateria);

		JPanel panelDerecho = new JPanel();
		splitPane.setRightComponent(panelDerecho);
		panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));

		JLabel lblNombreDeLaMateira = new JLabel("Nombre de la mateira");
		panelDerecho.add(lblNombreDeLaMateira);

		JPanel panelMateria = new JPanel();
		panelDerecho.add(panelMateria);
		panelMateria.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblAprobo = new JLabel("Aprobo:");
		panelMateria.add(lblAprobo);

		JCheckBox checkBoxAprobo = new JCheckBox("");
		panelMateria.add(checkBoxAprobo);

		JLabel lblAnioDeCursada = new JLabel("Año de cursada:");
		panelMateria.add(lblAnioDeCursada);

		JTextField textFieldAnio = new JTextField();
		panelMateria.add(textFieldAnio);
		textFieldAnio.setColumns(10);

		JLabel lblProfesorDeCursada = new JLabel("Profesor de cursada:");
		panelMateria.add(lblProfesorDeCursada);

		JTextField textFieldProfesor = new JTextField();
		panelMateria.add(textFieldProfesor);
		textFieldProfesor.setColumns(10);

		JLabel lblUbicacion = new JLabel("Ubicacion");
		panelMateria.add(lblUbicacion);

		JComboBox<Ubicacion> comboBoxUbicacion = new JComboBox<Ubicacion>();
		comboBoxUbicacion.setModel(new DefaultComboBoxModel<Ubicacion>(controller.getModelo().getUbicacionesPosibles()));
		panelMateria.add(comboBoxUbicacion);

		JLabel labelNotas = new JLabel("Notas de la cursada");
		panelDerecho.add(labelNotas);

		JTable tablaNotas = new JTable();
		panelDerecho.add(tablaNotas);

		JPanel panelBotones = new JPanel();
		panelDerecho.add(panelBotones);

		JButton botonEditar = new JButton("Editar");
		panelBotones.add(botonEditar);

		JButton botonQuitar = new JButton("+");
		panelBotones.add(botonQuitar);

		JButton botonAgregar = new JButton("-");
		panelBotones.add(botonAgregar);
		
		
		listaDeMaterias.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				seSelecciono(listaDeMaterias.getSelectedValue());
				
			}

			private void seSelecciono(Materia materia) {
				lblNombreDeLaMateira.setText(materia.getNombreMateria());
				checkBoxAprobo.setSelected(materia.getEstaAprobada());
				textFieldAnio.setText(materia.getAnioCursada().toString());
				textFieldProfesor.setText(materia.getProfesor());
				comboBoxUbicacion.setSelectedItem(materia.getUbicacion());
				
			}
			
		});
	}

	private void crearController() {
		controller = new SeguidorController();

		DataDummy data = new DataDummy();

		Carrera carreraDummy = data.crearCarreraDummy();

		controller.getModelo().setCarrera(carreraDummy);
		controller.getModelo().setMateriaSeleccionada(carreraDummy.getMaterias().get(0));

	}

}
