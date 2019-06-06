package modelo;

import java.util.Date;

public class Nota {
	private String descripcion;
	private Date fecha;
	private Boolean estaAprobada;

	public Nota() {
		fecha = new Date();
		estaAprobada = false;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Boolean getEstaAprobada() {
		return estaAprobada;
	}

	public void setEstaAprobada(Boolean estaAprobada) {
		this.estaAprobada = estaAprobada;
	}
	
	
}
