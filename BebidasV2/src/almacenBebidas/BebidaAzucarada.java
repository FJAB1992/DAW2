package almacenBebidas;

import java.time.LocalDate;

/**
 * 
 * @author Francisco Javier Aranda Barba
 * @version 9/6/2023 1.0 Objetivo:Clase BebidaAzucarada
 *
 */
public class BebidaAzucarada extends Bebida {
	private static int id;
	
	
	private double porcentajeAzucar;
	private boolean promocion;
	

	static {
		id = 1;
	}

	/* Constructor */
	public BebidaAzucarada(int unidades, double precio, LocalDate fechaEntrada, String marca, int estanteria,
			double porcentajeAzucar, boolean promocion) {
		super(unidades, precio, fechaEntrada, marca, estanteria);
		this.porcentajeAzucar = porcentajeAzucar;
		this.promocion = promocion;
		super.idBebida = generarId();
	}

	/* Método para generar id para bebida azucarada */
	@Override
	public String generarId() {
		String identificador = String.format("Bebida azucarada ID: %04d", id);
		id++;
		return identificador;
	}

	/* Getters y setters */
	public double getPorcentajeAzucar() {
		return porcentajeAzucar;
	}

	public void setPorcentajeAzucar(double porcentajeAzucar) {
		this.porcentajeAzucar = porcentajeAzucar;
	}

	public boolean isPromocion() {
		return promocion;
	}

	public void setPromocion(boolean promocion) {
		this.promocion = promocion;
	}

	public static int getId() {
		return id;
	}

	/* Método toString */
	@Override
	public String toString() {
		return super.toString() + "BebidaAzucarada [porcentajeAzucar=" + porcentajeAzucar + ", promocion=" + promocion
				+ "]";
	}

}
