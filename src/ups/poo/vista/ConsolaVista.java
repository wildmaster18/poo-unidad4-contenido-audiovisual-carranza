package ups.poo.vista;

import ups.poo.controlador.ContenidoControlador;
import ups.poo.excepciones.*;
import ups.poo.modelo.*;

import java.nio.file.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
 
public class ConsolaVista {

	// Constante para la ruta del archivo de salida
    private static final Path TXT_SALIDA = Paths.get("contenidos.txt");

    // Controlador de contenidos
    private final ContenidoControlador ctrl;
    private final Scanner sc = new Scanner(System.in);

    // Constructor que recibe el controlador
    public ConsolaVista(ContenidoControlador ctrl) { this.ctrl = ctrl; }

    // Método principal para iniciar la aplicación
    public void iniciar() {
        String opcion;
        
        // Bucle principal del menú
        do {
            imprimirSeparador();
            System.out.println(" GESTOR DE CONTENIDOS AUDIOVISUALES ");
            imprimirSeparador();
            System.out.println("1. Cargar contenidos de demostración");
            System.out.println("2. Agregar contenido");
            System.out.println("3. Listar contenidos");
            System.out.println("4. Eliminar contenido");
            System.out.println("5. Exportar contenidos a TXT");
            System.out.println("0. Salir");
            imprimirSeparador();
            System.out.print("Seleccione: ");
            opcion = sc.nextLine().trim();

            // Manejo de excepciones para las operaciones del menú
            try {
                switch (opcion) {
                    case "1" -> cargarDemo();
                    case "2" -> agregarContenido();
                    case "3" -> listar();
                    case "4" -> eliminar();
                    case "5" -> exportarTxt();
                    case "0" -> System.out.println("Programa finalizado.");
                    default  -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        } while (!opcion.equals("0"));
    }


    // Método para cargar contenidos de demostración
    private void cargarDemo() throws PersistenciaException, EntradaDuplicadaException {
        Pelicula peli = new Pelicula(0, "Matrix", 136, LocalDate.of(1999, 1, 1));
        peli.agregarActor(new Actor("Keanu Reeves"));
        ctrl.crear(peli);

        // Crear una serie de TV con temporadas y episodios
        SerieTV serie = new SerieTV(0, "Stranger Things", 50, LocalDate.of(2016, 1, 1));
        serie.crearTemporada(1).agregarEpisodio("Capítulo uno");
        serie.crearTemporada(2);
        ctrl.crear(serie);

        // Crear un documental con un investigador
        Documental doc = new Documental(0, "Planeta Tierra", 90,
                LocalDate.of(2006, 1, 1),
                new Investigador("David Attenborough", "Naturaleza"));
        ctrl.crear(doc);
    }

    // Método para agregar un nuevo contenido audiovisual
    private void agregarContenido() throws PersistenciaException, EntradaDuplicadaException {
        imprimirSeparador();
        System.out.println(" AGREGAR CONTENIDO");
        imprimirSeparador();
        System.out.println("1. Película");
        System.out.println("2. Serie de TV");
        System.out.println("3. Documental");
        System.out.print("Tipo: ");
        String tipo = sc.nextLine().trim();

        System.out.print("Título: ");
        String titulo = sc.nextLine().trim();

        int duracion = leerEntero("Duración en minutos: ", 1);

        int anio = leerEntero("Año de estreno (AAAA): ", 1800);
        LocalDate fechaEstreno = LocalDate.of(anio, 1, 1);

        // Validación del tipo de contenido y creación del objeto correspondiente
        switch (tipo) {
            case "1" -> {
                Pelicula pel = new Pelicula(0, titulo, duracion, fechaEstreno);
                int n = leerEntero("¿Cuántos actores desea ingresar? ", 0);
                for (int i = 1; i <= n; i++) {
                    System.out.print("Nombre actor " + i + ": ");
                    pel.agregarActor(new Actor(sc.nextLine().trim()));
                }
                ctrl.crear(pel);
            }
            case "2" -> {
                SerieTV s = new SerieTV(0, titulo, duracion, fechaEstreno);
                int t = leerEntero("¿Número de temporadas a crear ahora? ", 0);
                for (int i = 1; i <= t; i++) s.crearTemporada(i);
                ctrl.crear(s);
            }
            case "3" -> {
                System.out.print("Nombre del investigador: ");
                String nomInv = sc.nextLine().trim();
                System.out.print("Especialidad del investigador: ");
                String espInv = sc.nextLine().trim();
                Investigador inv = new Investigador(nomInv, espInv);
                Documental doc = new Documental(0, titulo, duracion, fechaEstreno, inv);
                ctrl.crear(doc);
            }
            default -> System.out.println("Tipo no reconocido. Operación cancelada.");
        }
        System.out.println("Registro completado.");
    }

    // Método para listar todos los contenidos registrados 
    private void listar() throws PersistenciaException {
        List<ContenidoAudiovisual> lista = ctrl.listar();
        imprimirSeparador();
        System.out.println(" LISTA DE CONTENIDOS");
        imprimirSeparador();
        if (lista.isEmpty()) {
            System.out.println("No existen contenidos registrados.");
            return;
        }
        System.out.printf("%-5s | %-20s | %-10s | %-6s | %-24s%n",
                "ID", "Título", "Duración", "Año", "Tipo");
        imprimirSeparador();
        lista.forEach(c -> System.out.println(c.mostrarDetalles()));
        System.out.println("\nTotal de registros: " + lista.size());
    }

    // Método para eliminar un contenido por su ID
    private void eliminar() throws PersistenciaException, ContenidoNoEncontradoException {
        listar();
        int id = leerEntero("Ingrese el ID del contenido a eliminar: ", 1);
        ctrl.eliminar(id);
        System.out.println("Contenido eliminado.");
    }

    // Método para exportar los contenidos a un archivo de texto
    private void exportarTxt() throws Exception {
        List<String> lineas = ctrl.listar().stream()
                .map(ContenidoAudiovisual::mostrarDetalles)
                .collect(Collectors.toList());
        Files.write(TXT_SALIDA, lineas);
        System.out.println("Contenidos exportados a contenidos.txt");
    }

    // Método auxiliar para leer un entero con validación
    private int leerEntero(String mensaje, int minimo) {
        while (true) {
            System.out.print(mensaje);
            String entrada = sc.nextLine().trim();
            try {
                int valor = Integer.parseInt(entrada);
                if (valor >= minimo) return valor;
                System.out.println("Debe ser un número mayor o igual a " + minimo + ".");
            } catch (NumberFormatException nfe) {
                System.out.println("Entrada inválida. Ingrese solo números.");
            }
        }
    }

    // Método para imprimir un separador visual en la consola
    private void imprimirSeparador() { System.out.println("=".repeat(60)); }
}
