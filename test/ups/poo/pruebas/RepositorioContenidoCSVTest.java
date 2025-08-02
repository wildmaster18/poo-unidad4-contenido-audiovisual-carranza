package ups.poo.pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ups.poo.excepciones.*;
import ups.poo.modelo.*;
import ups.poo.repositorio.RepositorioContenidoCSV;

class RepositorioContenidoCSVTest {

    @TempDir Path dir;

    // Test para verificar el funcionamiento del repositorio de contenido CSV
    private RepositorioContenidoCSV nuevoRepo() {
        return new RepositorioContenidoCSV() {      // redirige ruta
            @Override protected Path getRuta() { return dir.resolve("data.csv"); }
        };
    }

    // Test para verificar que se puede guardar y listar contenidos
    @Test
    void crudYDuplicados() throws Exception {
        var repo = nuevoRepo();
        Pelicula p = new Pelicula(1,"Test",100,LocalDate.of(2020,1,1));

        repo.guardar(p);
        assertEquals(1, repo.listarTodos().size());

        assertThrows(EntradaDuplicadaException.class, () -> repo.guardar(p));

        repo.eliminar(1);
        assertTrue(repo.listarTodos().isEmpty());
    }
}
