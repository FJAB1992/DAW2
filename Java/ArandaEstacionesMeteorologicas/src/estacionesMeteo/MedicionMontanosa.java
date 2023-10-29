package estacionesMeteo;

import java.time.LocalDateTime;

public class MedicionMontanosa extends Medicion {
	private double velocidadViento;
	private String direccionViento;

	/* Constructor */
	public MedicionMontanosa(LocalDateTime fechaHora, int temperatura, int humedadRelativa, double pluviometria,
			double velocidadViento, String direccionViento) {
		super(fechaHora, temperatura, humedadRelativa, pluviometria);
		this.velocidadViento = velocidadViento;
		this.direccionViento = direccionViento;
	}

	/* Getters y setters */
	public double getVelocidadViento() {
		return velocidadViento;
	}

	public void setVelocidadViento(double velocidadViento) {
		this.velocidadViento = velocidadViento;
	}

	public void setDireccionViento(String direccionViento) {
		this.direccionViento = direccionViento;
	}

	public String getDireccionViento() {
		return direccionViento;
	}

	/* Método ToString */
	@Override
	public String toString() {
		return super.toString() + "Montañosa [velocidadViento=" + velocidadViento + ", direccionViento="
				+ direccionViento + "]\n";
	}

}
