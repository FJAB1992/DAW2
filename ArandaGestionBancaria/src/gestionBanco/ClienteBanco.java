package gestionBanco;

/**
 * 
 * @author Francisco Javier Aranda Barba
 * @version 16/05/2023 1.0 Objetivo:Clase ClienteBanco
 *
 */
public class ClienteBanco {
	private String dni;
	private String nombre;
	private String telefono;
	private String direccion;

	/* Constructor */
	 public ClienteBanco(String dni, String nombre, String telefono, String direccion){
	        this.dni = dni;
	        this.nombre = nombre;
	        this.telefono = telefono;
	        this.direccion = direccion;
	    }

	/*Getters y Setters*/
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDni() {
		return dni;
	}
	
	/*Método toString*/

	@Override
	public String toString() {
		return "ClienteBanco [ dni = " + dni + " , nombre = " + nombre + " , telefono = " + telefono + " , direccion = "
				+ direccion + " ]";
	}

}
