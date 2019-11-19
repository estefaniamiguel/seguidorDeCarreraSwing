package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Carrera;
import viewmodel.CrearMateriaViewModel;

import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.BoxLayout;

public class NuevaMateria extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6142653852116469223L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNombreMateria;

	/**
	 * Create the dialog.
	 * @param carrera 
	 */
	public NuevaMateria(Carrera carrera) {
		CrearMateriaViewModel modelo = new CrearMateriaViewModel(carrera);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		{
			JLabel lblNuevaMateria = new JLabel("Nueva materia");
			contentPanel.add(lblNuevaMateria);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(1, 0, 0, 0));
			{
				JLabel lblNombre = new JLabel("Nombre:");
				panel.add(lblNombre);
			}
			{
				textFieldNombreMateria = new JTextField();
				panel.add(textFieldNombreMateria);
				textFieldNombreMateria.addKeyListener(new TextoCambiaListener() {
					
					@Override
					public void keyReleased(KeyEvent e) {
						modelo.getMateria().setNombreMateria(textFieldNombreMateria.getText());
						
					}
				});
				
				textFieldNombreMateria.setColumns(10);
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
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						modelo.agregarMateria();
						dispose();
					}
					
				});
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
					
				});
			}
		}
	}

}
