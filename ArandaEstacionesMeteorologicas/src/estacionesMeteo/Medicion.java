package estacionesMeteo;

import java.time.LocalDateTime;

public class Medicion {
	private LocalDateTime fecha;
	private int temperatura;
	private int humedad;
	private double pluviometria;/* (l/m2) */

	/* Constructor */
	public Medicion(LocalDateTime fecha, int temperatura, int humedad, double pluviometria) {
		this.fecha = fecha;
		this.temperatura = temperatura;
		this.humedad = humedad;
		this.pluviometria = pluviometria;
	}

	/* Getters y setters */
	public int getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(int temperatura) {
		this.temperatura = temperatura;
	}

	public int getHumedad() {
		return humedad;
	}

	public void setHumedad(int humedad) {
		this.humedad = humedad;
	}

	public double getPluviometria() {
		return pluviometria;
	}

	public void setPluviometria(double pluviometria) {
		this.pluviometria = pluviometria;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	/* Método toString */
	@Override
	public String toString() {
		return "Medición [fecha=" + fecha + ", temperatura=" + temperatura + ", humedad=" + humedad + ", pluviometria="
				+ pluviometria + "]\n";
	}

}
