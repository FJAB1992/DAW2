package almacenBebidas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author Francisco Javier Aranda Barba
 * @version 9/6/2023 1.0 Objetivo:Clase MainAlmacen
 *
 */
public class MainAlmacen {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Almacen almacen = new Almacen();

		Agua agua1 = new Agua(150, 0.9, LocalDate.now(), "Aquabona", 1, "Sacalm");
		Agua agua2 = new Agua(50, 0.45, LocalDate.now(), "AquaSectum", 2, "Alora");
		Agua agua3 = new Agua(250, 0.68, LocalDate.now(), "Lidl", 3, "Motril");
		Agua agua4 = new Agua(10, 0.7, LocalDate.now(), "FontBella", 1, "Granada");
		BebidaAzucarada azucar1 = new BebidaAzucarada(15, 0.4, LocalDate.now(), "CocaCola", 1, 0.8, true);
		BebidaAzucarada azucar2 = new BebidaAzucarada(150, 0.5, LocalDate.parse("2023-09-08"), "Fanta", 2, 0.2, false);
		BebidaAzucarada azucar3 = new BebidaAzucarada(100, 0.3, LocalDate.parse("2023-12-08"), "Nestea", 3, 0.5, false);
		BebidaAzucarada azucar4 = new BebidaAzucarada(120, 0.2, LocalDate.parse("2022-02-08"), "Cacaolat", 1, 0.4,
				true);

		almacen.agregarBebida(azucar1);
		almacen.agregarBebida(azucar2);
		almacen.agregarBebida(azucar3);
		almacen.agregarBebida(azucar4);
		almacen.agregarBebida(agua1);
		almacen.agregarBebida(agua2);
		almacen.agregarBebida(agua3);
		almacen.agregarBebida(agua4);

		int opcion = 0;
		boolean bandera;

