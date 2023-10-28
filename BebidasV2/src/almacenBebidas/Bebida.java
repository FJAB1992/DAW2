package almacenBebidas;

import java.time.LocalDate;

/**
 * 
 * @author Francisco Javier Aranda Barba
 * @version 9/6/2023 1.0 Objetivo:Clase Bebidas
 *
 */
public abstract class Bebida{
	
	protected String idBebida;
	protected int unidades;
	protected double precio;
	protected LocalDate fechaEntrada;
	protected String marca;
	protected int estanteria;

	/* Constructor */

	public Bebida(int unidades, double precio, LocalDate fechaEntrada, String marca, int estanteria) {
		this.idBebida = generarId();
		this.unidades = unidades;
		this.precio = precio;
		this.fechaEntrada = fechaEntrada;
		this.marca = marca;
		this.estanteria = estanteria;
	}

	
	/* Getters y setters */
	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getIdBebida() {
		return idBebida;
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public String getMarca() {
		return marca;
	}
	
	public int getEstanteria() {
		return estanteria;
	}

	public void setEstanteria(int estanteria) {
		this.estanteria = estanteria;
	}

	/*Método abstracto para Id*/
	public abstract String generarId();
	
	/*Método toString*/
	@Override
	public String toString() {
		return "Bebida [idBebida=" + idBebida + ", unidades=" + unidades + ", precio=" + precio + ", fechaEntrada="
				+ fechaEntrada + ", marca=" + marca + ", estanteria=" + estanteria + "]";
	}

}
