package ups.poo.util;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import ups.poo.excepciones.PersistenciaException;


public final class GestorArchivosCSV {

    private GestorArchivosCSV() {}

    // Método para leer un archivo CSV y devolver una lista de líneas
    public static List<String> leerLineas(Path ruta) throws PersistenciaException {
        
    	
    	try {
            if (Files.notExists(ruta)) return List.of();
            return Files.readAllLines(ruta);
        } catch (IOException e) {
            throw new PersistenciaException("Error leyendo CSV", e);
        }
    }

    // Método para escribir una línea en un archivo CSV
    public static void escribirLinea(Path ruta, String linea)
            throws PersistenciaException {
        try {
            Files.writeString(ruta, linea + System.lineSeparator(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new PersistenciaException("Error escribiendo CSV", e);
        }
    }

    // Método para sobrescribir un archivo CSV con una lista de líneas
    public static void sobreEscribirArchivo(Path ruta, List<String> lineas)
            throws PersistenciaException {
        try {
            Files.write(ruta, lineas, StandardOpenOption.CREATE,
                                   StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new PersistenciaException("Error sobrescribiendo CSV", e);
        }
    }
}

