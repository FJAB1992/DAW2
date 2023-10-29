package estacionesMeteo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Estacion {
	private String nombre;
	private double coordenadasGPS;
	private double altitud;
	private LocalDate fechaInstalacion;
	private String responsable;
	private ArrayList<Medicion> listaMediciones;

	/* Constructor */
	public Estacion(String nombre, double coordenadasGPS, double altitud, LocalDate fechaInstalacion,
			String responsable) {
		this.nombre = nombre;
		this.coordenadasGPS = coordenadasGPS;
		this.altitud = altitud;
		this.fechaInstalacion = fechaInstalacion;
		this.responsable = responsable;
		this.listaMediciones = new ArrayList<>();
	}

	/* Getters y setters */
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public double getCoordenadasGPS() {
		return coordenadasGPS;
	}

	public double getAltitud() {
		return altitud;
	}

	public LocalDate getFechaInstalacion() {
		return fechaInstalacion;
	}

	public ArrayList<Medicion> getListaMediciones() {
		return listaMediciones;
	}

	public void setListaMediciones(ArrayList<Medicion> listaMediciones) {
		this.listaMediciones = listaMediciones;
	}

	/* Método para agregar medicion */
	public boolean agregarMedicion(Medicion medicion) {
		boolean agregada = false;
		if (buscarMedicionHora(medicion.getFecha()) == null) {
			listaMediciones.add(medicion);
			agregada = true;
		} else {
			agregada = false;
		}
		return agregada;
	}

	/* Método para buscar una medicion por hora */
	public Medicion buscarMedicionHora(LocalDateTime hora) {
		Medicion medicionEncontrada = null;
		boolean encontrada = false;
		int i = 0;
		while (i < listaMediciones.size() && !encontrada) {
			Medicion medicion = listaMediciones.get(i);
			if (medicion.getFecha().equals(hora)) {
				medicionEncontrada = medicion;
				encontrada = true;
			}
			i++;
		}
		return medicionEncontrada;
	}

	/* Método para eliminar medicion por hora */
	public boolean eliminarMedicionHora(LocalDateTime hora) {
		boolean eliminada = false;
		Medicion medicion = buscarMedicionHora(hora);
		if (medicion != null) {
			listaMediciones.remove(medicion);
			eliminada = true;
		}
		return eliminada;
	}

	/* Método para listar Mediciones */
	public ArrayList<Medicion> listarMediciones() {
		for (Medicion medicion : listaMediciones) {
			System.out.println(medicion);
		}
		return this.listaMediciones;
	}

	/* Método toString */
	@Override
	public String toString() {
		return "Estacion [nombre=" + nombre + ", coordenadasGPS=" + coordenadasGPS + ", altitud=" + altitud
				+ ", fechaInstalacion=" + fechaInstalacion + ", responsable=" + responsable + ", listaMediciones="
				+ listaMediciones + "]\n";
	}

	/*Método para calcular pluviometría por fecha*/
	public double calcularPluviometriaDia(LocalDate fecha) {
		double pluviometriaTotal = 0;

		for (Medicion medicion : listaMediciones) {
			LocalDateTime fechaHora = medicion.getFecha();
			LocalDate fechaMedicion = fechaHora.toLocalDate();

			if (fechaMedicion.equals(fecha)) {
				double pluviometria = medicion.getPluviometria();
				pluviometriaTotal += pluviometria;
			}
		}

		return pluviometriaTotal;
	}

}
