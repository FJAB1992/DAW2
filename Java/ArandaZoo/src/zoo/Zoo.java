package zoo;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


public class Zoo {
	private ArrayList<Animal> listaAnimales;

	/* Constructor */
	public Zoo() {
		this.listaAnimales = new ArrayList<Animal>();
	}

	/* Getters y setters */
	public ArrayList<Animal> getListaAnimales() {
		return listaAnimales;
	}

	public void setListaAnimales(ArrayList<Animal> listaAnimales) {
		this.listaAnimales = listaAnimales;
	}

	/* Método toString */
	@Override
	public String toString() {
		return "Zoo [listaAnimales=" + listaAnimales + "]";
	}

	/* Método para buscar animal por ID */
	public Animal buscarAnimalID(int id) {
		Animal animalEncontrado = null;
		boolean encontrado = false;
		int i = 0;
		while (i < listaAnimales.size() && !encontrado) {
			Animal animal = listaAnimales.get(i);
			if (animal.getId() == id) {
				animalEncontrado = animal;
				encontrado = true;
			}
			i++;
		}
		return animalEncontrado;
	}

	/* Método para listar animales */
	public void listarAnimales() {
		for (Animal animal : listaAnimales) {
			System.out.println(animal.toString());
		}
	}

	/* Método para mostrar un animal por ID */
	public void mostrarAnimalPorId(int id) {
		buscarAnimalID(id);
		for (Animal animal : listaAnimales) {
			if (animal.getId() == id) {
				System.out.println(animal);
			}
		}
	}

	/* Método para añadir una empleado a la lista */
	public boolean agregarAnimal(Animal animal) {
		boolean agregado = false;
		if (buscarAnimalEdad(animal.getId()) == null) {
			listaAnimales.add(animal);
			agregado = true;
		} else {
			agregado = false;
		}
		return agregado;
	}

	/* Método para eliminar animal */
	public boolean eliminarAnimal(int id) {
		boolean eliminado = false;
		Animal animal = buscarAnimalID(id);
		if (animal != null) {
			listaAnimales.remove(animal);
			eliminado = true;
		}
		return eliminado;
	}

	/*
	 * mostrar animales que se encuentren entre dos fechas de entrada
	 */
	public ArrayList<Animal> buscarAnimalesEntreFechas(LocalDate fechaInicio, LocalDate fechaFin) {
		ArrayList<Animal> animalesEntreFechas = new ArrayList<>();
		for (Animal animal : listaAnimales) {
			LocalDate fechaEntrada = animal.getFechaEntrada();
			if (fechaEntrada.isAfter(fechaInicio) && fechaEntrada.isBefore(fechaFin)) {
				animalesEntreFechas.add(animal);
			}
		}
		return animalesEntreFechas;
	}

	/* Método para buscar animal por edad */
	public Animal buscarAnimalEdad(int edad) {
		Animal animalEncontrado = null;
		boolean encontrado = false;
		int i = 0;
		while (i < listaAnimales.size() && !encontrado) {
			Animal animal = listaAnimales.get(i);
			if (animal.getEdad() == edad) {
				animalEncontrado = animal;
				encontrado = true;
			}
			i++;
		}
		return animalEncontrado;
	}

	/* Método para exportar un fichero de los animales por edad */
	public void exportarAnimalesEdad() {
		ArrayList<Animal> animalesOrdenadosTipo = new ArrayList<>(listaAnimales);
		ComparadorEdades comparador = new ComparadorEdades();
		animalesOrdenadosTipo.sort(comparador);
		/* Tenemos una lista de animales ordenados por edad */

		ArrayList<Animal> listaTerrestres = new ArrayList<>();
		ArrayList<Animal> listaAcuaticos = new ArrayList<>();
		/* Separamos en 2 listas cada uno de los tipos */
		for (Animal animal : animalesOrdenadosTipo) {
			if (animal instanceof Terrestre) {
				listaTerrestres.add(animal);
			}
			if (animal instanceof Acuatico) {
				listaAcuaticos.add(animal);
			}
		}

		try {
			FileWriter archivoTerrestre = new FileWriter("listaTerrestres.txt");
			FileWriter archivoAcuatico = new FileWriter("listaAcuaticos.txt");
			/*
			 * listamos animales y los introduzco en una lista, el for each irá
			 * introduciendo la información de cada animal en el archivo escribiendo con
			 * FileWriter
			 */

			/* Escribimos cada archivo */
			for (Animal animal : listaTerrestres) {
				archivoTerrestre.write(animal.toString() + "\n\n");
			}
			for (Animal animal : listaAcuaticos) {
				archivoAcuatico.write(animal.toString() + "\n\n");
			}
			/* LOS CERRAMOS */
			archivoTerrestre.close();
			archivoAcuatico.close();
			System.out.println("Archivos creado correctamente.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
