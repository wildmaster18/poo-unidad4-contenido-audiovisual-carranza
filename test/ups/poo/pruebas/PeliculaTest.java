package ups.poo.pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import ups.poo.modelo.*;

class PeliculaTest {

	// Test para verificar que el constructor de Pelicula funciona correctamente
    @Test
    void repartoYCSV() {
        Pelicula p = new Pelicula(1, "Matrix", 136, LocalDate.of(1999, 1, 1));
        p.agregarActor(new Actor("Keanu Reeves"));
        p.agregarActor(new Actor("Carrie-Anne Moss"));

        // Verificar que el reparto se ha añadido correctamente
        assertEquals(2, p.getReparto().size());
        String esperado = "1,Matrix,136,1999-01-01,Pelicula,Keanu Reeves;Carrie-Anne Moss";
        assertEquals(esperado, p.aCSV());
    }
}
