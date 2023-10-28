package almacenBebidas;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 
 * @author Francisco Javier Aranda Barba
 * @version 9/6/2023 1.0 Objetivo:Clase Almacen
 *
 */
public class Almacen {
	private ArrayList<Bebida> listaBebidas;

	/* Constructor */
	public Almacen() {
		this.listaBebidas = new ArrayList();
	}

	/* Getter y setters */
	public ArrayList<Bebida> getListaBebidas() {
		return listaBebidas;
	}

	public void setListaBebidas(ArrayList<Bebida> listaBebidas) {
		this.listaBebidas = listaBebidas;
	}

	/* Método toString */
	@Override
	public String toString() {
		return "Almacen [listaBebidas=" + listaBebidas + "]";
	}

	/* Método para buscar bebidas por ID */
	public Bebida buscarBebidaId(String idBebida) {
		for (Bebida bebida : listaBebidas) {
			if (bebida.getIdBebida().equals(idBebida)) {
				return bebida;
			}
		}
		return null;
	}

	/* Método para buscar bebidas por marca */
	public Bebida buscarBebidaMarca(String marca) {
		for (Bebida bebida : listaBebidas) {
			if (bebida.getMarca().equals(marca)) {
				return bebida;
			}
		}
		return null;
	}

	/* Método para calcular precio agua de una marca concreta */
	public double calcularPrecioAguaPorMarca(String marca) {
		double precioAgua = 0;
		buscarBebidaMarca(marca);
		ArrayList<Bebida> bebidasAgua = new ArrayList<>();
		for (Bebida bebida : listaBebidas) {
			if (bebida instanceof Agua) {
				bebidasAgua.add(bebida);
			}
		}

		for (Bebida bebida : bebidasAgua) {
			if (bebida.getMarca().equals(marca)) {
				precioAgua += bebida.getPrecio() * bebida.getUnidades();
			}
		}
		if (precioAgua == 0) {
			System.out.println("No disponemos de agua de esa marca.");
		}
		return precioAgua;
	}

	/* Método para mostrar un producto por ID */

	public void mostrarBebidaPorId(String idBebida) {
		buscarBebidaId(idBebida);
		for (Bebida bebida : listaBebidas) {
			if (bebida.getIdBebida().equals(idBebida)) {
				System.out.println(bebida);
			}
		}
	}

	/* Método para añadir una bebida a la lista */
	public boolean agregarBebida(Bebida bebida) {
		if (buscarBebidaId(bebida.getIdBebida()) == null) {
			listaBebidas.add(bebida);
			return true;
		} else {
			return false;
		}
	}

	/* Método para reponer producto */
	public void reponerProducto(String idBebida, int cantidad) {
		Bebida bebida = buscarBebidaId(idBebida);
		if (bebida != null) {
			int unidadesActuales = bebida.getUnidades();
			bebida.setUnidades(unidadesActuales + cantidad);
		}
	}

	/* Método para eliminar bebidas */
	public boolean eliminarProducto(String idBebida) {
		boolean eliminada=false;
		Bebida bebida = buscarBebidaId(idBebida);
		if (bebida != null) {
			listaBebidas.remove(bebida);
			eliminada=true;
		}
		return eliminada;
	}

	/* Método para retirar cantidad de bebidas */
	public void retirarBebidas(String idBebida, int cantidad) {
		Bebida bebida = buscarBebidaId(idBebida);
		if (bebida != null) {
			int unidadesActuales = bebida.getUnidades();
			if (cantidad <= unidadesActuales) {
				bebida.setUnidades(unidadesActuales - cantidad);
			} else {
				System.out.println("Disponemos de " + bebida.getUnidades() + " unidades.");
			}
		}
	}

	/* Método para calcular el precio total de todas las bebidas */
	public double calcularPrecioTodasBebidas() {
		double precioTotal = 0;
		for (Bebida bebida : listaBebidas) {
			precioTotal += bebida.getPrecio() * bebida.getUnidades();
		}
		return precioTotal;
	}

	/* Método para calcular la media de precio de las bebidas */
	public double calcularMediaPrecios() {
		double totalPrecios = 0;
		int contadorBebidas = 0;

		for (int i = 0; i < listaBebidas.size(); i++) {
			Bebida bebidaActual = listaBebidas.get(i);
			boolean bebidaRepetida = false;

			/* Comprueba si la bebida actual ya ha sido contabilizada antes */
			for (int j = 0; j < i && !bebidaRepetida; j++) {
				Bebida bebidaAnterior = listaBebidas.get(j);
				if (bebidaActual.getIdBebida().equals(bebidaAnterior.getIdBebida())) {
					bebidaRepetida = true;
				}
			}

			/*
			 * Si la bebida no ha sido contabilizada antes, se agrega a la suma total de
			 * precios
			 */
			if (!bebidaRepetida) {
				totalPrecios += bebidaActual.getPrecio();
				contadorBebidas++;
			}
		}

		if (contadorBebidas == 0) {
			System.out.println("No hay bebidas en el almacén.");
			return 0;
		}

		return totalPrecios / contadorBebidas;
	}

