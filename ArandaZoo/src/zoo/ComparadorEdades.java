package zoo;

import java.util.Comparator;

public class ComparadorEdades implements Comparator<Animal> {

	/* Método compare */
	@Override
	public int compare(Animal a1, Animal a2) {
		return compararPorEdad(a1, a2);
	}

	/* Método para comparar por precio */
	private int compararPorEdad(Animal a1, Animal a2) {
		/* cast a Integer */
		return Integer.compare(a1.getEdad(), a2.getEdad());
	}
}
