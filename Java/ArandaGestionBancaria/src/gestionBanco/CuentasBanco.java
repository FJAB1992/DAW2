package gestionBanco;

/**
 * 
 * @author Francisco Javier Aranda Barba
 * @version 16/05/2023 1.0 Objetivo:Clase CuentasBanco
 *
 */
public class CuentasBanco {

	private int numCuenta;
	private String dni;
	private String situacion;

	/* Constructor */
	public CuentasBanco(int numCuenta, String dni, String situacion) {
		this.numCuenta = numCuenta;
		this.dni = dni;
		this.situacion = situacion;
	}

	/* Getters y Setters */

	public int getNumCuenta() {
		return numCuenta;
	}

	public void setNumCuenta(int numCuenta) {
		this.numCuenta = numCuenta;
	}

	public String getSituacion() {
		return situacion;
	}

	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

	public String getDni() {
		return dni;
	}

	/* Método toString */

	@Override
	public String toString() {
		return "CuentasBanco [ numCuenta = " + numCuenta + " , dni = " + dni + " , situacion = " + situacion + " ]";
	}

}