	/* Método para ver productos por encima de la media */
	public void productosEncimaMedia() {
		ArrayList<Bebida> bebidasPorEncima = new ArrayList();
		for (Bebida bebida : listaBebidas) {
			if (bebida.getPrecio() > calcularMediaPrecios()) {
				bebidasPorEncima.add(bebida);
			}
		}
		for (Bebida bebida : bebidasPorEncima) {
			System.out.println(bebida);
		}
	}

	/* Método para ver productos por debajo de la media */
	public void productosDebajoMedia() {
		ArrayList<Bebida> bebidasPorEncima = new ArrayList();
		for (Bebida bebida : listaBebidas) {
			if (bebida.getPrecio() < calcularMediaPrecios()) {
				bebidasPorEncima.add(bebida);
			}
		}
		for (Bebida bebida : bebidasPorEncima) {
			System.out.println(bebida);
		}
	}

	/* Método para mostrar las marcas con precio mayor a la media */
	public void marcasPrecioMayorMedia() {
		double mediaPrecios = calcularMediaPrecios();
		ArrayList<String> marcasMayoresMedia = new ArrayList<>();

		boolean hayMarcasMayores = false;
		for (Bebida bebida : listaBebidas) {
			if (bebida.getPrecio() > mediaPrecios) {
				String marca = bebida.getMarca();
				boolean marcaRepetida = false;

				for (String marcaExistente : marcasMayoresMedia) {
					if (marcaExistente.equals(marca)) {
						marcaRepetida = true;
						break;
					}
				}

				if (!marcaRepetida) {
					marcasMayoresMedia.add(marca);
					hayMarcasMayores = true;
				}
			}
		}

		if (!hayMarcasMayores) {
			System.out.println("No hay marcas con precio mayor a la media.");
		} else {
			System.out.println("Marcas con precio mayor a la media:");
			for (String marca : marcasMayoresMedia) {
				System.out.println(marca);
			}
		}
	}

	/* Método para mostrar las marcas con precio mayor a la media */
	public void marcasPrecioMenorMedia() {
		double mediaPrecios = calcularMediaPrecios();
		ArrayList<String> marcasMenoresMedia = new ArrayList<>();

		boolean hayMarcasMenores = false;
		for (Bebida bebida : listaBebidas) {
			if (bebida.getPrecio() < mediaPrecios) {
				String marca = bebida.getMarca();
				boolean marcaRepetida = false;

				for (String marcaExistente : marcasMenoresMedia) {
					if (marcaExistente.equals(marca)) {
						marcaRepetida = true;
						break;
					}
				}

				if (!marcaRepetida) {
					marcasMenoresMedia.add(marca);
					hayMarcasMenores = true;
				}
			}
		}

		if (!hayMarcasMenores) {
			System.out.println("No hay marcas con precio menor a la media.");
		} else {
			System.out.println("Marcas con precio menor a la media:");
			for (String marca : marcasMenoresMedia) {
				System.out.println(marca);
			}
		}
	}

	/* Método para obtener el producto más caro */
	public Bebida productoMasCaro() {
		Bebida bebidaMasCara = null;
		for (Bebida bebida : listaBebidas) {
			if (bebidaMasCara == null || bebida.getPrecio() > bebidaMasCara.getPrecio()) {
				bebidaMasCara = bebida;
			}
		}
		return bebidaMasCara;
	}

	/* Método para obtener el producto más barato */
	public Bebida productoMasBarato() {
		Bebida bebidaMasBarata = null;
		for (Bebida bebida : listaBebidas) {
			if (bebidaMasBarata == null || bebida.getPrecio() < bebidaMasBarata.getPrecio()) {
				bebidaMasBarata = bebida;
			}
		}
		return bebidaMasBarata;
	}

	/* Método para calcular el precio total de una marca de bebidas */
	public double calcularPrecioMarca(String marca) {
		double precioTotal = 0;
		for (Bebida bebida : listaBebidas) {
			if (bebida.getMarca().equals(marca)) {
				precioTotal += bebida.getPrecio() * bebida.getUnidades();
			}
		}
		return precioTotal;
	}

	/* Método para calcular el precio de las bebidas en una estantería */
	public double calcularPrecioEstanteria(int estanteria) {
		double precioTotal = 0;
		for (Bebida bebida : listaBebidas) {
			if (bebida.getEstanteria() == estanteria) {
				precioTotal += bebida.getPrecio() * bebida.getUnidades();
			}
		}
		return precioTotal;
	}

