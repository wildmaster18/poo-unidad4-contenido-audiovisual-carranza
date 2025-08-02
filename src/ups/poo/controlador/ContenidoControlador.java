package ups.poo.controlador;


import ups.poo.excepciones.*;
import ups.poo.modelo.ContenidoAudiovisual;
import ups.poo.servicio.ServicioContenido;

import java.util.List;

// Controlador para gestionar contenidos audiovisuales.
public class ContenidoControlador {

	// Atributos
    private final ServicioContenido servicio;

    // Constructor que recibe el servicio de contenidos
    public ContenidoControlador(ServicioContenido servicio) {
        this.servicio = servicio;
    }
    
    // Métodos para gestionar contenidos audiovisuales
    public ContenidoAudiovisual crear(ContenidoAudiovisual c)
            throws PersistenciaException, EntradaDuplicadaException {
        return servicio.registrar(c);
    }

    // Método para cargar contenidos de demostración
    public List<ContenidoAudiovisual> listar() throws PersistenciaException {
        return servicio.listar();
    }

    // Método para eliminar un contenido por su ID
    public void eliminar(int id)
            throws PersistenciaException, ContenidoNoEncontradoException {
        servicio.eliminar(id);
    }

    // Método para exportar contenidos a un archivo de texto
    public ContenidoAudiovisual buscar(int id)
            throws PersistenciaException, ContenidoNoEncontradoException {
        return servicio.buscar(id);
    }
}

