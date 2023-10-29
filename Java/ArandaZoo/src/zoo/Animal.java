package zoo;

import java.time.LocalDate;

public class Animal {
	private static int contador;

	private int id;
	protected int edad;
	protected String nombreCuidador;
	protected LocalDate fechaEntrada;

	static {
		contador = 0;
	}
	/* Constructor */

	public Animal(int edad, String nombreCuidador, LocalDate fechaEntrada) {
		this.id = generarID();
		this.edad = edad;
		this.nombreCuidador = nombreCuidador;
		this.fechaEntrada = fechaEntrada;
	}

	/* Método para generar Id */
	protected int generarID() {
		contador++;
		return contador;
	}

	/* Getters y setters */
	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNombreCuidador() {
		return nombreCuidador;
	}

	public void setNombreCuidador(String nombreCuidador) {
		this.nombreCuidador = nombreCuidador;
	}

	public int getId() {
		return id;
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	/* Método toString */
	@Override
	public String toString() {
		return "Animal [id=" + id + ", edad=" + edad + ", nombreCuidador=" + nombreCuidador + ", fechaEntrada="
				+ fechaEntrada + "]";
	}

}
