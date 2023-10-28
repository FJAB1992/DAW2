package almacenBebidas;

import java.time.LocalDate;

/**
 * 
 * @author Francisco Javier Aranda Barba
 * @version 9/6/2023 1.0 Objetivo:Clase Agua
 *
 */
public class Agua extends Bebida {
	private static int id;

	private String origenManantial;

	static {
		id = 1;
	}

	/* Constructor */
	public Agua(int unidades, double precio, LocalDate fechaEntrada, String marca, int estanteria,
			String origenManantial) {
		super(unidades, precio, fechaEntrada, marca, estanteria);
		this.origenManantial = origenManantial;
	}

	/* Método para generar id para botellas de agua */
	@Override
	public String generarId() {
		String identificador = String.format("Agua ID: %04d", id);
		id++;
		return identificador;
	}

	/* Getters y setters */
	public String getOrigenManantial() {
		return origenManantial;
	}

	public void setOrigenManantial(String origenManantial) {
		this.origenManantial = origenManantial;
	}

	public static int getId() {
		return id;
	}

	/* Método toString */
	@Override
	public String toString() {
		return super.toString() + "Agua [origenManantial=" + origenManantial + "]";
	}

}
