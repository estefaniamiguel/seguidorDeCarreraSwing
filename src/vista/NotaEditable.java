package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import modelo.Nota;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JCheckBox;

public abstract class NotaEditable extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7367590554815810516L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldFecha;
	private JTextField textFieldDescripcion;

	/**
	 * Create the dialog.
	 */
	public NotaEditable(Nota nota) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		{
			JLabel lblEditarNota = new JLabel(getTitulo());
			contentPanel.add(lblEditarNota);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel lblFecha = new JLabel("Fecha");
				panel.add(lblFecha);
			}
			{
				textFieldFecha = new JTextField();
				panel.add(textFieldFecha);
				textFieldFecha.setColumns(10);
				String fecha = new SimpleDateFormat("dd/MM/yyyy").format(nota.getFecha());
				textFieldFecha.setText(fecha);
				textFieldFecha.addKeyListener(new TextoCambiaListener() {
					
					@Override
					public void keyReleased(KeyEvent e) {
						try {
							Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(textFieldFecha.getText());
							nota.setFecha(fecha);
						} catch (ParseException e1) {
							JOptionPane.showMessageDialog(panel, "La fecha debe estar en formato dd/MM/yyyy");
						}  
						
					}
				});
			}
			{
				JLabel lblDescripcion = new JLabel("Descripción");
				panel.add(lblDescripcion);
			}
			{
				textFieldDescripcion = new JTextField();
				panel.add(textFieldDescripcion);
				textFieldDescripcion.setColumns(10);
				textFieldDescripcion.setText(nota.getDescripcion());
				textFieldDescripcion.addKeyListener(new TextoCambiaListener() {
					
					@Override
					public void keyReleased(KeyEvent e) {
						nota.setDescripcion(textFieldDescripcion.getText());
						
					}
				});
			}
			{
				JLabel lblAprobo = new JLabel("Aprobó");
				panel.add(lblAprobo);
			}
			{
				JCheckBox checkBox = new JCheckBox("");
				panel.add(checkBox);
				checkBox.setSelected(nota.getEstaAprobada());
				checkBox.addChangeListener(new ChangeListener() {

					@Override
					public void stateChanged(ChangeEvent e) {
						nota.setEstaAprobada(checkBox.isSelected());
						
					}
				});
					
				
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
					
				});
			}
		}
	}

	abstract String getTitulo();

}
