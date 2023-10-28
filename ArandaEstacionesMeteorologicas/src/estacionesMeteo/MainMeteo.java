package estacionesMeteo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMeteo {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Estaciones listaEstaciones = new Estaciones();
		Estacion e1 = new Estacion("Almeria Oeste", 155, 12, LocalDate.parse("2022-12-12"), "paco");
		Estacion e2 = new Estacion("Málaga Norte", 205, 122, LocalDate.parse("2022-12-12"), "Julia");
		Medicion m1 = new Medicion(LocalDateTime.parse("2022-12-12T10:30:00"), 21, 2, 15);
		Medicion m2 = new Medicion(LocalDateTime.parse("2022-12-12T11:30:00"), 29, 5, 17);
		MedicionMontanosa m3 = new MedicionMontanosa(LocalDateTime.parse("2022-12-12T10:30:00"), 10, 5, 17, 3, "Sur");
		MedicionMontanosa m4 = new MedicionMontanosa(LocalDateTime.parse("2022-12-12T11:30:00"), 12, 25, 7, 5, "Oeste");
		listaEstaciones.agregarEstacion(e1);
		listaEstaciones.agregarEstacion(e2);
		e1.agregarMedicion(m1);
		e1.agregarMedicion(m2);
		e2.agregarMedicion(m3);
		e2.agregarMedicion(m4);

		int opcion = 0;
		boolean bandera;

		do {
			do {
				menu();
				try {
					bandera = false;
					opcion = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Opción no válida, vuelva a introducir una opción. \n");
					sc.nextLine();
					bandera = true;
				}
			} while (bandera);

			switch (opcion) {
			case 1: {
				listaEstaciones.agregarEstacion(pedirEstacionMeteorologica());
			}
				break;
			case 2: {
				listaEstaciones.listarEstaciones();
			}
				break;
			case 3: {
				String estacion = "";
				do {
					estacion = leerNombreEstacion();
					if (listaEstaciones.buscarEstacionNombre(estacion) != null) {
						menuParaTipoMedicion(listaEstaciones.buscarEstacionNombre(estacion));
					} else {
						System.out.println("Estación no encontrada. Inténtelo de nuevo.");
					}
				} while (listaEstaciones.buscarEstacionNombre(estacion) != null);
			}
				break;
			case 4: {
				String estacion = "";
				do {
					estacion = leerNombreEstacion();
					if (listaEstaciones.buscarEstacionNombre(estacion) != null) {
						listaEstaciones.eliminarEstacionNombre(estacion);
						System.out.println("Estación eliminada. " + estacion);
					} else {
						System.out.println("Estación no encontrada. Inténtelo de nuevo.");
					}
				} while (listaEstaciones.buscarEstacionNombre(estacion) != null);
			}
				break;
			case 5: {
				String archivo = leerNombreArchivo();
				listaEstaciones.importarInformacionDesdeArchivo(archivo);
			}
				break;
			case 6: {
				try {
					calcularTemperaturas(listaEstaciones);
				} catch (NoMedicionesEnFechaException e) {
					System.out.println("No se encontraron mediciones para la fecha especificada.");
				}
				break;
			}
			case 7: {
				try {
					calcularTemperaturasDia(listaEstaciones.getListaEstaciones());
				} catch (NoMedicionesEnFechaException e) {
					System.out.println(e.getMessage());
					System.out.println("No se encontraron mediciones para la fecha especificada.");
				}
			}
				break;
			case 8: {
				String nombreEstacion = leerNombreEstacion();
				Estacion estacion = listaEstaciones.buscarEstacionNombre(nombreEstacion);
				if (estacion != null) {
					LocalDate fechaInicio = leerFecha();
					LocalDate fechaFin = leerFecha();
					mostrarInformacionMeteorologicaPorRangoDeDias(estacion, fechaInicio, fechaFin);
				} else {
					System.out.println("Estación no encontrada.");
				}
			}
				break;
			case 9: {
				calcularPluviometriaDia(listaEstaciones.getListaEstaciones());
			}
				break;
			case 10: {
				System.out.println("Fin de programa.");
			}
				break;
			default: {
				System.out.println("Opción no válida, vuelva a introducir una opción. \n");
			}
				break;
			}
		} while (opcion != 10);

		sc.close();
	}

	/* Método para mostrar menú */
	public static void menu() {
		System.out.println("\n\tMenú\n");
		System.out.println("1. Añadir estación");
		System.out.println("2. Listar estaciones");
		System.out.println("3. Añadir medición");
		System.out.println("4. Eliminar estación por nombre");
		System.out.println("5. Importar desde un archivo de texto la información de una estación.");
		System.out.println("6. Calcular temperatura mínima y máxima de cada estación de un determinado día.");
		System.out.println(
				"7. Calcular la temperatura máxima y mínima en las estaciones durante un determinado día.");
		System.out.println(
				"8. Para una estación determinada, mostrar información meteorológica durante rango de días.");
		System.out.println("9. Calcule la estación con la máxima y la mínima pluviometría registrada para un determinado día.");
		System.out.println("10. Salir\n");
		System.out.print("Elija una opción: \n");
	}

	/* Submenú para añadir tipo medicion */
	public static void menuParaTipoMedicion(Estacion estacion) {
		Scanner sc = new Scanner(System.in);
		boolean bandera;

		int opcion = 0;
		boolean volver = false;
		do {
			System.out.println("Menú\n");
			System.out.println("1. Añadir medición");
			System.out.println("2. Añadir medición montañosa");
			System.out.println("3. Volver.\n");
			System.out.print("Elija una opción: \n");

			do {
				try {
					bandera = false;
					opcion = sc.nextInt();
				} catch (InputMismatchException e) {
					sc.nextLine();
					System.out.println("Valores mal introducidos. Pruebe otra vez.");
					bandera = true;
				}
			} while (bandera);
			switch (opcion) {
			case 1: {
				estacion.agregarMedicion(pedirMedicion());
			}
				break;
			case 2: {
				estacion.agregarMedicion(pedirMedicionMontanosa());
			}
				break;
			case 3: {
				volver = true;
			}
				break;
			default: {
				System.err.println("Opción no válida, vuelva a introducir una opción. \n");
			}
				break;
			}
		} while (opcion != 3 || !volver);
		System.out.print("Elija una opción: \n");
	}

	/* Método para pedir estación */
	public static Estacion pedirEstacionMeteorologica() {
		Scanner sc = new Scanner(System.in);
		String nombre = "", fechaInstalacion = "", responsable = "";
		double coordenadasGPS = 0, altitud = 0;
		LocalDate conversion = null;
		boolean bandera;
		do {
			bandera = false;
			try {
				System.out.println("Introduzca nombre de la estación:");
				nombre = sc.nextLine();
				System.out.println("Introduzca coordenadas (número decimal):");
				coordenadasGPS = sc.nextDouble();
				System.out.println("Introduzca altitud:");
				altitud = sc.nextDouble();
				sc.nextLine();
				/* Fecha nacimiento como String */
				System.out.println("Introduzca fecha de instalación (AAAA-MM-DD):");
				fechaInstalacion = sc.nextLine();
				try {
					conversion = LocalDate.parse(fechaInstalacion);
				} catch (DateTimeParseException ex) {
					System.out.println("Fecha de instalación incorrecta. Inténtelo de nuevo.");
					bandera = true;
					continue;
				}
				System.out.println("Introduzca el nombre del responsable de la estación:");
				responsable = sc.nextLine();

				System.out.println("Estación añadida satisfactoriamente.\n");
			} catch (InputMismatchException ex) {
				System.out.println("Valores mal introducidos. Inténtelo de nuevo.");
				bandera = true;
				sc.nextLine();
			}
		} while (bandera);
		return new Estacion(nombre, coordenadasGPS, altitud, conversion, responsable);
	}

	/* Método para añadir medicion */
	public static Medicion pedirMedicion() {
		Scanner sc = new Scanner(System.in);
		String fechaYHora = "";
		LocalDateTime conversion = null;
		int temperatura = 0, humedad = 0;
		double pluviometria = 0;
		boolean bandera;
		do {
			bandera = false;
			try {
				/* Fecha nacimiento como String */
				System.out.println("Introduzca fecha de la medición (AAAA-MM-DDTHH:mm:ss - Ej: 2022-12-12T10:30:00):");
				fechaYHora = sc.nextLine();
				try {
					conversion = LocalDateTime.parse(fechaYHora);
				} catch (DateTimeParseException ex) {
					System.out.println("Fecha de medición incorrecta. Inténtelo de nuevo.");
					bandera = true;
					continue;
				}
				System.out.println("Introduzca temperatura:");
				temperatura = sc.nextInt();
				System.out.println("Introduzca humedad:");
				humedad = sc.nextInt();
				System.out.println("Introduzca pluviometría:");
				pluviometria = sc.nextDouble();
				System.out.println("Medición añadida satisfactoriamente.\n");
			} catch (InputMismatchException ex) {
				System.out.println("Valores mal introducidos. Inténtelo de nuevo.");
				bandera = true;
				sc.nextLine();
			}
		} while (bandera);
		return new Medicion(conversion, temperatura, humedad, pluviometria);
	}

	/* Método para añadir medicion */
	public static MedicionMontanosa pedirMedicionMontanosa() {
		Scanner sc = new Scanner(System.in);
		String fechaYHora = "";
		LocalDateTime conversion = null;
		int temperatura = 0, humedad = 0;
		double pluviometria = 0;
		String direccionViento = "";
		double velocidadViento = 0;
		boolean bandera;
		do {
			bandera = false;
			try {
				/* Fecha nacimiento como String */
				System.out.println("Introduzca fecha de la medición (AAAA-MM-DDTHH:mm:ss - Ej: 2022-12-12T10:30:00):");
				fechaYHora = sc.nextLine();
				try {
					conversion = LocalDateTime.parse(fechaYHora);
				} catch (DateTimeParseException ex) {
					System.out.println("Fecha de medición incorrecta. Inténtelo de nuevo.");
					bandera = true;
					continue;
				}
				System.out.println("Introduzca temperatura:");
				temperatura = sc.nextInt();
				System.out.println("Introduzca humedad:");
				humedad = sc.nextInt();
				System.out.println("Introduzca pluviometría:");
				pluviometria = sc.nextDouble();
				System.out.println("Introduzca velocidad del viento:");
				velocidadViento = sc.nextDouble();
				sc.nextLine();
				System.out.println("Introduzca dirección del viento:");
				direccionViento = sc.nextLine();
				System.out.println("Medición añadida satisfactoriamente.\n");
			} catch (InputMismatchException ex) {
				System.out.println("Valores mal introducidos. Inténtelo de nuevo.");
				bandera = true;
				sc.nextLine();
			}
		} while (bandera);
		return new MedicionMontanosa(conversion, temperatura, humedad, pluviometria, velocidadViento, direccionViento);
	}

	/* Método para leer nombre de estación */
	public static String leerNombreEstacion() {
		Scanner sc = new Scanner(System.in);
		String lectorNombre = "";
		boolean bandera;
		do {
			try {
				bandera = false;
				System.out.println("Introduzca nombre de estación: ");
				lectorNombre = sc.nextLine();
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("Valores mal introducidos. Pruebe otra vez.");
				bandera = true;
			}
		} while (bandera);
		return lectorNombre;
	}

	/* Método para darle un nombre a un archivo a exportar */
	public static String leerNombreArchivo() {
		Scanner sc = new Scanner(System.in);
		String lectorNombreArchivo = "";
		boolean bandera;
		do {
			try {
				bandera = false;
				System.out.println("Introduzca el nombre del archivo (se agregará .txt por defecto): ");
				lectorNombreArchivo = sc.nextLine();
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("Valores mal introducidos. Pruebe otra vez.");
				bandera = true;
			}
		} while (bandera);
		/* Aquí añado .txt para la extensión del archivo */
		return lectorNombreArchivo + ".txt";
	}

	/* Método para leer fecha */
	public static LocalDate leerFecha() {
		Scanner sc = new Scanner(System.in);
		LocalDate fecha = null;
		boolean fechaValida = false;

		do {
			try {
				System.out.print("Ingrese una fecha (AAAA-MM-DD): ");
				String input = sc.nextLine();
				fecha = LocalDate.parse(input);
				fechaValida = true;
			} catch (DateTimeParseException e) {
				System.out.println("Fecha inválida. Vuelva a intentarlo.");
			} catch (IllegalArgumentException e) {
				System.out.println("Formato de fecha inválido. Vuelva a intentarlo.");
			}
		} while (!fechaValida);

		return fecha;
	}

	/* Método para leer fechaYHora */
	public static LocalDateTime leerFechaYHora() {
		Scanner sc = new Scanner(System.in);
		LocalDateTime dateTime = null;
		boolean dateTimeValido = false;

		do {
			try {
				System.out.print("Ingrese una fecha y hora (AAAA-MM-DDTHH:mm): ");
				String input = sc.nextLine();
				/*
				 * El formato se debe para hacer pruebas de forma mas rápida ya que es el
				 * formato inicial de los objetos creados
				 */
				dateTime = LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
				dateTimeValido = true;
			} catch (DateTimeParseException e) {
				System.out.println("Fecha y hora inválidas. Vuelva a intentarlo.");
			} catch (IllegalArgumentException e) {
				System.out.println("Formato de fecha y hora inválido. Vuelva a intentarlo.");
			}
		} while (!dateTimeValido);

		return dateTime;
	}

	/* Método para calcular */
	/* Calcula la máxima y la mínima para un día en concreto */
	public static void calcularTemperaturas(Estaciones listaEstacionesAux) throws NoMedicionesEnFechaException {
		LocalDateTime fechaHora = leerFechaYHora();

		int temperaturaMinima = Integer.MAX_VALUE;
		int temperaturaMaxima = Integer.MIN_VALUE;

		boolean medicionesEncontradas = false;

		for (Estacion estacion : listaEstacionesAux.getListaEstaciones()) {
			for (Medicion medicion : estacion.getListaMediciones()) {
				if (medicion.getFecha().equals(fechaHora)) {
					int temperatura = medicion.getTemperatura();
					temperaturaMinima = Math.min(temperaturaMinima, temperatura);
					temperaturaMaxima = Math.max(temperaturaMaxima, temperatura);
					medicionesEncontradas = true;
				}
			}
		}

		if (!medicionesEncontradas) {
			throw new NoMedicionesEnFechaException("No se encontraron mediciones para la fecha y hora especificadas.");
		}

		if (temperaturaMinima == Integer.MAX_VALUE || temperaturaMaxima == Integer.MIN_VALUE) {
			System.out.println("No hay mediciones válidas para la fecha y hora especificadas.");
		} else {
			System.out.println("Temperatura mínima: " + temperaturaMinima);
			System.out.println("Temperatura máxima: " + temperaturaMaxima);
		}
	}

	/* Método para calcular */
	/* Calcula la máxima y la mínima para un día en concreto de las estaciones */
	public static void calcularTemperaturasDia(ArrayList<Estacion> listaEstaciones)
			throws NoMedicionesEnFechaException {
		LocalDate fecha = leerFecha();

		int temperaturaMinima = Integer.MAX_VALUE;
		int temperaturaMaxima = Integer.MIN_VALUE;
		boolean medicionesEncontradas = false;

		for (Estacion estacion : listaEstaciones) {
			for (Medicion medicion : estacion.getListaMediciones()) {
				LocalDate fechaMedicion = medicion.getFecha().toLocalDate();
				if (fechaMedicion.equals(fecha)) {
					int temperatura = medicion.getTemperatura();
					temperaturaMinima = Math.min(temperaturaMinima, temperatura);
					temperaturaMaxima = Math.max(temperaturaMaxima, temperatura);
					medicionesEncontradas = true;
				}
			}
		}

		if (!medicionesEncontradas) {
			throw new NoMedicionesEnFechaException("No se encontraron mediciones para el día especificado.");
		} else {
			System.out.println("Temperatura mínima del día " + fecha + ": " + temperaturaMinima);
			System.out.println("Temperatura máxima del día " + fecha + ": " + temperaturaMaxima);
		}
	}

	/* Método para info por rango días */
	public static void mostrarInformacionMeteorologicaPorRangoDeDias(Estacion estacion, LocalDate fechaInicio,
			LocalDate fechaFin) {
		System.out.println("Información meteorológica para la estación: " + estacion.getNombre());
		System.out.println("Rango de fechas: " + fechaInicio + " - " + fechaFin);

		boolean informacionEncontrada = false;

		for (Medicion medicion : estacion.getListaMediciones()) {
			LocalDate fechaMedicion = medicion.getFecha().toLocalDate();

			if (fechaMedicion.isEqual(fechaInicio) || fechaMedicion.isEqual(fechaFin)
					|| (fechaMedicion.isAfter(fechaInicio) && fechaMedicion.isBefore(fechaFin))) {
				informacionEncontrada = true;
				System.out.println("Fecha y hora: " + medicion.getFecha());
				System.out.println("Temperatura: " + medicion.getTemperatura());
				System.out.println("Humedad: " + medicion.getHumedad());
				System.out.println("Pluviometría: " + medicion.getPluviometria());
				System.out.println();
			}
		}

		if (!informacionEncontrada) {
			System.out.println("No se encontró información meteorológica para el rango de fechas especificado.");
		}
	}

	/*Método para calcular pluviometría en un dia*/
	public static void calcularPluviometriaDia(ArrayList<Estacion> estaciones) {
		Scanner sc = new Scanner(System.in);
		LocalDate fecha = null;
		boolean bandera;

		do {
			bandera = false;
			try {
				System.out.println("Introduzca la fecha (AAAA-MM-DD):");
				fecha = LocalDate.parse(sc.nextLine());
			} catch (DateTimeParseException e) {
				System.out.println("Fecha incorrecta. Inténtelo de nuevo.");
				bandera = true;
			}
		} while (bandera);

		Estacion estacionMaxima = null;
		Estacion estacionMinima = null;
		double maxPluviometria = Double.MIN_VALUE;
		double minPluviometria = Double.MAX_VALUE;

		for (Estacion estacion : estaciones) {
			double pluviometria = estacion.calcularPluviometriaDia(fecha);
			if (pluviometria != -1) {
				if (pluviometria > maxPluviometria) {
					maxPluviometria = pluviometria;
					estacionMaxima = estacion;
				}
				if (pluviometria < minPluviometria) {
					minPluviometria = pluviometria;
					estacionMinima = estacion;
				}
			}
		}

		if (estacionMaxima != null && estacionMinima != null) {
			System.out.println("Estación con máxima pluviometría (" + fecha + "): " + estacionMaxima.getNombre());
			System.out.println("Estación con mínima pluviometría (" + fecha + "): " + estacionMinima.getNombre());
		} else {
			System.out.println("No se encontraron mediciones de pluviometría para la fecha especificada.");
		}
	}

}