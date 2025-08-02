package ups.poo.servicio;

import java.util.List;
import ups.poo.excepciones.*;
import ups.poo.modelo.*;
import ups.poo.repositorio.RepositorioContenido;

// Define la implementación del servicio de contenidos audiovisuales
public class ServicioContenidoImpl implements ServicioContenido {

    private final RepositorioContenido repo;

    public ServicioContenidoImpl(RepositorioContenido repo) { this.repo = repo; }

    // Registra un nuevo contenido audiovisual o actualiza uno existente
    @Override
    public ContenidoAudiovisual registrar(ContenidoAudiovisual c)
            throws PersistenciaException, EntradaDuplicadaException {

    	// Verifica si el contenido ya existe en el repositorio
        if (c.getId() == 0)
            c = clonarConNuevoId(c, repo.nuevoId());

        repo.guardar(c);
        return c;
    }

    // Lista todos los contenidos audiovisuales registrados
    @Override public List<ContenidoAudiovisual> listar() throws PersistenciaException {
        return repo.listarTodos();
    }

    // Elimina un contenido audiovisual por su ID
    @Override public void eliminar(int id)
            throws PersistenciaException, ContenidoNoEncontradoException {
        repo.eliminar(id);
    }

    // Busca un contenido audiovisual por su ID
    @Override public ContenidoAudiovisual buscar(int id)
            throws PersistenciaException, ContenidoNoEncontradoException {
        return repo.buscarPorId(id);
    }

    // Clona un contenido audiovisual con un nuevo ID, manteniendo sus datos
    private ContenidoAudiovisual clonarConNuevoId(ContenidoAudiovisual c, int nuevoId) {
        return switch (c) {
            case Pelicula p -> {
                Pelicula n = new Pelicula(nuevoId, p.getTitulo(),
                        p.getDuracionMinutos(), p.getFechaEstreno());
                p.getReparto().forEach(n::agregarActor);
                yield n;
            }
            case SerieTV s -> {
                SerieTV n = new SerieTV(nuevoId, s.getTitulo(),
                        s.getDuracionMinutos(), s.getFechaEstreno());
                for (int i = 1; i <= s.getTemporadas().size(); i++)
                    n.crearTemporada(i);
                yield n;
            }
            case Documental d -> new Documental(nuevoId, d.getTitulo(),
                    d.getDuracionMinutos(), d.getFechaEstreno(), d.getInvestigador());
            default -> throw new IllegalStateException("Tipo no soportado");
        };
    }
}

