package gestionGutbol;

/**
 * 
 * @author Francisco Javier Aranda Barba
 * @version 19/05/2023 1.0 Objetivo:Clase ExcepcionDNI
 *
 */
public class ExcepcionDNI extends Exception {
	private String dniException;

	public ExcepcionDNI(String dni) {
		this.dniException = dni;
	}

	public String toString() {
		return "ExcepcionDni[ " + this.dniException + " ]";
	}
}
