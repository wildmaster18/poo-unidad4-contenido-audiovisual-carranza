package ups.poo.repositorio;

import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;
import ups.poo.excepciones.*;
import ups.poo.modelo.*;
import ups.poo.util.GestorArchivosCSV;

// Repositorio de contenidos audiovisuales en formato CSV
public class RepositorioContenidoCSV implements RepositorioContenido {

	// Ruta del archivo CSV donde se almacenan los contenidos
    private static final Path   RUTA = Paths.get("contenidos.csv");
    private static final String SEP  = ",";
    private static final Pattern NUMERO = Pattern.compile("\\d+");

    // Contador para contenidos de demostración
    private static int     contadorDemo  = 0;
    private static boolean rutaMostrada  = false;

    // Constructor privado para evitar instancias directas
    protected Path getRuta() { return RUTA; }

    // Constructor de la clase
    @Override
    public void guardar(ContenidoAudiovisual c)
            throws EntradaDuplicadaException, PersistenciaException {

    	// Verifica si el contenido ya existe en el repositorio
        if (listarTodos().stream().anyMatch(e -> e.getId() == c.getId()))
            throw new EntradaDuplicadaException(c.getId());

        GestorArchivosCSV.escribirLinea(getRuta(), c.aCSV());
        contadorDemo++;
        notificar("Guardado");
    }

    // Actualiza un contenido audiovisual existente en el repositorio
    @Override
    public void actualizar(ContenidoAudiovisual c)
            throws ContenidoNoEncontradoException, PersistenciaException {
    	
    	// Busca el contenido en la lista y lo reemplaza si existe
        List<ContenidoAudiovisual> lista = listarTodos();
        boolean reemplazado = false;

        // Recorre la lista para encontrar el contenido por su ID
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == c.getId()) {
                lista.set(i, c);
                reemplazado = true;
                break;
            }
        }
        
        // Si no se encontró el contenido, lanza una excepción
        if (!reemplazado) throw new ContenidoNoEncontradoException(c.getId());

        // Sobrescribe el archivo CSV con la lista actualizada
        GestorArchivosCSV.sobreEscribirArchivo(getRuta(),
                lista.stream().map(ContenidoAudiovisual::aCSV).toList());
        notificar("Actualizado");
    }

    // Elimina un contenido audiovisual por su ID
    @Override
    public void eliminar(int id)
            throws ContenidoNoEncontradoException, PersistenciaException {
    	// Busca el contenido en la lista y lo elimina si existe
        List<ContenidoAudiovisual> lista = listarTodos();
        boolean borrado = lista.removeIf(c -> c.getId() == id);
        if (!borrado) throw new ContenidoNoEncontradoException(id);

        // Sobrescribe el archivo CSV con la lista actualizada
        GestorArchivosCSV.sobreEscribirArchivo(getRuta(),
                lista.stream().map(ContenidoAudiovisual::aCSV).toList());
        notificar("Eliminado");
    }

    // Busca un contenido audiovisual por su ID
    @Override
    public ContenidoAudiovisual buscarPorId(int id)
            throws ContenidoNoEncontradoException, PersistenciaException {
    	
    	// Busca el contenido en la lista y lo devuelve si existe
        return listarTodos().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ContenidoNoEncontradoException(id));
    }

    // Lista todos los contenidos audiovisuales del repositorio
    @Override
    public List<ContenidoAudiovisual> listarTodos() throws PersistenciaException {
        List<String> lineas = GestorArchivosCSV.leerLineas(getRuta());
        List<ContenidoAudiovisual> lista = new ArrayList<>();

        // Si el archivo está vacío, retorna una lista vacía
        for (String l : lineas) {
            if (l.isBlank()) continue;
            String[] p = l.split(SEP, -1);
            if (p.length < 5 || !NUMERO.matcher(p[0]).matches()) continue;

            int id          = Integer.parseInt(p[0]);
            String titulo   = p[1];
            int dur         = Integer.parseInt(p[2]);
            LocalDate fecha = LocalDate.parse(p[3]);
            String tipo     = p[4];

            // Verifica que el tipo de contenido sea válido
            switch (tipo) {
                /*──────── Pelicula ────────*/
                case "Pelicula" -> {
                    Pelicula peli = new Pelicula(id, titulo, dur, fecha);
                    if (p.length >= 6 && !p[5].isBlank()) {
                        for (String nom : p[5].split(";"))
                            peli.agregarActor(new Actor(nom.trim()));
                    }
                    lista.add(peli);
                }
                /*──────── SerieTV ────────*/
                case "SerieTV" -> {
                    SerieTV serie = new SerieTV(id, titulo, dur, fecha);
                    if (p.length >= 6 && NUMERO.matcher(p[5]).matches()) {
                        int nTemp = Integer.parseInt(p[5]);
                        for (int i = 1; i <= nTemp; i++) serie.crearTemporada(i);
                    }
                    lista.add(serie);
                }
                /*──────── Documental ─────*/
                case "Documental" -> {
                    String nomInv = (p.length >= 6) ? p[5] : "Desconocido";
                    String espInv = (p.length >= 7) ? p[6] : "N/D";
                    Investigador inv = new Investigador(nomInv, espInv);
                    lista.add(new Documental(id, titulo, dur, fecha, inv));
                }
            }
        }
        return lista;
    }

    // Genera un nuevo ID para un contenido audiovisual
    @Override
    public int nuevoId() throws PersistenciaException {
        return listarTodos().stream()
                .mapToInt(ContenidoAudiovisual::getId)
                .max()
                .orElse(0) + 1;
    }

    // Notifica sobre la acción realizada en el repositorio
    private void notificar(String accion) {
        if (!rutaMostrada) {
            System.out.println("Archivo CSV: " + getRuta().toAbsolutePath());
            rutaMostrada = true;
        }
        if ("Guardado".equals(accion) && contadorDemo == 3) {
            System.out.println("Se añadieron 3 contenidos de demostración.");
            contadorDemo = 0;
        }
    }
}

