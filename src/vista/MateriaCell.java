package vista;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import modelo.Materia;

public class MateriaCell extends JLabel implements ListCellRenderer<Materia> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7113937991056162157L;

	@Override
	public Component getListCellRendererComponent(JList<? extends Materia> list, Materia value, int index,
			boolean isSelected, boolean cellHasFocus) {
		setText(value.getNombreMateria());
		if (isSelected) {
			setForeground(Color.BLUE);
		} else {
			setForeground(Color.BLACK);

		}
		return this;
	}

}
