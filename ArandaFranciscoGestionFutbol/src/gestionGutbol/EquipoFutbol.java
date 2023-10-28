package gestionGutbol;

import java.util.ArrayList;


/**
 * 
 * @author Francisco Javier Aranda Barba
 * @version 5/06/2023 1.0 Objetivo:Clase EquipoFutbol
 *
 */
public class EquipoFutbol {
	private ArrayList<Socio> listaSocio;
	private ArrayList<Cuota> listaCuota;

	public EquipoFutbol() {
		this.listaSocio = new ArrayList<Socio>();
		this.listaCuota = new ArrayList<Cuota>();
	}

	/*Getters y setters*/
	public ArrayList<Socio> getListaSocio() {
		return listaSocio;
	}

	public void setListaSocio(ArrayList<Socio> listaSocio) {
		this.listaSocio = listaSocio;
	}

	public ArrayList<Cuota> getListaCuota() {
		return listaCuota;
	}

	public void setListaCuota(ArrayList<Cuota> listaCuota) {
		this.listaCuota = listaCuota;
	}

	@Override
	public String toString() {
		return "EquipoFutbol [listaSocio=" + listaSocio + ", listaCuota=" + listaCuota + "]";
	}
	
	/* Método para añadir Socio */
	public void anadirSocio(Socio socio) {
		this.listaSocio.add(socio);
	}
	/* Método para añadir Cuota */
	public void anadirCuota(Cuota cuota) {
		this.listaCuota.add(cuota);
	}
	
}
