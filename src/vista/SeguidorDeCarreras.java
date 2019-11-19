package vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controller.SeguidorController;
import modelo.Carrera;
import modelo.DataDummy;
import modelo.Materia;
import modelo.Nota;
import modelo.Ubicacion;
import viewmodel.SeguidorDeCarreraViewModel;
import javax.swing.ListSelectionModel;

public class SeguidorDeCarreras extends JFrame {

	private JPanel contentPane;
	private SeguidorDeCarreraViewModel modelo;
	private JTable tablaNotas;
	private NotaTableModel notaTableModel;
	private JLabel lblNombreDeLaMateira;
	private JCheckBox checkBoxAprobo;
	private JTextField textFieldAnio;
	private JTextField textFieldProfesor;
	private JComboBox<Ubicacion> comboBoxUbicacion;

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
		crearModelo();
		setTitle("Seguidor de carrera");
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
		lblMaterias.setHorizontalAlignment(SwingConstants.CENTER);
		panelMaterias.add(lblMaterias);

		JList<Materia> listaDeMaterias = new JList<Materia>();
		listaDeMaterias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaDeMaterias.setCellRenderer(new MateriaCell());
		listaDeMaterias.setModel(new SeguidorController(modelo).getMaterias());
		panelMaterias.add(listaDeMaterias);

