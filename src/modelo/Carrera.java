package modelo;

import java.util.ArrayList;
import java.util.List;

public class Carrera {
	private List<Materia> materias;
	
	public Carrera() { 
		materias = new ArrayList<Materia>();
	}
	
	public void agregarMateria(Materia materia){
		materias.add(materia);
	}

	public List<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}
	
	
}
