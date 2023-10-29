package almacenBebidas;

import java.util.Comparator;

public class ComparadorBebidasPrecio implements Comparator<Bebida> {

	/* Método compare */
	@Override
	public int compare(Bebida b1, Bebida b2) {
		return compararPorPrecio(b1, b2);
	}

	/* Método para comparar por precio */
	private int compararPorPrecio(Bebida b1, Bebida b2) {
		/* cast a double */
		return Double.compare(b1.getPrecio(), b2.getPrecio());
	}

}
