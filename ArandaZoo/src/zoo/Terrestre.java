package zoo;

import java.time.LocalDate;

public class Terrestre extends Animal {
	private double velocidad;
	private String pelaje;

	/* Constructor */

	public Terrestre(int edad, String nombreCuidador, LocalDate fechaEntrada, double velocidad, String pelaje) {
		super(edad, nombreCuidador, fechaEntrada);
		this.velocidad = velocidad;
		this.pelaje = pelaje;
	}

	/*Getters y setters*/
	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public String getPelaje() {
		return pelaje;
	}

	public void setPelaje(String pelaje) {
		this.pelaje = pelaje;
	}

	/*Método toString*/
	@Override
	public String toString() {
		return super.toString() + "Terrestre [velocidad=" + velocidad + ", pelaje=" + pelaje + "]\n";
	}

}
