package ups.poo.pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import ups.poo.excepciones.*;
import ups.poo.modelo.Pelicula;
import ups.poo.repositorio.RepositorioContenidoCSV;
import ups.poo.servicio.ServicioContenidoImpl;

class ServicioContenidoTest {

    @TempDir Path dir;

    // Test para verificar el funcionamiento del servicio de contenido
    private ServicioContenidoImpl servicio() {
        var repo = new RepositorioContenidoCSV() {
            @Override protected Path getRuta() { return dir.resolve("repo.csv"); }
        };
        return new ServicioContenidoImpl(repo);
    }

    // Test para verificar que se puede registrar, listar, buscar y eliminar contenidos
    @Test
    void flujoFelizYNoEncontrado() throws Exception {
        var svc = servicio();
        Pelicula original = new Pelicula(0, "Demo", 90, LocalDate.of(2021, 1, 1));

        Pelicula guardada = (Pelicula) svc.registrar(original);   // alta (id asignado)
        assertTrue(guardada.getId() > 0);
        assertFalse(svc.listar().isEmpty());

        svc.eliminar(guardada.getId());                           // baja
        assertTrue(svc.listar().isEmpty());

        assertThrows(ContenidoNoEncontradoException.class,
                     () -> svc.buscar(guardada.getId()));
    }
}