		do {
			try {
				bandera = false;
				menu();
				do {
					opcion = sc.nextInt();
					switch (opcion) {
					case 1: {
						menuParaTipoBebida(almacen);
					}
						break;
					case 2: {
						System.out.println(
								"El precio total de todas las bebidas es: " + almacen.calcularPrecioTodasBebidas());
					}
						break;
					case 3: {
						String marca = leerMarca();
						System.out.println(
								"El precio total de la marca " + marca + " es: " + almacen.calcularPrecioMarca(marca));
					}
						break;
					case 4: {
						int estante = leerEstante();
						System.out.println("El precio total de la estantería " + estante + " es: "
								+ almacen.calcularPrecioEstanteria(estante));
					}
						break;
					case 5: {
						String bebidaId = leerID();
						int cantidadRepuesta = leerCantidad();
						almacen.reponerProducto(bebidaId, cantidadRepuesta);
					}
						break;
					case 6: {
						String bebidaId = leerID();
						almacen.eliminarProducto(bebidaId);
					}
						break;
					case 7: {
						almacen.mostrarInformacionOrdenadaPorPrecio();
					}
						break;
					case 8: {
						String nombreArchivo = leerNombreArchivo();
						almacen.exportarAgua(nombreArchivo);
					}
						break;
					case 9: {
						String bebidaId = leerID();
						int cantidadRetirar = leerCantidad();
						almacen.retirarBebidas(bebidaId, cantidadRetirar);
					}
						break;
					case 10: {
						almacen.listarBebidas();
					}
						break;
					case 11: {
						String bebidaId = leerID();
						almacen.mostrarBebidaPorId(bebidaId);
					}
						break;
					case 12: {
						System.out.println(almacen.mostrarPrecioAgua());
					}
						break;
					case 13: {
						System.out.println(almacen.mostrarPrecioBebidasAzu());
					}
						break;
					case 14: {
						String marca = leerMarca();
						System.out.println(almacen.calcularPrecioAguaPorMarca(marca));
					}
						break;
					case 15: {
						System.out.println(almacen.calcularMediaPrecios());
					}
						break;
					case 16: {
						almacen.productosEncimaMedia();
					}
						break;
					case 17: {
						almacen.productosDebajoMedia();
					}
						break;
					case 18: {
						almacen.marcasPrecioMayorMedia();
					}
						break;
					case 19: {
						almacen.marcasPrecioMenorMedia();
					}
						break;
					case 20: {
						System.out.println(almacen.productoMasCaro());
					}
						break;
					case 21: {
						System.out.println(almacen.productoMasBarato());
					}
						break;
					case 22: {
						almacen.mostrarInformacionOrdenadaPorFecha();
					}
						break;
					case 23: {
						almacen.mostrarInformacionOrdenadaPorUnidades();
					}
						break;
					case 24: {
						String nombreArchivo = leerNombreArchivo();
						almacen.exportarAguasPorPrecio(nombreArchivo);
					}
						break;
					case 25: {
						String nombreArchivo = leerNombreArchivo();
						almacen.exportarBebidasPorFecha(nombreArchivo);
					}
						break;
					case 26: {
						System.out.println("Fin de programa.");
					}
						break;
					default: {
						System.out.println("Opción no válida, vuelva a introducir una opción. \n");
					}
						break;
					}
				} while (opcion != 26);
			} catch (InputMismatchException e) {
				sc.nextLine();
				e.printStackTrace();
				bandera = true;
			}
		} while (bandera);
		sc.close();
	}

	/* Método para mostrar menú */
	public static void menu() {
		System.out.println("\n\tMenú\n");
		System.out.println("1. Añadir bebida al almacen");
		System.out.println("2. Calcular precio total de bebidas del almacén");
		System.out.println("3. Calcular el precio total de una marca de bebida");
		System.out.println("4. Calcular el precio total de bebidas en una estantería");
		System.out.println("5. Reponer producto");
		System.out.println("6. Eliminar un producto");
		System.out.println("7. Mostrar información ordenada por precio");
		System.out.println("8. Importar agua a un archivo");
		System.out.println("----------------------------------");
		System.out.println("9. Retirar unidades de producto");
		System.out.println("10. Listar bebidas (sin orden).");
		System.out.println("11. Mostrar bebida por ID");
		System.out.println("12. Mostrar precio agua");
		System.out.println("13. Mostrar precio bebidas azucaradas");
		System.out.println("14. Calcular precio agua por marca");
		System.out.println("15. Calcular media precio de bebidas");
		System.out.println("16. Mostrar productos por encima de la media");
		System.out.println("17. Mostrar productos por debajo de la media");
		System.out.println("18. Mostrar marcas por encima de la media");
		System.out.println("19. Mostrar marcas por debajo de la media");
		System.out.println("20. Mostrar bebida mas cara");
		System.out.println("21. Mostrar bebida mas barata");
		System.out.println("22. Mostrar información ordenada por fecha");
		System.out.println("23. Mostrar información ordenada por cantidad");
		System.out.println("24. Exportar doc aguas por precio");
		System.out.println("25. Exportar doc bebidas por fecha");
		System.out.println("26. Salir.\n");
		System.out.print("Elija una opción: \n");
	}

	/* Submenú para añadir bebida */
	public static void menuParaTipoBebida(Almacen alamcenAux) {
		Scanner sc = new Scanner(System.in);
		boolean bandera;

		int opcion = 0;
		boolean volver = false;
		do {
			System.out.println("Menú\n");
			System.out.println("1. Añadir Agua");
			System.out.println("2. Añadir Bebida azucarada");
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
				alamcenAux.agregarBebida(pedirAgua());
			}
				break;
			case 2: {
				alamcenAux.agregarBebida(pedirBebidaAzu());
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

	/* Método para añadir agua */
	public static Agua pedirAgua() {
		Scanner sc = new Scanner(System.in);
		int unidades = 0, estanteria = 0;
		String fechaEntrada = "";
		double precio = 0;
		String marca = "", manantial = "";
		boolean bandera;
		do {
			try {
				bandera = false;
				System.out.println("Introduzca las unidades de agua:");
				unidades = sc.nextInt();
				System.out.println("Introduzca precio:");
				precio = sc.nextDouble();
				sc.nextLine();
				/* Fecha entrada como String */
				System.out.println("Introduzca fecha de entrada:");
				fechaEntrada = sc.nextLine();
				System.out.println("Introduzca marca:");
				marca = sc.nextLine();
				System.out.println("Introduzca en qué estantería depositarla(número entero):");
				estanteria = sc.nextInt();
				sc.nextLine();
				System.out.println("Introduzca manantial:");
				manantial = sc.nextLine();
				System.out.println("Agua añadida satisfactoriamente.\n");
			} catch (InputMismatchException e) {
				sc.nextLine();
				bandera = true;
				System.out.println("Valores mal introducidos. Pruebe otra vez.");
			}
		} while (bandera);
		/* Conversión de String a localDate */
		Agua aqua1 = new Agua(unidades, precio, LocalDate.parse(fechaEntrada), marca, estanteria, manantial);
		return aqua1;
	}

	/* Método para añadir bebida azucarada */
	public static BebidaAzucarada pedirBebidaAzu() {
		Scanner sc = new Scanner(System.in);
		int unidades = 0, estanteria = 0, tienePromo = 0;
		LocalDate fechaEntrada = LocalDate.now();
		double precio = 0, porcentajeAzucar = 0;
		String marca = "";
		boolean bandera, promocion = false;
		do {
			try {
				bandera = false;
				System.out.println("Introduzca las unidades bebida azucarada:");
				unidades = sc.nextInt();
				System.out.println("Introduzca precio:");
				precio = sc.nextDouble();
				sc.nextLine();
				System.out.println("Introduzca marca:");
				marca = sc.nextLine();
				System.out.println("Introduzca en qué estantería depositarla(número entero):");
				estanteria = sc.nextInt();
				sc.nextLine();
				System.out.println("Introduzca porcentaje de azúcar:");
				porcentajeAzucar = sc.nextDouble();
				System.out.println("Introduzca si tiene o no promocion (Pulse 1 si tiene promocion):");
				tienePromo = sc.nextInt();
				if (tienePromo == 1) {
					promocion = true;
				} else {
					promocion = false;
				}
				System.out.println("Bebida añadida satisfactoriamente.\n");
			} catch (InputMismatchException e) {
				sc.nextLine();
				bandera = true;
				System.out.println("Valores mal introducidos. Pruebe otra vez.");
			}
		} while (bandera);
		BebidaAzucarada bebida1 = new BebidaAzucarada(unidades, precio, fechaEntrada, marca, estanteria,
				porcentajeAzucar, promocion);
		return bebida1;
	}

	/* Método para leer ID */
	public static String leerID() {
		Scanner sc = new Scanner(System.in);
		String lectorID = "";
		boolean bandera;
		do {
			try {
				bandera = false;
				System.out.println("Introduzca el ID de la bebida (es un String completo ej: Agua ID: 0001): ");
				lectorID = sc.nextLine();
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("Valores mal introducidos. Pruebe otra vez.");
				bandera = true;
			}
		} while (bandera);
		return lectorID;
	}

	/* Método para leer Marca */
	public static String leerMarca() {
		Scanner sc = new Scanner(System.in);
		String lectorMarca = "";
		boolean bandera;
		do {
			try {
				bandera = false;
				System.out.println("Introduzca la marca de la bebida: ");
				lectorMarca = sc.nextLine();
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("Valores mal introducidos. Pruebe otra vez.");
				bandera = true;
			}
		} while (bandera);
		return lectorMarca;
	}

	/* Método para leer número de estantería */
	public static int leerEstante() {
		Scanner sc = new Scanner(System.in);
		int lectorEstante = 0;
		boolean bandera;
		do {
			try {
				bandera = false;
				System.out.println("Introduzca la estantería donde se encuentra la bebida: ");
				lectorEstante = sc.nextInt();
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("Valores mal introducidos. Pruebe otra vez.");
				bandera = true;
			}
		} while (bandera);
		return lectorEstante;
	}

	/* Método para leer cantidad bebidas */
	public static int leerCantidad() {
		Scanner sc = new Scanner(System.in);
		int lectorCantidad = 0;
		boolean bandera;
		do {
			try {
				bandera = false;
				System.out.println("Introduzca cantidad de bebidas: ");
				lectorCantidad = sc.nextInt();
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("Valores mal introducidos. Pruebe otra vez.");
				bandera = true;
			}
		} while (bandera);
		return lectorCantidad;
	}

	/* Método para darle un nombre a un archivo a exportar */
	public static String leerNombreArchivo() {
		Scanner sc = new Scanner(System.in);
		String lectorNombreArchivo = "";
		boolean bandera;
		do {
			try {
				bandera = false;
				System.out.println("Introduzca el nombre del archivo (se generará un txt): ");
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

}
