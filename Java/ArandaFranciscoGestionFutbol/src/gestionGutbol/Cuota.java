package gestionGutbol;
/**
 * 
 * @author Francisco Javier Aranda Barba
 * @version 4/06/2023 1.0 Objetivo:Clase Socio
 *
 */
public class Cuota {
	private int numSocio;
	private String mes;
	private boolean pagado;
	
	public Cuota(int numSocio, String mes, boolean pagado) {
		this.numSocio = numSocio;
		this.mes = mes;
		this.pagado = pagado;
	}

	/*Getters y setters*/
	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	public int getNumSocio() {
		return numSocio;
	}

	@Override
	public String toString() {
		return "Cuota [numSocio=" + numSocio + ", mes=" + mes + ", pagado=" + pagado + "]";
	}
	
	
	
}
