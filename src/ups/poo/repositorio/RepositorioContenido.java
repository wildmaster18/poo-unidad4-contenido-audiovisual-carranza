package ups.poo.repositorio;

import java.util.List;
import ups.poo.excepciones.*;
import ups.poo.modelo.ContenidoAudiovisual;

// Repositorio de contenidos audiovisuales
public interface RepositorioContenido {

	// Guarda un nuevo contenido audiovisual.
    void guardar(ContenidoAudiovisual c)
            throws EntradaDuplicadaException, PersistenciaException;
    // Actualiza un contenido audiovisual existente.
    void actualizar(ContenidoAudiovisual c)
            throws ContenidoNoEncontradoException, PersistenciaException;
    // Elimina un contenido audiovisual por su id.
    void eliminar(int id)
            throws ContenidoNoEncontradoException, PersistenciaException;

    // Busca un contenido por su id.
    ContenidoAudiovisual buscarPorId(int id)
            throws ContenidoNoEncontradoException, PersistenciaException;

    // Lista todos los contenidos audiovisuales.
    List<ContenidoAudiovisual> listarTodos() throws PersistenciaException;

    // Lista los contenidos audiovisuales que coinciden con el título.
    int nuevoId() throws PersistenciaException;
}
