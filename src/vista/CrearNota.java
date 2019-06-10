package vista;

import modelo.Nota;

public class CrearNota extends NotaEditable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3877281369649668757L;

	public CrearNota(Nota nota) {
		super(nota);
	}
	
	public String getTitulo() {
		return "Crear nota";
	}

}
