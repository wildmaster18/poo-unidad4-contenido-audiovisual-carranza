package ups.poo.pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import ups.poo.modelo.*;

class SerieTVTest {

	// Test para verificar que el constructor de SerieTV funciona correctamente
    @Test
    void temporadasYSalida() {
        SerieTV s = new SerieTV(3, "ST", 50, LocalDate.of(2016,1,1));
        s.crearTemporada(1);
        s.crearTemporada(2);

        // Verificar que las temporadas se han añadido correctamente
        assertEquals(2, s.getTemporadas().size());
        assertTrue(s.mostrarDetalles().contains("(2 temp.)"));
    }
}
