package modelo;

import java.util.ArrayList;
import java.util.List;

public class Materia {
	private String nombreMateria;
	private Integer anioCursada;
	private String profesor;
	private Ubicacion ubicacion;
	private Boolean estaAprobada;
	private List<Nota> notas;

	public Materia() {
		notas = new ArrayList<Nota>();
		estaAprobada = false;
	}

	public void agregarNota(Nota nota) {
		this.notas.add(nota);
	}

	public void eliminarNota(Nota nota) {
		this.notas.remove(nota);
	}

	public String getNombreMateria() {
		return nombreMateria;
	}

	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}

	public Integer getAnioCursada() {
		return anioCursada;
	}

	public void setAnioCursada(Integer anioCursada) {
		this.anioCursada = anioCursada;
	}

	public String getProfesor() {
		return profesor;
	}

	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Boolean getEstaAprobada() {
		return estaAprobada;
	}

	public void setEstaAprobada(Boolean estaAprobada) {
		this.estaAprobada = estaAprobada;
	}

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}
	
	
}
