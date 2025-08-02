package ups.poo.excepciones;

// Excepción personalizada para indicar que un contenido no fue encontrado
public class ContenidoNoEncontradoException extends Exception {
    public ContenidoNoEncontradoException(int id) {
        super("No se encontró contenido con id " + id);
    }
}
