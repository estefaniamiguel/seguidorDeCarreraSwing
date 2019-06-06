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
	
}