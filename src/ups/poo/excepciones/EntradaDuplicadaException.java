package ups.poo.excepciones;

// Excepción personalizada para indicar que una entrada con un ID ya existe
public class EntradaDuplicadaException extends Exception {
    public EntradaDuplicadaException(int id) {
        super("El id " + id + " ya existe.");
    }
}
