package gestionGutbol;
/**
 * 
 * @author Francisco Javier Aranda Barba
 * @version 6/06/2023 1.0 Objetivo:Clase ExcepcionMesEnum
 *
 */
public class ExcepcionMesEnum extends Exception{
	private enum Mes {
		Enero, Febrero, Marzo, Abril, Mayo, Junio, Julio, Agosto, Septiembre, Octubre, Noviembre, Diciembre
	}
	private int mesIntroducido;
	
	public ExcepcionMesEnum(int mesIntroducido) {
		
		this.mesIntroducido =mesIntroducido;
	}

	/*
	 * Ejemplo:
	 * 
	 * public enum Demarcacion { PORTERO, DEFENSA, CENTROCAMPISTA, DELANTERO }
	 * 
	 * 
	 * 
	 * public enum Demarcacion{PORTERO, DEFENSA, CENTROCAMPISTA, DELANTERO}
	 * Demarcacion delantero = Demarcacion.DELANTERO;
	 *  
	 * // Instancia de un enum de la
	 * clase Demarcación delantero.name(); 
	 * 
	 * // Devuelve un String con el nombre de la
	 * constante (DELANTERO) delantero.toString(); 
	 * 
	 * // Devuelve un String con el
	 * nombre de la constante (DELANTERO) delantero.ordinal(); 
	 * 
	 * // Devuelve un entero
	 * con la posición del enum según está declarada (3). delantero.compareTo(Enum
	 * otro); 
	 * 
	 * // Compara el enum con el parámetro según el orden en el que están
	 * declarados lo enum Demarcacion.values(); 
	 * 
	 * // Devuelve un array que contiene todos los enum
	 */

}
