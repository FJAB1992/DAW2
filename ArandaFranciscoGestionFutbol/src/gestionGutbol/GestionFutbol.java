package gestionGutbol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author Francisco Javier Aranda Barba
 * @version 3/06/2023 1.0 Objetivo:Clase GestionFutbol
 *
 */
/*
 * Cosas a modificar: -si el socio tiene un pago pendiente no borrarlo
 * 
 * Método para verificar el mes -Forma de introducir el mes?
 */
public class GestionFutbol {
	
	public static void main(String[] args) {
		Connection pruebaConexion = conexion();
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Conexión establecida");

			boolean salir = false;
			while (!salir) {
				mostrarMenuPrincipal();
				try {
					int opcion = sc.nextInt();

					switch (opcion) {
					case 1: {
						gestionarSocio(pruebaConexion);
						break;
					}
					case 2: {
						listarSocios(pruebaConexion);
						break;
					}
					case 3: {
						gestionarCuotas(pruebaConexion);
						break;
					}
					case 4: {
						salir = true;
						break;
					}
					default: {
						System.out.println("Opción no válida. Vuelva a intentarlo.");
						break;
					}
					}
				} catch (InputMismatchException mis) {
					sc.nextLine();
					System.out.println("Opción no válida. Vuelva a intentarlo.");
					mis.printStackTrace();
				}
			}
			pruebaConexion.close();
			System.out.println("Conexión cerrada");
		} catch (SQLException e) {
			System.out.println("Error al conectar a la base de datos: " + e.getMessage());
			e.printStackTrace();
		}
		sc.close();

	}

	/* Método para la conexión */
	public static Connection conexion() {
		Connection conexion;
		String url = "jdbc:mysql://localhost:3306/ClubFutbol?useSSL=false&allowPublicKeyRetrieval=true";
		String user = "prog";
		String pass = "programacion";
		try {
			conexion = DriverManager.getConnection(url, user, pass);
		} catch (SQLException ex) {
			conexion = null;
			ex.printStackTrace();
			System.out.println("SQLException : " + ex.getMessage());
			System.out.println("SQLState : " + ex.getSQLState());
			System.out.println("VendorError : " + ex.getErrorCode());
		}
		return conexion;
	}

	/* Método para mostrar un menú inicial */
	public static void mostrarMenuPrincipal() {
		System.out.println("\t----- Menú Principal -----");
		System.out.println("\n1.Gestion socio.");
		System.out.println("2.Listado socios.");
		System.out.println("3.Gestion cuotas.");
		System.out.println("4.Salir\n");
		System.out.print("\tSeleccione una opción: ");
	}

	/* Menú para gestionar socio */
	public static void gestionarSocio(Connection conexion) {
		Scanner sc = new Scanner(System.in);
		boolean volver = false;
		while (!volver) {
			System.out.println("\t----- Gestión de Socios -----");
			System.out.println("\n1.Alta socios.");
			System.out.println("2.Modificación socios.");
			System.out.println("3.Baja socios.");
			System.out.println("4.Volver al Menú Principal\n");
			System.out.print("\tSeleccione una opción: ");
			try {
				int opcion = sc.nextInt();

				switch (opcion) {
				case 1: {
					altaSocio(conexion);
					break;
				}
				case 2: {
					modificarSocio(conexion);
					break;
				}
				case 3: {
					borrarSocio(conexion);
					break;
				}
				case 4: {
					volver = true;
					break;
				}
				default: {
					System.out.println("Opción no válida. Vuelva a intentarlo.");
					break;
				}
				}
			} catch (InputMismatchException mis) {
				sc.nextLine();
				System.out.println("Opción no válida. Vuelva a intentarlo.");
				mis.printStackTrace();
			}
		}
	}

	/* Menú para gestionar cuotas */
	public static void gestionarCuotas(Connection conexion) {
		Scanner sc = new Scanner(System.in);
		boolean volver = false;
		while (!volver) {
			System.out.println("\t----- Gestión de Cuotas -----");
			System.out.println("\n1.Generar cuota de un determinado mes para todos los socios.");
			System.out.println("2.Pago de un socio un determinado mes.");
			System.out.println("3.Listado de pagos pendientes de un socio total.");
			System.out.println("4.Volver al Menú Principal\n");
			System.out.print("\tSeleccione una opción: ");
			try {
				int opcion = sc.nextInt();
				switch (opcion) {
				case 1: {
					generarCuota(conexion);
					break;
				}
				case 2: {
					int numSocio = leerNumSocio();
					pagarCuota(conexion, numSocio);
					break;
				}
				case 3: {
					int numSocio = leerNumSocio();
					mostrarCuotasPendientes(conexion, numSocio);
					break;
				}
				case 4: {
					volver = true;
					break;
				}
				default: {
					System.out.println("Opción no válida. Vuelva a intentarlo.");
					break;
				}
				}
			} catch (InputMismatchException mis) {
				System.out.println("Opción no válida. Vuelva a intentarlo.");
				sc.nextLine();
				mis.printStackTrace();
			}
		}
	}

	/* Método para crear excepcion para regular el DNI */
	public static void verificarDni(String dni) throws ExcepcionDNI {
		if (!dni.matches("^[0-9]{8}[A-Za-z]{1}")) {
			throw new ExcepcionDNI(dni);
		}
	}

	/* Método para ingresar DNI */
	public static String obtenerDNI() {
		Scanner sc = new Scanner(System.in);
		String dni = "";
		boolean bandera = true;
		while (bandera) {
			try {
				System.out.print("Ingrese el DNI del socio (8 enteros y una letra): ");
				dni = sc.next();
				verificarDni(dni);
				bandera = false;
			} catch (ExcepcionDNI ex) {
				System.out.println("El DNI está mal introducido, vuelva a intentarlo.");
				ex.printStackTrace();
			}
		}
		return dni;
	}

	/* Método para pedir socio */
	public static Socio pedirDatosSocio() {
		Scanner sc = new Scanner(System.in);
		String dni = obtenerDNI(), nombre = "", cuentaPagos = "";
		LocalDate fechaNacimiento = leerFechaNacimiento(sc);
		boolean alCorrienDePago = true;
		System.out.print("Ingrese el nombre del socio: ");
		nombre = sc.nextLine();
		System.out.print("Ingrese la cuenta del socio: ");
		cuentaPagos = sc.nextLine();
		Socio datosSocio = new Socio(dni, fechaNacimiento, nombre, cuentaPagos, alCorrienDePago);
		return datosSocio;
	}

	/* Método para leer y verificar el formato de fecha pasando un Scanner */
	public static LocalDate leerFechaNacimiento(Scanner sc) {
		boolean formatoFechaCorrecto = false;
		LocalDate fechaNacimiento = null;

		while (!formatoFechaCorrecto) {
			System.out.print("Ingrese la fecha de nacimiento (AAAA-MM-DD): ");
			String fechaNacimientoTexto = sc.nextLine();

			try {
				fechaNacimiento = LocalDate.parse(fechaNacimientoTexto);
				formatoFechaCorrecto = true;
			} catch (Exception e) {
				System.out.println("Formato de fecha inválido");
			}
		}
		return fechaNacimiento;
	}

	/* Método para dar de alta a un socio */
	public static void altaSocio(Connection conexion) {
		Scanner sc = new Scanner(System.in);
		String query = "INSERT INTO Socios (dni, fechaNacimiento, nombre, CuentaPagos,AlCorrienteDePago) VALUES (?,?, ?, ?, ?)";
		System.out.println("\n----- Alta de Socio -----");
		Socio socio1 = pedirDatosSocio();
		try (PreparedStatement consulta = conexion.prepareStatement(query)) {
			consulta.setString(1, socio1.getDni());
			/*
			 * Convierte LocalDate de Java a java.sqlDate Necesarias las bibliotecas
			 * java.sql.Date y SQLException, pero esa se va a usar en este tipo de
			 * ejercicios...
			 */
			consulta.setDate(2, java.sql.Date.valueOf(socio1.getFechaNacimiento()));
			consulta.setString(3, socio1.getNombre());
			consulta.setString(4, socio1.getCuentaPagos());
			consulta.setBoolean(5, socio1.isAlCorrienDePago());

			int filasAfectadas = consulta.executeUpdate();
			if (filasAfectadas > 0) {
				System.out.println("Socio agregado exitosamente.");
			}
		} catch (SQLException e) {
			System.out.println("Error al agregar el socio: " + e.getMessage());
		}
	}

	/* Método para dar de baja un socio */
	public static void borrarSocio(Connection conexion) {
		Scanner sc = new Scanner(System.in);
		String query = "DELETE FROM Socios WHERE NumSocio = ?";
		System.out.println("\n----- Borrar Socio -----");
		System.out.println("Ingrese el número de socio a borrar: ");
		int numSocio = sc.nextInt();

		try (PreparedStatement consulta = conexion.prepareStatement(query)) {
			consulta.setInt(1, numSocio);

			int filasAfectadas = consulta.executeUpdate();
			if (filasAfectadas > 0) {
				System.out.println("Socio borrado exitosamente.");
			} else {
				System.out.println("No se encontró un socio con el número de socio " + numSocio + ".");
			}
		} catch (SQLException e) {
			System.out.println("Error al borrar el socio: " + e.getMessage());
		}
	}

	/* Método para modificar socio */

	/*
	 * Nota: no se modifica el DNI, pero si me lo pide pues estoy usando el método
	 * pedirDatosSocio, será otro dato más para verificar su identidad
	 */
	public static void modificarSocio(Connection conexion) {
		String query = "UPDATE Socios SET fechaNacimiento = ?, nombre = ?, CuentaPagos = ?, AlCorrienteDePago = ? WHERE NumSocio = ?";
		System.out.println("\n----- Modificar Socio -----");
		int numSocio = leerNumSocio();

		Socio socioExistente = obtenerSocio(conexion, numSocio);
		if (socioExistente != null) {
			Socio socioModificado = pedirDatosSocio();

			try (PreparedStatement consulta = conexion.prepareStatement(query)) {
				consulta.setDate(1, java.sql.Date.valueOf(socioModificado.getFechaNacimiento()));
				consulta.setString(2, socioModificado.getNombre());
				consulta.setString(3, socioModificado.getCuentaPagos());
				consulta.setBoolean(4, socioModificado.isAlCorrienDePago());
				consulta.setInt(5, numSocio);

				int filasAfectadas = consulta.executeUpdate();
				if (filasAfectadas > 0) {
					System.out.println("Socio modificado exitosamente.");
				} else {
					System.out.println("No se encontró un socio con el número de socio " + numSocio + ".");
				}
			} catch (SQLException e) {
				System.out.println("Error al modificar el socio: " + e.getMessage());
			}
		} else {
			System.out.println("No se encontró un socio con el número de socio " + numSocio + ".");
		}
	}

	/*
	 * Método para verificar si el socio existe en la base de datos dado su numero
	 * de socio
	 */
	private static Socio obtenerSocio(Connection conexion, int numSocio) {
		String query = "SELECT * FROM Socios WHERE NumSocio = ?";
		Socio socio = null;

		try (PreparedStatement consulta = conexion.prepareStatement(query)) {
			consulta.setInt(1, numSocio);

			try (ResultSet resultado = consulta.executeQuery()) {
				if (resultado.next()) {
					String dni = resultado.getString("DNI");
					LocalDate fechaNacimiento = resultado.getDate("fechaNacimiento").toLocalDate();
					String nombre = resultado.getString("nombre");
					String cuentaPagos = resultado.getString("CuentaPagos");
					boolean alCorrienteDePago = resultado.getBoolean("AlCorrienteDePago");

					socio = new Socio(dni, fechaNacimiento, nombre, cuentaPagos, alCorrienteDePago);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error al obtener el socio: " + e.getMessage());
		}

		return socio;
	}

	/* Método para listar socios */
	public static ResultSet listarSocios(Connection conexion) {
		String query = "SELECT * FROM Socios";
		ResultSet resultados = null;
		try (PreparedStatement consulta = conexion.prepareStatement(query)) {
			resultados = consulta.executeQuery();
			System.out.println("\n\tSocios\n");
			while (resultados.next()) {
				int numSocio = resultados.getInt("NumSocio");
				String dni = resultados.getString("DNI");
				LocalDate fechaNacimiento = resultados.getDate("fechaNacimiento").toLocalDate();
				String nombre = resultados.getString("nombre");
				String cuentaPagos = resultados.getString("cuentaPagos");
				boolean alCorrienDePago = resultados.getBoolean("AlCorrienteDePago");
				System.out.println("Número de socio: " + numSocio);
				System.out.println("DNI: " + dni);
				System.out.println("Fecha de nacimiento: " + fechaNacimiento);
				System.out.println("Nombre del socio: " + nombre);
				System.out.println("Pagos contados: " + cuentaPagos);
				System.out.println("Al corriente de pago: " + alCorrienDePago + "\n");
			}
		} catch (SQLException e) {
			System.out.println("Error al mostrar los socios: " + e.getMessage());
		}
		return resultados;
	}

	/* Método para leer mes y año por teclado */
	public static String leerMesPorTeclado() {
		enum Mes {
			ENERO, FEBRERO, MARZO, ABRIL, MAYO, JUNIO, JULIO, AGOSTO, SEPTIEMBRE, OCTUBRE, NOVIEMBRE, DICIEMBRE
		}
		Scanner sc = new Scanner(System.in);
		System.out.print("Ingrese el mes de la cuota: ");
		int mesNumero = 0;
		int año = 0;
		String resultado = "";
		
		/*Me sigue leyendo solo el string*/
		
		try {
			mesNumero = sc.nextInt();
			sc.nextLine();
			System.out.print("Ingrese el año: ");
			año = sc.nextInt();
			sc.nextLine();
			
			/* Validar el número de mes ingresado*/
			if (mesNumero < 1 || mesNumero > 12) {
				resultado = "Número de mes inválido";
			} else {
				/* Obtener el mes correspondiente al número ingresado*/
				Mes mes = Mes.values()[mesNumero - 1];
				resultado = mes.toString() + " " + año;
			}
		} catch (InputMismatchException e) {
			resultado = "Entrada inválida. Debe ingresar un número entero.";
		}
		
		return resultado;
	}

	/* Método para obtener el número de socio */
	public static int leerNumSocio() {
		Scanner sc = new Scanner(System.in);
		int numSocio;
		try {
			System.out.print("Ingrese el número de socio: ");
			numSocio = sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Error: Ingrese un número entero válido.");
			sc.nextLine();
			numSocio = leerNumSocio();
		}
		return numSocio;
	}

	/* Método para generar la cuota de un determinado mes para todos los socios */
	public static void generarCuota(Connection conexion) {
		System.out.println("\n----- Generar Cuota -----");
		String mes = leerMesPorTeclado();

		/* SELECCIONAMOS TODOS LOS SOCIOS */
		String query = "SELECT NumSocio FROM Socios";
		try (PreparedStatement consulta = conexion.prepareStatement(query)) {
			ResultSet resultados = consulta.executeQuery();
			while (resultados.next()) {
				int numSocio = resultados.getInt("NumSocio");
				generarCuotaSocio(conexion, numSocio, mes);
			}
			System.out.println("Cuotas generadas exitosamente para el mes de " + mes);
		} catch (SQLException e) {
			System.out.println("Error al generar las cuotas: " + e.getMessage());
		}
	}

	/*
	 * Método para generar la cuota de un determinado mes para un socio específico
	 */
	public static void generarCuotaSocio(Connection conexion, int numSocio, String mes) {
		String query = "INSERT INTO Cuotas (NumSocio, Mes, Pagado) VALUES (?, ?, false)";
		try (PreparedStatement consulta = conexion.prepareStatement(query)) {
			consulta.setInt(1, numSocio);
			consulta.setString(2, mes);
			consulta.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al generar la cuota para el socio " + numSocio + ": " + e.getMessage());
		}
	}

	/* Método para realizar el pago de una cuota de un socio */
	public static void pagarCuota(Connection conexion, int numSocio) {
		System.out.println("\n----- Pago de Cuota -----");
		String mes = leerMesPorTeclado();

		/* Verificar si la cuota a pagar existe, pasando mes y numero de socio */
		if (verificarCuotaPendiente(conexion, numSocio, mes)) {
			/* Actualizamos estado de la cuota a pagado */
			String query = "UPDATE Cuotas SET Pagado = true WHERE NumSocio = ? AND Mes = ?";
			try (PreparedStatement consulta = conexion.prepareStatement(query)) {
				consulta.setInt(1, numSocio);
				consulta.setString(2, mes);
				consulta.executeUpdate();
				System.out.println("Cuota para el mes de " + mes + " del socio " + numSocio + " pagada exitosamente.");
			} catch (SQLException e) {
				System.out.println("Error al realizar el pago de la cuota: " + e.getMessage());
			}
		} else {
			System.out.println("No se encontró una cuota pendiente para el socio " + numSocio + " y el mes de " + mes);
		}
	}

	/*
	 * Método para verificar si existe una cuota pendiente para un socio y un mes
	 * específicos
	 */
	public static boolean verificarCuotaPendiente(Connection conexion, int numSocio, String mes) {
		String query = "SELECT * FROM Cuotas WHERE NumSocio = ? AND Mes = ? AND Pagado = false";
		try (PreparedStatement consulta = conexion.prepareStatement(query)) {
			consulta.setInt(1, numSocio);
			consulta.setString(2, mes);
			ResultSet resultados = consulta.executeQuery();
			return resultados.next();
		} catch (SQLException e) {
			System.out.println("Error al verificar la cuota pendiente: " + e.getMessage());
			return false;
		}
	}

	/* Método para mostrar las cuotas pendientes de un socio */
	public static void mostrarCuotasPendientes(Connection conexion, int numSocio) {
		String query = "SELECT Mes FROM Cuotas WHERE NumSocio = ? AND Pagado = false";
		try (PreparedStatement consulta = conexion.prepareStatement(query)) {
			consulta.setInt(1, numSocio);
			ResultSet resultados = consulta.executeQuery();
			if (resultados.next()) {
				System.out.println("\n----- Cuotas Pendientes -----");
				do {
					String mes = resultados.getString("Mes");
					System.out.println(mes);
				} while (resultados.next());
			} else {
				System.out.println("No se encontraron cuotas pendientes para el socio " + numSocio);
			}
		} catch (SQLException e) {
			System.out.println("Error al mostrar las cuotas pendientes: " + e.getMessage());
		}
	}

}
