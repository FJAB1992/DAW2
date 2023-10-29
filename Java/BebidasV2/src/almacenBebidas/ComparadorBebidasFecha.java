package almacenBebidas;

import java.util.Comparator;

public class ComparadorBebidasFecha implements Comparator<Bebida> {
    /* Método compare */
    @Override
    public int compare(Bebida b1, Bebida b2) {
        return compararPorFecha(b1, b2);
    }

    /* Método para comparar por fecha */
    private int compararPorFecha(Bebida b1, Bebida b2) {
        return b1.getFechaEntrada().compareTo(b2.getFechaEntrada());
    }
}
