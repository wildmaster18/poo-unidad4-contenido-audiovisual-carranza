package ups.poo.excepciones;

// Excepción personalizada para indicar que la entrada es inválida
public class EntradaInvalidaException extends Exception {
    public EntradaInvalidaException(String msg) { super(msg); }
}
