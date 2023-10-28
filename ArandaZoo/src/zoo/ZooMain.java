package zoo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ZooMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Zoo listaZoo = new Zoo();
		Acuatico a1 = new Acuatico(2, "paco", LocalDate.parse("2022-12-13"), "camarones");
		Acuatico a2 = new Acuatico(2, "paco", LocalDate.parse("2021-11-13"), "sardinas");
		Terrestre a3 = new Terrestre(2, "paco", LocalDate.parse("2002-10-13"), 30, "largo");
		Acuatico a4 = new Acuatico(2, "paco", LocalDate.parse("2021-11-13"), "sardinas");

		listaZoo.agregarAnimal(a1);
		listaZoo.agregarAnimal(a2);
		listaZoo.agregarAnimal(a3);
		listaZoo.agregarAnimal(a4);

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
				menuParaTipoAnimal(listaZoo);
			}
				break;
			case 2: {
				listaZoo.listarAnimales();
			}
				break;
			case 3: {
				int idAnimal = 0;
				do {
					idAnimal = leerId();
					if (listaZoo.buscarAnimalID(idAnimal) != null) {
						System.out.println(listaZoo.buscarAnimalID(idAnimal).toString());
					} else {
						System.out.println("Animal no encontrado. Inténtelo de nuevo.");
					}
				} while (listaZoo.buscarAnimalID(idAnimal) == null);
			}
				break;
			case 4: {
				int idAnimal = 0;
				do {
					idAnimal = leerId();
					if (listaZoo.buscarAnimalID(idAnimal) != null) {
						listaZoo.eliminarAnimal(idAnimal);
						System.out.println("El animal fue eliminado con Id " + idAnimal);
					} else {
						System.out.println("Animal no encontrado. Inténtelo de nuevo.");
					}
				} while (listaZoo.buscarAnimalID(idAnimal) != null);
			}
				break;
			case 5: {
				mostrarEntreDosFechas(listaZoo);
			}
				break;
			case 6: {
				listaZoo.exportarAnimalesEdad();
			}
				break;
			case 7: {
				System.out.println("Fin de programa.");
			}
				break;
			default: {
				System.out.println("Opción no válida, vuelva a introducir una opción. \n");
			}
				break;
			}
		} while (opcion != 7);

		sc.close();
	}

	/* Método para mostrar menú */
	public static void menu() {
		System.out.println("\n\tMenú\n");
		System.out.println("1. Añadir animal.");
		System.out.println("2. Listar animales.");
		System.out.println("3. Buscar animal por Id.");
		System.out.println("4. Eliminar animal por Id.");
		System.out.println("5. Mostrar animales entre dos fechas de entrada.");
		System.out.println("6. Exportar a ficheros por edad.");
		System.out.println("7. Salir\n");
		System.out.print("Elija una opción: \n");
	}

	/* Submenú para añadir tipo animal */
	public static void menuParaTipoAnimal(Zoo listaZooAux) {
		Scanner sc = new Scanner(System.in);
		boolean bandera;

		int opcion = 0;
		boolean volver = false;
		do {
			System.out.println("Menú\n");
			System.out.println("1. Añadir animal terrestre");
			System.out.println("2. Añadir animal acuático");
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
				listaZooAux.agregarAnimal(pedirAnimalTerrestre());
			}
				break;
			case 2: {
				listaZooAux.agregarAnimal(pedirAnimalAcuatico());
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

	/* Método para pedir Animal Acuatico */
	public static Acuatico pedirAnimalAcuatico() {
		Scanner sc = new Scanner(System.in);
		int edad = 0;
		String nombreCuidador = "", fechaEntrada = "", comida = "";
		LocalDate conversion = null;
		boolean bandera;
		do {
			bandera = false;
			try {
				System.out.println("Introduzca edad del animal:");
				edad = sc.nextInt();
				if (edad < 0) {
					throw new IllegalArgumentException("La edad no puede ser negativa.");
				}
				sc.nextLine();
				System.out.println("Introduzca el nombre del cuidador:");
				nombreCuidador = sc.nextLine();
				/* Fecha nacimiento como String */
				System.out.println("Introduzca fecha de entrada (AAAA-MM-DD):");
				fechaEntrada = sc.nextLine();
				try {
					conversion = LocalDate.parse(fechaEntrada);
				} catch (DateTimeParseException ex) {
					System.out.println("Fecha de entrada incorrecta. Inténtelo de nuevo.");
					bandera = true;
					continue;
				}
				System.out.println("Introduzca el alimento (sardinas-camarones):");
				comida = sc.nextLine();
				/* Controlamos los valores */
				while (!comida.equalsIgnoreCase("sardinas") && !comida.equalsIgnoreCase("camarones")) {
					System.out.println("Introduzca el alimento (sardinas-camarones):");
					comida = sc.nextLine();
				}
				System.out.println("Animal acuático añadido satisfactoriamente.\n");
			} catch (InputMismatchException ex) {
				System.out.println("Valores mal introducidos. Inténtelo de nuevo.");
				bandera = true;
				sc.nextLine();
			} catch (IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
				bandera = true;
			}
		} while (bandera);
		return new Acuatico(edad, nombreCuidador, conversion, comida);
	}

	/* Método para pedir Animal Terrestre */
	public static Terrestre pedirAnimalTerrestre() {
		Scanner sc = new Scanner(System.in);
		int edad = 0;
		String nombreCuidador = "", fechaEntrada = "", pelaje = "";
		double velocidad = 0;
		LocalDate conversion = null;
		boolean bandera;
		do {
			bandera = false;
			try {
				System.out.println("Introduzca edad del animal:");
				edad = sc.nextInt();
				if (edad < 0) {
					throw new IllegalArgumentException("La edad no puede ser negativa.");
				}
				sc.nextLine();
				System.out.println("Introduzca el nombre del cuidador:");
				nombreCuidador = sc.nextLine();
				/* Fecha nacimiento como String */
				System.out.println("Introduzca fecha de entrada (AAAA-MM-DD):");
				fechaEntrada = sc.nextLine();
				try {
					conversion = LocalDate.parse(fechaEntrada);
				} catch (DateTimeParseException ex) {
					System.out.println("Fecha de entrada incorrecta. Inténtelo de nuevo.");
					bandera = true;
					continue;
				}
				System.out.println("Introduzca la velocidad media del animal:");
				velocidad = sc.nextDouble();
				if (velocidad < 0) {
					throw new IllegalArgumentException("La velocidad no puede ser negativa.");
				}
				sc.nextLine();
				System.out.println("Introduzca pelaje (largo-corto): ");
				pelaje = sc.nextLine();
				/* Controlamos los valores */
				while (!pelaje.equalsIgnoreCase("largo") && !pelaje.equalsIgnoreCase("corto")) {
					System.out.println("Introduzca pelaje (largo-corto): ");
					pelaje = sc.nextLine();
				}
				System.out.println("Animal terrestre añadido satisfactoriamente.\n");
			} catch (InputMismatchException ex) {
				System.out.println("Valores mal introducidos. Inténtelo de nuevo.");
				bandera = true;
				sc.nextLine();
			} catch (IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
				bandera = true;
			}
		} while (bandera);
		return new Terrestre(edad, nombreCuidador, conversion, velocidad, pelaje);
	}

	/* Método para leer id */
	public static int leerId() {
		Scanner sc = new Scanner(System.in);
		int id = 0;
		boolean bandera = true;
		while (bandera) {
			try {
				System.out.println("Ingrese el id del animal: ");
				id = sc.nextInt();
				bandera = false;
			} catch (InputMismatchException ex) {
				System.out.println("El id está mal introducido, vuelva a intentarlo.");
				ex.printStackTrace();
			}
		}
		return id;
	}

	/* Método para comparar animales entre dos fechas */
	public static void mostrarEntreDosFechas(Zoo listaZooAux) {
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		try {
			System.out.println("Ingresa la primera fecha de entrada (formato: AAAA-MM-DD):");
			LocalDate fechaInicio = LocalDate.parse(sc.nextLine(), formato);

			System.out.println("Ingresa la segunda fecha de entrada (formato: AAAA-MM-DD):");
			LocalDate fechaFin = LocalDate.parse(sc.nextLine(), formato);

			ArrayList<Animal> animalesEntreFechas = listaZooAux.buscarAnimalesEntreFechas(fechaInicio, fechaFin);
			if (animalesEntreFechas.isEmpty()) {
				System.out.println("No hay animales entre las fechas proporcionadas.");
			} else {
				System.out.println("Animales entre " + fechaInicio + " y " + fechaFin + ":");
				for (Animal animal : animalesEntreFechas) {
					System.out.println(animal.toString());
				}
			}
		} catch (DateTimeParseException e) {
			System.out.println("Error: El formato de fecha es incorrecto. Por favor, utiliza el formato AAAA-MM-DD.");
		}
	}
}
