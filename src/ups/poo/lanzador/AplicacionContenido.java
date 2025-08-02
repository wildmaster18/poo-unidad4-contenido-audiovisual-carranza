package ups.poo.lanzador;

import ups.poo.controlador.ContenidoControlador;
import ups.poo.repositorio.RepositorioContenidoCSV;
import ups.poo.servicio.ServicioContenidoImpl;
import ups.poo.vista.ConsolaVista;

// Inicia la aplicación de gestión de contenido audiovisual
public final class AplicacionContenido {
	
	// Constructor para evitar instanciación
    public static void main(String[] args) {
        var repo = new RepositorioContenidoCSV();
        var servicio = new ServicioContenidoImpl(repo);
       
        // Crea el controlador, la vista, y arranca la aplicación
        var ctrl = new ContenidoControlador(servicio);
        var vista = new ConsolaVista(ctrl);
        vista.iniciar();

    }
}
