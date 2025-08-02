package ups.poo.modelo;

import java.util.ArrayList;
import java.util.List;

// Define una clase Temporada que representa una temporada de una serie de TV
public class Temporada {

    private final int numero;
    private final List<String> episodios = new ArrayList<>();

    // Constructor que recibe el número de la temporada
    public Temporada(int numero) { this.numero = numero; }

    // Getters para obtener el número de la temporada y la lista de episodios
    public int getNumero()            { return numero; }
    public List<String> getEpisodios(){ return List.copyOf(episodios); }

    // Método para agregar un episodio a la temporada
    public void agregarEpisodio(String titulo) { episodios.add(titulo); }
}
