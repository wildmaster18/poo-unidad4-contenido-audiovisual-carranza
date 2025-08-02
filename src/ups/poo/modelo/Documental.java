package ups.poo.modelo;

import java.time.LocalDate;

// Define una clase Documental que extiende ContenidoAudiovisual
public final class Documental extends ContenidoAudiovisual {

    private Investigador investigador;

    // Constructor que recibe los parámetros necesarios
    public Documental(int id, String titulo, int duracionMinutos,
                      LocalDate fechaEstreno, Investigador investigador) {
        super(id, titulo, duracionMinutos, fechaEstreno);
        this.investigador = investigador;
    }

    // Getters y setters
    public Investigador getInvestigador()            { return investigador; }
    public void setInvestigador(Investigador inv)    { this.investigador = inv; }

    // Método para obtener el nombre del investigador
    @Override public String aCSV() {
        return "%d,%s,%d,%s,Documental,%s,%s".formatted(
                getId(), getTitulo(), getDuracionMinutos(), getFechaEstreno(),
                investigador.nombre(), investigador.especialidad());
    }

    // Método para mostrar los detalles del documental
    @Override public String mostrarDetalles() {
        return "%-5d | %-20s | %-10d | %-6d | Documental (%s)"
               .formatted(getId(), getTitulo(),
                          getDuracionMinutos(),
                          getFechaEstreno().getYear(),
                          investigador.nombre());
    }
}
