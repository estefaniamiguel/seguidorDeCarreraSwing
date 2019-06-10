package viewmodel;

import modelo.Carrera;
import modelo.Materia;

public class CrearMateriaViewModel {

	private Materia materia;
	private Carrera carrera;
	
	public CrearMateriaViewModel(Carrera carrera){
		this.carrera = carrera;
		materia = new Materia();
	}
	
	public void agregarMateria(){
		carrera.agregarMateria(materia);
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}
	
	
	
}