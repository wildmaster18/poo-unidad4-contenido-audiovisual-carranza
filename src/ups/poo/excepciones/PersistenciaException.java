package ups.poo.excepciones;

// Excepción personalizada para indicar problemas de persistencia
public class PersistenciaException extends Exception {
    public PersistenciaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
