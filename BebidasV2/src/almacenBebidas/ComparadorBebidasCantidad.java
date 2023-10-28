package almacenBebidas;

import java.util.Comparator;

public class ComparadorBebidasCantidad implements Comparator<Bebida> {
	/* Método compare */
	@Override
	public int compare(Bebida b1, Bebida b2) {
		return compararPorUnidades(b1, b2);
	}

	/* Método para comparar por unidades */
	private int compararPorUnidades(Bebida b1, Bebida b2) {
		int unidadesB1 = b1.getUnidades();
		int unidadesB2 = b2.getUnidades();

		if (unidadesB1 < unidadesB2) {
			return -1;
		} else if (unidadesB1 > unidadesB2) {
			return 1;
		} else {
			return 0;
		}
	}
}
