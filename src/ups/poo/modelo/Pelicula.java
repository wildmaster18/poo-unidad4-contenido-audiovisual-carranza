package ups.poo.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Define una clase Pelicula que extiende ContenidoAudiovisual
public final class Pelicula extends ContenidoAudiovisual {

    private final List<Actor> reparto = new ArrayList<>();

    // Constructor que recibe los parámetros necesarios 
    public Pelicula(int id, String titulo, int duracionMinutos,
                    LocalDate fechaEstreno) {
        super(id, titulo, duracionMinutos, fechaEstreno);
    }

    // Getters y setters
    public void agregarActor(Actor actor) { reparto.add(actor); }
    public List<Actor> getReparto() { return List.copyOf(reparto); }

    // Método para obtener el nombre de los actores en formato CSV
    @Override public String aCSV() {
        String actores = reparto.stream()
                                .map(Actor::nombre)
                                .collect(Collectors.joining(";"));
        return "%d,%s,%d,%s,Pelicula,%s".formatted(
                getId(), getTitulo(), getDuracionMinutos(),
                getFechaEstreno(), actores);
    }

    // Método para mostrar los detalles de la película
    @Override public String mostrarDetalles() {
        String actores = reparto.isEmpty()
                ? "Sin actores"
                : reparto.stream()
                         .map(Actor::nombre)
                         .collect(Collectors.joining(", "));
        return "%-5d | %-20s | %-10d | %-6d | Película (%s)"
               .formatted(getId(), getTitulo(),
                          getDuracionMinutos(),
                          getFechaEstreno().getYear(),
                          actores);
    }
}
