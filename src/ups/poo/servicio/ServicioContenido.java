package ups.poo.servicio;

import java.util.List;
import ups.poo.excepciones.*;
import ups.poo.modelo.ContenidoAudiovisual;

// Define la interfaz ServicioContenido que declara los métodos para manejar contenidos audiovisuales
public interface ServicioContenido {
	// Método para registrar un contenido audiovisual
    ContenidoAudiovisual registrar(ContenidoAudiovisual c) throws PersistenciaException, EntradaDuplicadaException;
    // Método para actualizar un contenido audiovisual
    List<ContenidoAudiovisual> listar()       throws PersistenciaException;
    void eliminar(int id)                     throws PersistenciaException, ContenidoNoEncontradoException;
    // Método para buscar un contenido audiovisual por su ID
    ContenidoAudiovisual buscar(int id)       throws PersistenciaException, ContenidoNoEncontradoException;
}
