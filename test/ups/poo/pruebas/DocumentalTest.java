package ups.poo.pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import ups.poo.modelo.*;

class DocumentalTest {

	// Test para verificar que el constructor de Documental funciona correctamente
    @Test
    void investigadorEnCSV() {
        Investigador inv = new Investigador("Attenborough","Naturaleza");
        Documental d = new Documental(5,"Planeta",90,LocalDate.of(2006,1,1),inv);
        
        // Verificar que el investigador se ha añadido correctamente
        String csv = d.aCSV();
        assertTrue(csv.contains("Documental"));
        assertTrue(csv.endsWith("Attenborough,Naturaleza"));
    }
}