	/* Método para mostrar las bebidas ordenadas por precio */
	public void mostrarInformacionOrdenadaPorPrecio() {
		ArrayList<Bebida> bebidasOrdenadasPrecio = new ArrayList<>(listaBebidas);
		ComparadorBebidasPrecio comparador = new ComparadorBebidasPrecio();
		bebidasOrdenadasPrecio.sort(comparador);
		for (Bebida bebida : bebidasOrdenadasPrecio) {
			System.out.println(bebida.toString());
		}
	}

	/* Método para mostrar las bebidas ordenadas por fecha */
	public void mostrarInformacionOrdenadaPorFecha() {
		ArrayList<Bebida> bebidasOrdenadasFecha = new ArrayList<>(listaBebidas);
		ComparadorBebidasFecha comparador = new ComparadorBebidasFecha();
		bebidasOrdenadasFecha.sort(comparador);
		for (Bebida bebida : bebidasOrdenadasFecha) {
			System.out.println(bebida.toString());
		}
	}

	/* Método para mostrar las bebidas ordenadas por unidades */
	public void mostrarInformacionOrdenadaPorUnidades() {
		ArrayList<Bebida> bebidasOrdenadasUnidades = new ArrayList<>(listaBebidas);
		ComparadorBebidasCantidad comparador = new ComparadorBebidasCantidad();
		bebidasOrdenadasUnidades.sort(comparador);
		for (Bebida bebida : bebidasOrdenadasUnidades) {
			System.out.println(bebida.toString());
		}
	}

	/* Mostrar el precio del agua */
	public double mostrarPrecioAgua() {
		double precioAgua = 0;
		ArrayList<Bebida> bebidasAgua = new ArrayList<>();
		for (Bebida bebida : listaBebidas) {
			if (bebida instanceof Agua) {
				bebidasAgua.add(bebida);
			}
		}
		for (Bebida bebida : bebidasAgua) {
			precioAgua += bebida.getPrecio() * bebida.getUnidades();
		}
		return precioAgua;
	}

	/* Mostrar el precio del bebidas azucaradas */
	public double mostrarPrecioBebidasAzu() {
		double precioBebidaAzu = 0;
		ArrayList<Bebida> bebidasAzucar = new ArrayList<>();
		for (Bebida bebida : listaBebidas) {
			if (bebida instanceof BebidaAzucarada) {
				bebidasAzucar.add(bebida);
			}
		}
		for (Bebida bebida : bebidasAzucar) {
			precioBebidaAzu += bebida.getPrecio() * bebida.getUnidades();
		}
		return precioBebidaAzu;
	}

	/* Método para exportar documento de agua */
	public void exportarAgua(String nombreArchivo) {
		/* Creo arrayList auxiliar recorriendo bebidas y agregando solo el agua */
		ArrayList<Bebida> bebidasAgua = new ArrayList<>();
		for (Bebida bebida : listaBebidas) {
			if (bebida instanceof Agua) {
				bebidasAgua.add(bebida);
			}
		}

		try {
			FileWriter archivoAguaMineral = new FileWriter(nombreArchivo);
			for (Bebida bebida : bebidasAgua) {
				archivoAguaMineral.write(bebida.toString() + "\n\n");
			}
			archivoAguaMineral.close();
			System.out.println("Archivo de agua mineral creado correctamente.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* Método para listar bebidas */
	public void listarBebidas() {
		for (Bebida bebida : listaBebidas) {
			System.out.println(bebida.toString());
		}
	}

	/* Método para exportar documento de aguas por precio */
	public void exportarAguasPorPrecio(String nombreArchivo) {
		ArrayList<Bebida> aguas = new ArrayList<>();
		for (Bebida bebida : listaBebidas) {
			if (bebida instanceof Agua) {
				aguas.add(bebida);
			}
		}

		aguas.sort(new ComparadorBebidasPrecio());

		try {
			FileWriter archivoAguas = new FileWriter(nombreArchivo);
			for (Bebida bebida : aguas) {
				archivoAguas.write(bebida.toString() + "\n\n");
			}
			archivoAguas.close();
			System.out.println("Archivo de aguas por precio creado correctamente.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* Método para exportar documento de bebidas por fecha */
	public void exportarBebidasPorFecha(String nombreArchivo) {
		listaBebidas.sort(new ComparadorBebidasFecha());

		try {
			FileWriter archivoBebidas = new FileWriter(nombreArchivo);
			for (Bebida bebida : listaBebidas) {
				archivoBebidas.write(bebida.toString() + "\n\n");
			}
			archivoBebidas.close();
			System.out.println("Archivo de bebidas por fecha creado correctamente.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
