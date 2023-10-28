package zoo;

import java.time.LocalDate;

public class Acuatico extends Animal {
	private String comida;

	/* Constructor */

	public Acuatico(int edad, String nombreCuidador, LocalDate fechaEntrada, String comida) {
		super(edad, nombreCuidador, fechaEntrada);
		this.comida = comida;
	}

	/*Getters y setters*/
	public String getComida() {
		return comida;
	}

	public void setComida(String comida) {
		this.comida = comida;
	}

	/*Método toString*/
	@Override
	public String toString() {
		return super.toString() + "Acuatico [comida=" + comida + "]\n";
	}

}
