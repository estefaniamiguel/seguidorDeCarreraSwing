package viewmodel;

import javax.swing.ListModel;

import modelo.Carrera;
import modelo.Materia;
import modelo.Nota;
import modelo.Ubicacion;

public class SeguidorDeCarreraViewModel {
	private Carrera carrera;
	private Materia materiaSeleccionada;
	private Nota notaSeleccionada;
	
	public Ubicacion[] getUbicacionesPosibles(){
		return Ubicacion.values();
	}
	
	public Nota nuevaNota() {
		Nota nota = new Nota();
		materiaSeleccionada.agregarNota(nota);
		return nota;
	}
	
	public void eliminarNota() {
		materiaSeleccionada.eliminarNota(notaSeleccionada);
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public Materia getMateriaSeleccionada() {
		return materiaSeleccionada;
	}

	public void setMateriaSeleccionada(Materia materiaSeleccionada) {
		this.materiaSeleccionada = materiaSeleccionada;
	}

	public Nota getNotaSeleccionada() {
		return notaSeleccionada;
	}

	public void setNotaSeleccionada(Nota notaSeleccionada) {
		this.notaSeleccionada = notaSeleccionada;
	}
	
	
}