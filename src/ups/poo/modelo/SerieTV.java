package ups.poo.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Define una clase SerieTV que extiende ContenidoAudiovisual
public final class SerieTV extends ContenidoAudiovisual {

    private final List<Temporada> temporadas = new ArrayList<>();

    // Constructor que recibe los parámetros necesarios
    public SerieTV(int id, String titulo, int duracionMinutos,
                   LocalDate fechaEstreno) {
        super(id, titulo, duracionMinutos, fechaEstreno);
    }

    // Getters y setters
    public Temporada crearTemporada(int numero) {
        Temporada t = new Temporada(numero);
        temporadas.add(t);
        return t;
    }
    public List<Temporada> getTemporadas() { return List.copyOf(temporadas); }

    // Método para obtener el número total de episodios en todas las temporadas
    @Override public String aCSV() {
        return "%d,%s,%d,%s,SerieTV,%d".formatted(
                getId(), getTitulo(), getDuracionMinutos(),
                getFechaEstreno(), temporadas.size());
    }

    // Método para mostrar los detalles de la serie de TV
    @Override public String mostrarDetalles() {
        return "%-5d | %-20s | %-10d | %-6d | SerieTV (%d temp.)"
               .formatted(getId(), getTitulo(),
                          getDuracionMinutos(),
                          getFechaEstreno().getYear(),
                          temporadas.size());
    }
}
