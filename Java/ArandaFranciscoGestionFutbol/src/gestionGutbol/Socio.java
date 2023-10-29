package gestionGutbol;

import java.time.LocalDate;
/**
 * 
 * @author Francisco Javier Aranda Barba
 * @version 3/06/2023 1.0 Objetivo:Clase Socio
 *
 */
public class Socio {
	private String dni;
	private LocalDate fechaNacimiento;
	private String nombre;
	private String cuentaPagos;
	private boolean alCorrienDePago;

	public Socio(String dni, LocalDate fechaNacimiento, String nombre, String cuentaPagos,
			boolean alCorrienDePago) {
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.nombre = nombre;
		this.cuentaPagos = cuentaPagos;
		this.alCorrienDePago = alCorrienDePago;
	}

	/* Getters y setters */

	public String getDni() {
		return dni;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCuentaPagos() {
		return cuentaPagos;
	}

	public void setCuentaPgaos(String cuentaPgaos) {
		this.cuentaPagos = cuentaPgaos;
	}

	public boolean isAlCorrienDePago() {
		return alCorrienDePago;
	}

	public void setAlCorrienDePago(boolean alCorrienDePago) {
		this.alCorrienDePago = alCorrienDePago;
	}

	@Override
	public String toString() {
		return "Socio [Dni=" + dni + ", fechaNacimiento=" + fechaNacimiento + ", nombre="
				+ nombre + ", cuentaPgaos=" + cuentaPagos + ", AlCorrienDePago=" + alCorrienDePago + "]";
	}

}
