package gestionBanco;

/**
 * 
 * @author Francisco Javier Aranda Barba
 * @version 16/05/2023 1.0 Objetivo:Clase GestionBancoPrincipal
 *
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.util.Scanner;

/*Cosas por modificar:
 * 
 * 
 * -Mostrar el tratamiento cuando se produzca un saldo negativo
 * (falta)
 * 
 *
 * */

public class GestionBancoPrincipal {

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
						gestionarClientes(pruebaConexion);
						break;
					}
					case 2: {
						gestionarCuentas(pruebaConexion);
						break;
					}
					case 3: {
						gestionarMovimientos(pruebaConexion);
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

	/* Método para generar la conexión a la BD */

	/**
	 * Establece conexión con la base de datos
	 * 
	 * 
	 * @param ninguno
	 * @return establece conexion
	 */

	public static Connection conexion() {
		Connection conexion;
		String url = "jdbc:mysql://localhost:3306/BancoApp?useSSL=false&allowPublicKeyRetrieval=true";
		String user = "prog";
		String pass = "programacion";
		try {
			/*
			 * Nota para el alumno: Agregar la opción de cliente a su conector mysql
			 * ----allowPublicKeyRetrieval=true -----para permitir que el cliente solicite
			 * automáticamente la clave pública del servidor. Teniendo en cuenta que
			 * ----allowPublicKeyRetrieval=True ----podría permitir que un proxy malicioso
			 * realice un ataque MITM para obtener la contraseña de texto sin formato, por
			 * lo que es Falso de forma predeterminada y debe habilitarse explícitamente.
			 *
			 * useSSL=false&allowPublicKeyRetrieval=true
			 * 
			 */
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

	/**
	 * Muestra menú
	 * 
	 * 
	 * @param ninguno
	 * @return un menú inicial
	 */

	public static void mostrarMenuPrincipal() {
		System.out.println("\t----- Menú Principal -----");
		System.out.println("\n1. Gestión de Clientes");
		System.out.println("2. Gestión de Cuentas");
		System.out.println("3. Gestión de Movimientos");
		System.out.println("4. Salir\n");
		System.out.print("\tSeleccione una opción: ");
	}

	/* Método para gestionar clientes */

	/**
	 * Muestra menú para clientes
	 * 
	 * 
	 * @param Connection
	 * @return menú para clientes
	 */

	public static void gestionarClientes(Connection conexion) {
		Scanner sc = new Scanner(System.in);
		boolean volver = false;
		while (!volver) {
			System.out.println("\t----- Gestión de Clientes -----");
			System.out.println("\n1. Alta de Cliente");
			System.out.println("2. Baja de Cliente");
			System.out.println("3. Modificación de Cliente");
			System.out.println("4. Volver al Menú Principal\n");
			System.out.print("\tSeleccione una opción: ");
			try {
				int opcion = sc.nextInt();

				switch (opcion) {
				case 1: {
					altaCliente(conexion);
					break;
				}
				case 2: {
					bajaCliente(conexion);
					break;
				}
				case 3: {
					modificarCliente(conexion);
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

	/* Método para crear excepcion para regular el DNI */
	/**
	 * Permite introducir datos del dni correctamente
	 * 
	 * 
	 * @param String dni
	 * @return Excepción para el DNI
	 */

	public static void verificarDni(String dni) throws ExcepcionDNI {
		if (!dni.matches("^[0-9]{8}[A-Za-z]{1}")) {
			throw new ExcepcionDNI(dni);
		}
	}

	/* Método para generar un cliente */
	/**
	 * Permite introducir datos de los clientes
	 * 
	 * 
	 * @param ninguno
	 * @return Objeto ClienteBanco
	 */

	public static ClienteBanco pedirDatosClientes() {
		Scanner sc = new Scanner(System.in);
		String dni = obtenerDNI();
		System.out.print("Ingrese el nombre del cliente: ");
		String nombre = sc.nextLine();
		System.out.print("Ingrese el teléfono del cliente: ");
		String telefono = sc.nextLine();
		System.out.print("Ingrese la dirección del cliente: ");
		String direccion = sc.nextLine();
		ClienteBanco cliente = new ClienteBanco(dni, nombre, telefono, direccion);
		return cliente;
	}

	/* Método para ingresar DNI */
	/**
	 * Permite ingresar el DNI mediante un Scanner
	 * 
	 * 
	 * @param ninguno
	 * @return DNI por teclado
	 */

	public static String obtenerDNI() {
		Scanner sc = new Scanner(System.in);
		String dni = "";
		boolean bandera = true;
		while (bandera) {
			try {
				System.out.print("Ingrese el DNI del cliente (8 enteros y una letra): ");
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

	/* Método para dar de alta clientes */

	/**
	 * Permite introducir datos de los clientes, accediendo a la base de datos
	 * 
	 * 
	 * @param Connection
	 * @return mensaje si se ingresa el cliente de forma satisfactoria
	 */

	public static void altaCliente(Connection conexion) {
		Scanner sc = new Scanner(System.in);
		String query = "INSERT INTO CLIENTES (dni, nombre, telefono, direccion) SELECT ?, ?, ?, ? FROM DUAL WHERE NOT EXISTS (SELECT * FROM CLIENTES WHERE dni = ?)";
		System.out.println("\n----- Alta de Cliente -----");
		ClienteBanco cliente1 = pedirDatosClientes();
		try (PreparedStatement consulta = conexion.prepareStatement(query)) {
			consulta.setString(1, cliente1.getDni());
			consulta.setString(2, cliente1.getNombre());
			consulta.setString(3, cliente1.getTelefono());
			consulta.setString(4, cliente1.getDireccion());
			consulta.setString(5, cliente1.getDni());
			int filasAfectadas = consulta.executeUpdate();
			if (filasAfectadas > 0) {
				System.out.println("Cliente agregado exitosamente.");
			} else {
				System.out.println("Error al agregar el cliente: El cliente con DNI " + cliente1.getDni()
						+ " ya está en el sistema.");
			}
		} catch (SQLException e) {
			System.out.println("Error al agregar el cliente: " + e.getMessage());
		}
	}

	/* Método para dar de baja clientes */

	/**
	 * Permite borrar datos de los clientes
	 * 
	 * 
	 * @param Connection
	 * @return mensaje si se borra el cliente de forma satisfactoria
	 */

	public static void bajaCliente(Connection conexion) {
		Scanner sc = new Scanner(System.in);
		String cuentaQuery = "SELECT COUNT(*) FROM CUENTAS WHERE dni_cliente = ?";
		String borrarQuery = "DELETE FROM CLIENTES WHERE dni = ?";
		System.out.println("\n----- Baja de Cliente -----");
		String dni = obtenerDNI();

		try (PreparedStatement consultaCuenta = conexion.prepareStatement(cuentaQuery)) {
			consultaCuenta.setString(1, dni);
			ResultSet cuentasResult = consultaCuenta.executeQuery();
			cuentasResult.next();
			int contador = cuentasResult.getInt(1);

			if (contador > 0) {
				System.out.println("El cliente tiene cuentas asociadas. No se puede dar de baja.");
			} else {
				try (PreparedStatement borrar = conexion.prepareStatement(borrarQuery)) {
					borrar.setString(1, dni);
					int filasAfectadas = borrar.executeUpdate();
					if (filasAfectadas > 0) {
						System.out.println("Cliente dado de baja exitosamente.");
					} else {
						System.out.println("No se encontró el cliente con el DNI especificado.");
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Error al dar de baja el cliente: " + e.getMessage());
		}
	}

	/* Método para modificar clientes */

	/**
	 * Permite modificar datos de los clientes en función del DNI
	 * 
	 * 
	 * @param Connection
	 * @return mensaje si se modifica el cliente de forma satisfactoria
	 */

	public static void modificarCliente(Connection conexion) {
		Scanner sc = new Scanner(System.in);
		String query = "SELECT * FROM CLIENTES WHERE dni = ?";
		System.out.println("\n----- Modificación de Cliente -----");
		String dni = obtenerDNI();
		try (PreparedStatement consulta = conexion.prepareStatement(query)) {
			consulta.setString(1, dni);
			ResultSet resultados = consulta.executeQuery();
			if (resultados.next()) {
				String nombreActual = resultados.getString("nombre");
				String telefonoActual = resultados.getString("telefono");
				String direccionActual = resultados.getString("direccion");
				System.out.println("Datos actuales:");
				System.out.println("Nombre: " + nombreActual);
				System.out.println("Teléfono: " + telefonoActual);
				System.out.println("Dirección: " + direccionActual);
				System.out.print("Ingrese el nuevo nombre del cliente (dejar en blanco para mantener el actual): ");
				String nuevoNombre = sc.nextLine();
				if (nuevoNombre.isEmpty()) {
					nuevoNombre = nombreActual;
				}
				System.out.print("Ingrese el nuevo teléfono del cliente (dejar en blanco para mantener el actual): ");
				String nuevoTelefono = sc.nextLine();
				if (nuevoTelefono.isEmpty()) {
					nuevoTelefono = telefonoActual;
				}
				System.out.print("Ingrese la nueva dirección del cliente (dejar en blanco para mantener la actual): ");
				String nuevaDireccion = sc.nextLine();
				if (nuevaDireccion.isEmpty()) {
					nuevaDireccion = direccionActual;
				}
				String actualizarQuery = "UPDATE CLIENTES SET nombre = ?, telefono = ?, direccion = ? WHERE dni = ?";
				PreparedStatement actualizar = conexion.prepareStatement(actualizarQuery);
				/* Actualizo campos manteniendo el DNI */
				actualizar.setString(1, nuevoNombre);
				actualizar.setString(2, nuevoTelefono);
				actualizar.setString(3, nuevaDireccion);
				actualizar.setString(4, dni);
				int celdasActualizadas = actualizar.executeUpdate();
				if (celdasActualizadas > 0) {
					System.out.println("Cliente modificado exitosamente.");
				} else {
					System.out.println("No se encontró el cliente con el DNI especificado.");
				}
			} else {
				System.out.println("No se encontró el cliente con el DNI especificado.");
			}
		} catch (SQLException e) {
			System.out.println("Error al modificar el cliente: " + e.getMessage());
		}
	}

	/* Método para gestionar cuentas (muestra menú) */

	/**
	 * Muestra un menú para gestionar cuentas
	 * 
	 * 
	 * @param Connection
	 * @return menú para las cuentas
	 */

	public static void gestionarCuentas(Connection conexion) {
		Scanner sc = new Scanner(System.in);
		boolean volver = false;
		while (!volver) {
			System.out.println("\t----- Gestión de Cuentas -----");
			System.out.println("\n1. Alta de Cuenta");
			System.out.println("2. Baja de Cuenta");
			System.out.println("3. Modificación de Cuenta");
			System.out.println("4. Ingreso en Cuenta");
			System.out.println("5. Retirar dinero de Cuenta");
			System.out.println("6. Transferencia");
			System.out.println("7. Mostrar cuenta del cliente con su DNI");
			System.out.println("8. Volver al Menú Principal\n");
			System.out.print("\tSeleccione una opción: ");
			try {
				int opcion = sc.nextInt();

				switch (opcion) {
				case 1: {
					altaCuenta(conexion);
					break;
				}
				case 2: {
					bajaCuenta(conexion);
					break;
				}
				case 3: {
					modificarCuenta(conexion);
					break;
				}
				case 4: {
					ingresoCuenta(conexion);
					break;
				}
				case 5: {
					retirarDinero(conexion);
					break;
				}
				case 6: {
					transferirDinero(conexion);
					break;
				}
				case 7: {
					String dni = obtenerDNI();
					mostrarCuentasCliente(conexion, dni);
					break;
				}

				case 8: {
					volver = true;
					break;
				}
				default: {
					System.out.println("Opción no válida. Vuelva a introducir la opción.");
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

	/* Método para ingresar estado de la cuenta */
	/**
	 * Permite ingresar el DNI mediante un Scanner
	 * 
	 * 
	 * @param ninguno
	 * @return Estado de la cuenta por teclado
	 */

	public static String obtenerEstadoCuenta() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Ingrese la situación de la cuenta (activa/baja): ");
		String estado = sc.nextLine();
		while (true) {
			try {
				if (!estado.equalsIgnoreCase("activa") && !estado.equalsIgnoreCase("baja")) {
					throw new IllegalArgumentException("Valor no válido. Ingrese 'activa' o 'baja'.");
				}
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				System.out.print("Ingrese la situación de la cuenta (activa/baja): ");
				estado = sc.nextLine();
			}
		}
		return estado;
	}

	/* Método para leer saldo de la cuenta */
	/**
	 * Permite ingresar cantidad de saldo mediante un Scanner
	 * 
	 * 
	 * @param ninguno
	 * @return Saldo de la cuenta por teclado
	 */
	public static double leerSaldo() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Ingrese el saldo de la cuenta con el que desea operar: ");
		return sc.nextDouble();
	}

	/* Método para leer saldo de la cuenta */
	/**
	 * Permite mostrar las cuentas asignadas a un cliente
	 * 
	 * 
	 * @param Connection , String dni del cliente
	 * @return cuentas asociadas
	 */

	public static void mostrarCuentasCliente(Connection conexion, String dniCliente) {
		String query = "SELECT * FROM cuentas WHERE dni_cliente = ?";
		try (PreparedStatement consulta = conexion.prepareStatement(query)) {
			consulta.setString(1, dniCliente);
			ResultSet resultados = consulta.executeQuery();

			System.out.println("\nCuentas del cliente con DNI " + dniCliente + ":\n");
			while (resultados.next()) {
				int numeroCuenta = resultados.getInt("numero_cuenta");
				String situacion = resultados.getString("situacion");
				double saldo = resultados.getDouble("saldo");

				System.out.println("Número de cuenta: " + numeroCuenta);
				System.out.println("Tipo de cuenta: " + situacion);
				System.out.println("Saldo: " + saldo + "\n");
			}
		} catch (SQLException e) {
			System.out.println("Error al mostrar las cuentas del cliente: " + e.getMessage());
		}
	}

	/* Método para dar de alta cuentas */

	/**
	 * Permite dar de alta cuentas
	 * 
	 * 
	 * @param Connection
	 * @return Mensaje si se añade una cuenta correctamente
	 */

	public static void altaCuenta(Connection conexion) {
		/* Nota para el alumno: ojito CON AUTOINCREMENT */
		String query = "INSERT INTO CUENTAS (numero_cuenta,dni_cliente, situacion, saldo) VALUES (0,? , ?, ?)";
		System.out.println("\t----- Alta de Cuenta -----");
		String dni = obtenerDNI();
		String situacion = "activa";
		double saldo = leerSaldo();
		try (PreparedStatement consulta = conexion.prepareStatement(query)) {
			consulta.setString(1, dni);
			consulta.setString(2, situacion);
			consulta.setDouble(3, saldo);
			consulta.executeUpdate();
			System.out.println("Cuenta agregada exitosamente.");
		} catch (SQLException e) {
			System.out.println("Error al agregar la cuenta: " + e.getMessage());
		}
	}

	/* Método para dar de baja una cuenta */

	/**
	 * Permite dar de baja cuentas
	 * 
	 * 
	 * @param Connection
	 * @return Mensaje si se borra una cuenta correctamente
	 */
	/* añadir excepción, si hay dinero en cuenta, no BORRAR */

	public static void bajaCuenta(Connection conexion) {
		Scanner sc = new Scanner(System.in);
		String query = "DELETE FROM CUENTAS WHERE numero_cuenta = ?";
		System.out.println("\n----- Baja de Cuenta -----");
		System.out.print("Ingrese el número de cuenta a dar de baja: ");
		int numeroCuenta = sc.nextInt();
		try (PreparedStatement consultaBorrar = conexion.prepareStatement(query)) {
			consultaBorrar.setInt(1, numeroCuenta);
			int celdasBorradas = consultaBorrar.executeUpdate();
			if (celdasBorradas > 0) {
				System.out.println("Cuenta dada de baja exitosamente.");
			} else {
				System.out.println("No se encontró la cuenta con el número especificado.");
			}
		} catch (SQLException e) {
			System.out.println("Error al dar de baja la cuenta: " + e.getMessage());
		}
	}

	/* Método para modificar una cuenta */

	/**
	 * Permite modificar de baja cuentas
	 * 
	 * 
	 * @param Connection
	 * @return Mensaje tras modificar la cuenta si esta se encontró
	 */

	/* añadir excepción, si hay dinero en cuenta, no BORRAR */
	public static void modificarCuenta(Connection conexion) {
		Scanner sc = new Scanner(System.in);
		String query = "SELECT * FROM CUENTAS WHERE numero_cuenta = ?";
		System.out.println("\n----- Modificación de Cuenta -----");
		System.out.print("Ingrese el número de cuenta a modificar: ");
		int numeroCuenta = sc.nextInt();
		try (PreparedStatement consultaModificar = conexion.prepareStatement(query)) {
			consultaModificar.setInt(1, numeroCuenta);
			ResultSet resultado = consultaModificar.executeQuery();
			if (resultado.next()) {
				String dniClienteActual = resultado.getString("dni_cliente");
				String situacionActual = resultado.getString("situacion");
				double saldoActual = resultado.getDouble("saldo");
				System.out.println("Datos actuales:");
				System.out.println("DNI del cliente: " + dniClienteActual);
				System.out.println("Situación: " + situacionActual);
				System.out.println("Saldo: " + saldoActual);
				sc.nextLine();
				System.out.print("Ingrese la nueva situación de la cuenta (activa/baja): ");
				String nuevaSituacion = sc.nextLine();
				if (nuevaSituacion.isEmpty()) {
					nuevaSituacion = situacionActual;
				}

				String queryActualizar = "UPDATE CUENTAS SET situacion = ? WHERE numero_cuenta = ?";
				PreparedStatement actualizarConsulta = conexion.prepareStatement(queryActualizar);
				actualizarConsulta.setString(1, nuevaSituacion);
				actualizarConsulta.setInt(2, numeroCuenta);
				int celdasActualizadas = actualizarConsulta.executeUpdate();

				if (celdasActualizadas > 0) {
					System.out.println("Cuenta modificada exitosamente.");
				} else {
					System.out.println("No se encontró la cuenta con el número especificado.");
				}
			} else {
				System.out.println("No se encontró la cuenta con el número especificado.");
			}
		} catch (SQLException e) {
			System.out.println("Error al modificar la cuenta: " + e.getMessage());
		}
	}

	/* Método para realizar consulta al saldo Actual */
	/**
	 * Comprueba el saldo de una cuenta con parametros la conexion y un numero de
	 * cuenta
	 * 
	 * 
	 * @param Connection conexion, int numCuenta
	 * @return double saldo
	 */
	public static double consultaSaldo(Connection conexion, int numCuenta) {
		double saldo = 0;
		String consultaSaldo = "SELECT saldo FROM CUENTAS WHERE numero_cuenta = ?";
		try (PreparedStatement consulta = conexion.prepareStatement(consultaSaldo)) {
			consulta.setInt(1, numCuenta);
			ResultSet resultado = consulta.executeQuery(consultaSaldo);
			if (resultado.next()) {
				saldo = resultado.getDouble("saldo");
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return saldo;
	}

	/* Método para ingresar dinero en cuenta */

	/**
	 * Permite ingresar dinero en una cuenta
	 * 
	 * 
	 * @param Connection
	 * @return Mensaje si se ingresa dinero en una cuenta correctamente
	 */

	public static void ingresoCuenta(Connection conexion) {
		int numeroCuenta = obtenerNumeroCuenta();
		double saldo = leerSaldo();
		String tipo = "ingreso";
		String concepto = leerConcepto();
		String query = "UPDATE CUENTAS SET saldo = (saldo + ?) WHERE numero_cuenta = ?";

		try (PreparedStatement consulta = conexion.prepareStatement(query);) {
			consulta.setDouble(1, saldo);
			consulta.setInt(2, numeroCuenta);
			registrarMovimiento(conexion, numeroCuenta, saldo, tipo, numeroCuenta, concepto);
			int filasActualizadas = consulta.executeUpdate();

			if (filasActualizadas > 0) {
				System.out.println("Ingreso efectuado.");
			} else {
				System.out.println("No se encontró una cuenta asociada al DNI proporcionado.");
			}
		} catch (SQLException e) {
			System.out.println("Error al ingresar dinero en la cuenta: " + e.getMessage());
		}
	}

	/* Método para obtener un número de cuenta pasando un DNI por parámetro */

	/**
	 * Permite obtener número de cuenta
	 * 
	 * 
	 * @param String dni
	 * @return int con el numero de cuenta
	 */

	public static int obtenerNumeroCuenta() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Ingrese el número de cuenta: ");
		int numeroCuenta = sc.nextInt();
		return numeroCuenta;
	}

	/* Método para retirar dinero en cuenta */

	/**
	 * Permite retirar dinero en una cuenta
	 * 
	 * 
	 * @param Connection
	 * @return Mensaje si se retira dinero en una cuenta correctamente
	 */
	public static void retirarDinero(Connection conexion) {
		int numeroCuenta = obtenerNumeroCuenta();
		double saldo = leerSaldo();
		String tipo = "salida";
		String concepto = leerConcepto();
		String query = "UPDATE CUENTAS SET saldo = (saldo - ?) WHERE numero_cuenta = ?";

		try (PreparedStatement consulta = conexion.prepareStatement(query)) {
			consulta.setDouble(1, saldo);
			consulta.setInt(2, numeroCuenta);
			System.out.println(consultaSaldo(conexion, numeroCuenta) + "consulta Saldo");
			int filasActualizadas = consulta.executeUpdate();
			registrarMovimiento(conexion, numeroCuenta, saldo, tipo, numeroCuenta, concepto);
			if (filasActualizadas > 0) {
				System.out.println("Extracción efectuada.");
			} else {
				System.out.println(
						"No se encontró una cuenta asociada al DNI proporcionado o el saldo disponible es insuficiente.");
			}
		} catch (SQLException e) {
			System.out.println("Error al sacar dinero de la cuenta: " + e.getMessage());
		}
	}

	/* Método para leer el tipo de movimiento */
	/**
	 * Lee el tipo de movimiento
	 * 
	 * 
	 * @param nada
	 * @return String de lectura concepto
	 */

	public static String leerTipo() {
		Scanner sc = new Scanner(System.in);
		System.out.println(
				"Ingerese el tipo de movimiento (ingreso/salida/transferencia enviada/transferencia recibida): ");
		String tipo = sc.nextLine();
		return tipo;
	}

	/* Método para leer el concepto */

	/**
	 * Lee el concepto
	 * 
	 * 
	 * @param nada
	 * @return String de lectura concepto
	 */
	public static String leerConcepto() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingerese concepto: ");
		String concepto = sc.nextLine();
		return concepto;
	}

	/* Método para verificar cuentas */

	/**
	 * Verifica cuentas de origen y destino de una transferencia
	 * 
	 * 
	 * @param Connection, String (dniEmisor) , String (dniReceptor) , double
	 *                    (saldoDeTransferencia)
	 * @return boolean que devuelve si es posible realizar la transferencia
	 */

	public static boolean verificarCuentas(Connection connection, int cuentaOrigen, int cuentaDestino,
			double saldoTransferencia) {
		String querySaldoOrigen = "SELECT saldo FROM CUENTAS WHERE numero_cuenta = ?";
		String querySaldoDestino = "SELECT saldo FROM CUENTAS WHERE numero_cuenta = ?";

		try (PreparedStatement consultaSaldoOrigen = connection.prepareStatement(querySaldoOrigen);
				PreparedStatement consultaSaldoDestino = connection.prepareStatement(querySaldoDestino)) {
			consultaSaldoOrigen.setInt(1, cuentaOrigen);
			consultaSaldoDestino.setInt(1, cuentaDestino);

			double saldoOrigen = obtenerSaldoCuenta(consultaSaldoOrigen);
			double saldoDestino = obtenerSaldoCuenta(consultaSaldoDestino);

			return saldoOrigen >= saldoTransferencia && saldoDestino > 0;
		} catch (SQLException e) {
			System.out.println("Error al verificar las cuentas: " + e.getMessage());
			return false;
		}
	}

	/* Método para obtener el saldo en una cuenta */

	/**
	 * Permite mostrar movimientos
	 * 
	 * 
	 * @param PreparedStatement
	 * @return saldo de la consulta
	 */

	public static double obtenerSaldoCuenta(PreparedStatement consultaSaldo) throws SQLException {
		double saldo = 0;
		try (ResultSet resultado = consultaSaldo.executeQuery()) {
			if (resultado.next()) {
				saldo = resultado.getDouble("saldo");
			}
		}
		return saldo;
	}

	/* Método para realizar la transferencia entre cuentas */

	/**
	 * Realiza una transferencia entre cuentas
	 * 
	 * 
	 * @param Connection
	 * @return no devuelve nada, realiza la transferencia
	 */

	public static void transferirDinero(Connection conexion) {
		int numeroCuentaOrigen = obtenerNumeroCuenta();
		int numeroCuentaDestino = obtenerNumeroCuenta();
		double saldoTransferencia = leerSaldo();
		String tipoCuentaOrigen = "transferencia enviada";
		String concepto = leerConcepto();
		String tipoCuentaDestino = "transferencia recibida";
		String querySaldo = "SELECT saldo FROM CUENTAS WHERE numero_cuenta = ?";
		String queryTransferenciaOrigen = "UPDATE CUENTAS SET saldo = (saldo - ?) WHERE numero_cuenta = ?";
		String queryTransferenciaDestino = "UPDATE CUENTAS SET saldo = (saldo + ?) WHERE numero_cuenta = ?";

		try (PreparedStatement consultaSaldo = conexion.prepareStatement(querySaldo);
				PreparedStatement consultaTransferenciaOrigen = conexion.prepareStatement(queryTransferenciaOrigen);
				PreparedStatement consultaTransferenciaDestino = conexion.prepareStatement(queryTransferenciaDestino)) {

			consultaSaldo.setInt(1, numeroCuentaOrigen);
			double saldoOrigen = obtenerSaldoCuenta(consultaSaldo);

			consultaSaldo.setInt(1, numeroCuentaDestino);
			double saldoDestino = obtenerSaldoCuenta(consultaSaldo);

			if (saldoOrigen >= saldoTransferencia && saldoDestino >= saldoTransferencia) {
				consultaTransferenciaOrigen.setDouble(1, saldoTransferencia);
				consultaTransferenciaOrigen.setInt(2, numeroCuentaOrigen);
				consultaTransferenciaDestino.setDouble(1, saldoTransferencia);
				consultaTransferenciaDestino.setInt(2, numeroCuentaDestino);

				consultaTransferenciaOrigen.executeUpdate();
				consultaTransferenciaDestino.executeUpdate();

				registrarMovimiento(conexion, numeroCuentaOrigen, saldoTransferencia, tipoCuentaOrigen,
						numeroCuentaDestino, concepto);

				registrarMovimiento(conexion, numeroCuentaDestino, saldoTransferencia, tipoCuentaDestino,
						numeroCuentaOrigen, concepto);
				System.out.println("Transferencia realizada con éxito.");
			} else {
				System.out.println("No se pudo realizar la transferencia. Verifique los saldos de las cuentas.");
			}
		} catch (SQLException e) {
			System.out.println("Error al realizar la transferencia: " + e.getMessage());
		}
	}

	/* Método para gestionar movimientos (muestra menú) */

	/**
	 * Muestra un menú para gestionar movimientos
	 * 
	 * 
	 * @param Connection
	 * @return menú para los movimientos
	 */

	public static void gestionarMovimientos(Connection conexion) {
		Scanner sc = new Scanner(System.in);
		boolean volver = false;
		while (!volver) {
			System.out.println("\n----- Gestión de Movimientos -----");
			System.out.println("1. Listar movimientos entre fechas");
			System.out.println("2. Volver al Menú Principal");
			System.out.print("Seleccione una opción: ");
			int opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				listarMovimientosEntreFechas(conexion);
				break;
			case 2:
				volver = true;
				break;
			default:
				System.out.println("Opción no válida. Vuelva a introducir la opción.");
				break;
			}
		}
	}

	/* Método para registrar movimientos en la tabla movimientos */

	/**
	 * Registrar movimientos en la tabla movimientos
	 * 
	 * 
	 * @param Connection , int , double , String , int , String
	 * @return registro de movimiento
	 */

	public static void registrarMovimiento(Connection conexion, int numeroCuenta, double importe, String tipo,
			int numeroCuentaTransferencia, String concepto) {
		String query = "INSERT INTO MOVIMIENTOS (numero_cuenta, importe, fecha_hora, tipo, numero_cuenta_transferencia, concepto) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement consulta = conexion.prepareStatement(query)) {
			consulta.setInt(1, numeroCuenta);
			consulta.setDouble(2, importe);
			consulta.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
			consulta.setString(4, tipo);
			consulta.setInt(5, numeroCuentaTransferencia);
			consulta.setString(6, concepto);

			int filasInsertadas = consulta.executeUpdate();
			if (filasInsertadas > 0) {
				System.out.println("Movimiento registrado.");
			} else {
				System.out.println("No se pudo registrar el movimiento.");
			}
		} catch (SQLException e) {
			System.out.println("Error al registrar el movimiento: " + e.getMessage());
		}
	}

	/* Método para leer la fecha */
	/**
	 * Lee la fecha por teclado
	 * 
	 * 
	 * @param String
	 * @return String (una fecha)
	 */

	public static String obtenerFecha(String mensaje) {
		Scanner sc = new Scanner(System.in);
		System.out.print(mensaje);
		String fecha = sc.nextLine();
		/* Validar el formato de la fecha */
		while (!esFormatoFechaValido(fecha)) {
			System.out.print("Formato de fecha inválido. Ingrese la fecha en el formato yyyy-MM-dd: ");
			fecha = sc.nextLine();
		}
		return fecha;
	}

	/* Método para verificar el formato de la fecha */
	/**
	 * Verifica el formato de la fecha
	 * 
	 * 
	 * @param String (una fecha)
	 * @return boolean que devuelve si es o no válido el formato fecha
	 */

	public static boolean esFormatoFechaValido(String fecha) {
		try {
			String[] partes = fecha.split("-");
			if (partes.length != 3) {
				return false;
			}
			int anio = Integer.parseInt(partes[0]);
			int mes = Integer.parseInt(partes[1]);
			int dia = Integer.parseInt(partes[2]);
			/* Validar rango de valores */
			if (anio < 1000 || anio > 9999 || mes < 1 || mes > 12 || dia < 1 || dia > 31) {
				return false;
			}
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/* Método para listar movimientos */

	/**
	 * Permite mostrar movimientos
	 * 
	 * 
	 * @param Connection
	 * @return Mensaje mostrando el movimiento
	 */

	public static void listarMovimientosEntreFechas(Connection conexion) {
		String fechaInicio = obtenerFecha("Ingrese la fecha de inicio (yyyy-MM-dd): ");
		String fechaFin = obtenerFecha("Ingrese la fecha de fin (yyyy-MM-dd): ");

		if (fechaInicio != null && fechaFin != null) {
			ejecutarConsultaMovimientos(conexion, fechaInicio, fechaFin);
		} else {
			System.out.println("Las fechas ingresadas no son válidas.");
		}
	}

	/* Método para ejecutar consulta de los movimientos */

	/**
	 * Ejecuta la consulta mostrar movimientos
	 * 
	 * 
	 * @param Connection,String (fecha inicio),String (fecha fin)
	 * @return Mensaje mostrando los movimientos entre dichas fechas
	 */

	public static void ejecutarConsultaMovimientos(Connection conexion, String fechaInicio, String fechaFin) {
		String query = "SELECT * FROM MOVIMIENTOS WHERE fecha_hora BETWEEN ? AND ?";
		try (PreparedStatement consulta = conexion.prepareStatement(query)) {
			consulta.setString(1, fechaInicio + " 00:00:00");
			consulta.setString(2, fechaFin + " 23:59:59");
			ResultSet resultados = consulta.executeQuery();
			System.out.println("\nMovimientos entre " + fechaInicio + " y " + fechaFin + " :\n");
			while (resultados.next()) {
				int numeroCuenta = resultados.getInt("numero_cuenta");
				double importe = resultados.getDouble("importe");
				String fechaHora = resultados.getString("fecha_hora");
				String tipo = resultados.getString("tipo");
				int numeroCuentaTransferencia = resultados.getInt("numero_cuenta_transferencia");
				String concepto = resultados.getString("concepto");
				System.out.println("Número de cuenta: " + numeroCuenta);
				System.out.println("Importe: " + importe);
				System.out.println("Fecha y hora: " + fechaHora);
				System.out.println("Tipo: " + tipo);
				System.out.println("Número de cuenta de transferencia: " + numeroCuentaTransferencia);
				System.out.println("Concepto: " + concepto + "\n");
			}
		} catch (SQLException e) {
			System.out.println("Error al listar los movimientos: " + e.getMessage());
		}
	}
}
