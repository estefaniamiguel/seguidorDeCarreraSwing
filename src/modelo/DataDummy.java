package modelo;

public class DataDummy {
	public Carrera crearCarreraDummy() {
		Carrera carrera = new Carrera();
		carrera.agregarMateria(crearMateria("Matematica Discreta"));
		carrera.agregarMateria(crearMateria("Algoritmos"));
		carrera.agregarMateria(crearMateria("Análisis I"));
		return carrera;
	}

	public Materia crearMateria(String nombreDeLaMateria) {
		Materia materia = new Materia();
		materia.setNombreMateria(nombreDeLaMateria);
		materia.setAnioCursada(2014);
		materia.setProfesor("Jose");
		materia.setEstaAprobada(false);
		materia.setUbicacion(Ubicacion.PrimerCuatrimestre);

		Nota notaParcial = new Nota();
		notaParcial.setDescripcion("parcial");

		Nota notaTP = new Nota();
		notaTP.setDescripcion("tp");
		materia.agregarNota(notaParcial);
		materia.agregarNota(notaTP);
		return materia;
	}
}
