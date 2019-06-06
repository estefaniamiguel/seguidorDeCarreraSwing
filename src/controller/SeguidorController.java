package controller;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;

import modelo.Carrera;
import modelo.Materia;
import modelo.Ubicacion;
import viewmodel.SeguidorDeCarreraViewModel;

public class SeguidorController {
	SeguidorDeCarreraViewModel modelo;

	public SeguidorController() {
		modelo = new SeguidorDeCarreraViewModel();
	}

	public SeguidorDeCarreraViewModel getModelo() {
		return modelo;
	}

	public void setModelo(SeguidorDeCarreraViewModel modelo) {
		this.modelo = modelo;
	}

	public ListModel<Materia> getMaterias() {
		DefaultListModel<Materia> lista = new DefaultListModel<Materia>();
		List<Materia> materias = modelo.getCarrera().getMaterias();
		for (int i = 0; i < materias.size(); i++) {
			lista.add(i, materias.get(i));
		}
		return lista;
	}

}
