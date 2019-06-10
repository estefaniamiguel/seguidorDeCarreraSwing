package vista;

import modelo.Nota;

public class EditarNota extends NotaEditable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -531198010251020051L;

	public EditarNota(Nota nota) {
		super(nota);
	}
	
	public String getTitulo() {
		return "Editar nota";
	}

}