		JButton botonNuevaMateria = new JButton("Nueva materia");
		botonNuevaMateria.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				NuevaMateria ventana = new NuevaMateria(modelo.getCarrera());
				ventana.setVisible(true);
				ventana.addWindowListener(new VentanaSeCierraListener() {

					@Override
					public void windowClosed(WindowEvent e) {
						listaDeMaterias.setModel(new SeguidorController(modelo).getMaterias());

					}
				});
			}
		});

		panelMaterias.add(botonNuevaMateria);

		crearDetalleDeMateria(splitPane);

		listaDeMaterias.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				Materia materia = listaDeMaterias.getSelectedValue();
				if (materia != null) {
					seSelecciono(materia);
				}

			}

			private void seSelecciono(Materia materia) {
				modelo.setMateriaSeleccionada(materia);
				lblNombreDeLaMateira.setText(materia.getNombreMateria());
				checkBoxAprobo.setSelected(materia.getEstaAprobada());
				if (materia.getAnioCursada() != null) {
					textFieldAnio.setText(materia.getAnioCursada().toString());
				}
				textFieldProfesor.setText(materia.getProfesor());
				comboBoxUbicacion.setSelectedItem(materia.getUbicacion());
				refrescarTablaDeNotas();
			}

		});

	}
	
	private void crearDetalleDeMateria(JSplitPane splitPane) {
		JPanel panelDerecho = new JPanel();
		splitPane.setRightComponent(panelDerecho);
		panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));

		lblNombreDeLaMateira = new JLabel("Nombre de la mateira");
		panelDerecho.add(lblNombreDeLaMateira);

		JPanel panelMateria = new JPanel();
		panelDerecho.add(panelMateria);
		panelMateria.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblAprobo = new JLabel("Aprobo:");
		panelMateria.add(lblAprobo);

		checkBoxAprobo = new JCheckBox("");
		panelMateria.add(checkBoxAprobo);
		checkBoxAprobo.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				modelo.getMateriaSeleccionada().setEstaAprobada(checkBoxAprobo.isSelected());

			}

		});

		JLabel lblAnioDeCursada = new JLabel("Año de cursada:");
		panelMateria.add(lblAnioDeCursada);

		textFieldAnio = new JTextField();
		panelMateria.add(textFieldAnio);
		textFieldAnio.setColumns(10);
		textFieldAnio.addKeyListener(new TextoCambiaListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				modelo.getMateriaSeleccionada()
						.setAnioCursada(Integer.valueOf(textFieldAnio.getText()));

			}
		});

		JLabel lblProfesorDeCursada = new JLabel("Profesor de cursada:");
		panelMateria.add(lblProfesorDeCursada);

		textFieldProfesor = new JTextField();
		panelMateria.add(textFieldProfesor);
		textFieldProfesor.setColumns(10);
		textFieldProfesor.addKeyListener(new TextoCambiaListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				modelo.getMateriaSeleccionada().setProfesor(textFieldProfesor.getText());

			}
		});

		JLabel lblUbicacion = new JLabel("Ubicacion");
		panelMateria.add(lblUbicacion);

		comboBoxUbicacion = new JComboBox<Ubicacion>();
		comboBoxUbicacion
				.setModel(new DefaultComboBoxModel<Ubicacion>(modelo.getUbicacionesPosibles()));
		panelMateria.add(comboBoxUbicacion);
		comboBoxUbicacion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				modelo.getMateriaSeleccionada()
						.setUbicacion((Ubicacion) comboBoxUbicacion.getSelectedItem());

			}

		});

		crearSeccionNotas(panelDerecho);
	}

	private void crearSeccionNotas(JPanel panel) {
		JLabel labelNotas = new JLabel("Notas de la cursada");
		panel.add(labelNotas);

		tablaNotas = new JTable();
		panel.add(new JScrollPane(tablaNotas));
		tablaNotas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				Nota nota = notaTableModel.obtener(tablaNotas.convertRowIndexToModel(tablaNotas.getSelectedRow()));
				modelo.setNotaSeleccionada(nota);
			}
		});

		crearBotonesNota(panel);

		if (modelo.getMateriaSeleccionada() != null) {
			notaTableModel = new NotaTableModel(modelo.getMateriaSeleccionada().getNotas());
			tablaNotas.setModel(notaTableModel);
		}
	}

	private void crearBotonesNota(JPanel panel) {
		JPanel panelBotones = new JPanel();
		panel.add(panelBotones);

		JButton botonEditar = new JButton("Editar");
		panelBotones.add(botonEditar);
		botonEditar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Nota nota = modelo.getNotaSeleccionada();
				if (nota != null) {
					EditarNota editarDialog = new EditarNota(nota);
					editarDialog.setVisible(true);
					editarDialog.addWindowListener(new VentanaSeCierraListener() {

						@Override
						public void windowClosed(WindowEvent e) {
							refrescarTablaDeNotas();

						}
					});
				} else {
					JOptionPane.showMessageDialog(panelBotones, "Debe seleccionar una nota");

				}
			}
		});

		JButton botonQuitar = new JButton("-");
		panelBotones.add(botonQuitar);
		botonQuitar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				modelo.eliminarNota();
				refrescarTablaDeNotas();

			}
		});

		JButton botonAgregar = new JButton("+");
		panelBotones.add(botonAgregar);
		botonAgregar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Nota nota = modelo.nuevaNota();
				CrearNota crearDialog = new CrearNota(nota);
				crearDialog.setVisible(true);
				crearDialog.addWindowListener(new VentanaSeCierraListener() {

					@Override
					public void windowClosed(WindowEvent e) {
						refrescarTablaDeNotas();

					}
				});
			}
		});
	}

	private void crearModelo() {
		modelo = new SeguidorDeCarreraViewModel();

		DataDummy data = new DataDummy();

		Carrera carreraDummy = data.crearCarreraDummy();

		modelo.setCarrera(carreraDummy);
		modelo.setMateriaSeleccionada(carreraDummy.getMaterias().get(0));
	}

	private void refrescarTablaDeNotas() {
		notaTableModel = new NotaTableModel(modelo.getMateriaSeleccionada().getNotas());
		tablaNotas.setModel(notaTableModel);
		tablaNotas.revalidate();
		tablaNotas.repaint();
		tablaNotas.getSelectionModel().clearSelection();
		notaTableModel.fireTableDataChanged();
	}

	class NotaTableModel extends AbstractTableModel {

		/**
		* 
		*/
		private static final long serialVersionUID = 850840400037656638L;

		List<Nota> notasList;

		String[] headerList = { "Fecha", "Descripción", "Aprobó" };
		Class[] classes = { Date.class, String.class, String.class };

		public NotaTableModel(List<Nota> list) {
			notasList = list;
		}

		public Nota obtener(int index) {
			try {
				return notasList.get(index);
			} catch (IndexOutOfBoundsException e) {
				return null;
			}

		}

		@Override
		public int getColumnCount() {
			return headerList.length;
		}

		@Override
		public int getRowCount() {
			return notasList.size();
		}

		@Override
		public Class<?> getColumnClass(int index) {
			return classes[index];
		}

		// this method is called to set the value of each cell
		@Override
		public Object getValueAt(int row, int column) {
			Nota nota = notasList.get(row);
			switch (column) {
			case 0:
				return nota.getFecha();
			case 1:
				return nota.getDescripcion();
			case 2:
				return nota.getEstaAprobada() ? "Si" : "No";
			default:
				return "";
			}
		}

		// This method will be used to display the name of columns
		public String getColumnName(int col) {
			return headerList[col];
		}

		@Override
		public void setValueAt(Object value, int row, int col) {
			Nota entity = notasList.get(row);
			switch (col) {
			case 0:
				entity.setFecha((Date) value);
				break;
			case 1:
				entity.setDescripcion((String) value);
				break;
			case 2:
				entity.setEstaAprobada((Boolean) value);
				break;
			default:
				break;

			}
			fireTableCellUpdated(row, col);
		}

	}
}
