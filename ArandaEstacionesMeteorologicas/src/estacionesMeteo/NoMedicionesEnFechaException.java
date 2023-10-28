package estacionesMeteo;

public class NoMedicionesEnFechaException extends Exception {
    public NoMedicionesEnFechaException(String mensaje) {
        super(mensaje);
    }
}
