package estacionesMeteo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Estaciones {
	private ArrayList<Estacion> listaEstaciones;

	/* Constructor */
	public Estaciones() {
		this.listaEstaciones = new ArrayList<>();

	}

	/* Getters y setters */
	public ArrayList<Estacion> getListaEstaciones() {
		return listaEstaciones;
	}

	public void setListaEstaciones(ArrayList<Estacion> listaEstaciones) {
		this.listaEstaciones = listaEstaciones;
	}

	/* Método toString */
	@Override
	public String toString() {
		return "Estaciones [listaEstaciones=" + listaEstaciones + "]\n";
	}

	/* Método para agregar estacion */
	public boolean agregarEstacion(Estacion estacion) {
		boolean agregada = false;
		if (buscarEstacionNombre(estacion.getNombre()) == null) {
			listaEstaciones.add(estacion);
			agregada = true;
		} else {
			agregada = false;
		}
		return agregada;
	}

	/* Método para buscar una medicion por hora */
	public Estacion buscarEstacionNombre(String nombre) {
		Estacion estacionEncontrada = null;
		boolean encontrada = false;
		int i = 0;
		while (i < listaEstaciones.size() && !encontrada) {
			Estacion estacion = listaEstaciones.get(i);
			if (estacion.getNombre().equals(nombre)) {
				estacionEncontrada = estacion;
				encontrada = true;
			}
			i++;
		}
		return estacionEncontrada;
	}

	/* Método para eliminar estacion por nombre */
	public boolean eliminarEstacionNombre(String nombre) {
		boolean eliminada = false;
		Estacion estacion = buscarEstacionNombre(nombre);
		if (estacion != null) {
			listaEstaciones.remove(estacion);
			eliminada = true;
		}
		return eliminada;
	}

	/* Método para listar Mediciones */
	public ArrayList<Estacion> listarEstaciones() {
		for (Estacion estacion : listaEstaciones) {
			System.out.println(estacion);
		}
		return this.listaEstaciones;
	}
	
	/* Método para importar informacion de una medicion desde archivo */
	public void importarInformacionDesdeArchivo(String nombreArchivo) {
		try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
			String linea;
			while ((linea = reader.readLine()) != null) {
				String[] datos = linea.split(",");
				if (datos.length >= 5) {
					try {
						String nombre = datos[0];
						double coordenadasGPS = Double.parseDouble(datos[1]);
						double altitud = Double.parseDouble(datos[2]);
						LocalDate fechaInstalacion = LocalDate.parse(datos[3]);
						String responsable = datos[4];

						Estacion estacion = buscarEstacionNombre(nombre);
						if (estacion == null) {
							estacion = new Estacion(nombre, coordenadasGPS, altitud, fechaInstalacion, responsable);
							agregarEstacion(estacion);
						}

						if (datos.length >= 9) {
							LocalDateTime fechaHora = LocalDateTime.parse(datos[5]);
							int temperatura = Integer.parseInt(datos[6]);
							int humedad = Integer.parseInt(datos[7]);
							double pluviometria = Double.parseDouble(datos[8]);

							Medicion medicion;
							if (datos.length == 12) {
								double velocidadViento = Double.parseDouble(datos[9]);
								String direccionViento = datos[10];

								medicion = new MedicionMontanosa(fechaHora, temperatura, humedad, pluviometria,
										velocidadViento, direccionViento);
							} else {
								medicion = new Medicion(fechaHora, temperatura, humedad, pluviometria);
							}

							estacion.agregarMedicion(medicion);
						}
					} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
						System.out.println("Error al procesar la línea: " + linea);
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Error al leer el archivo: " + e.getMessage());
		}
	}
}
