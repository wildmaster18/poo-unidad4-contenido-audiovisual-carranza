# POO – Tarea Unidad 4: Gestor de Contenidos Audiovisuales

## Descripción  
Aplicación de consola en Java para gestionar **películas**, **series de TV** y **documentales**. Permite crear, listar, eliminar y exportar contenidos. Emplea una arquitectura en capas (MVC + DAO + Servicio), persistencia en CSV, validaciones seguras y pruebas JUnit 5.

## Objetivos y propósito  
- Separar la presentación, la lógica de negocio y la persistencia.  
- Aplicar principios SOLID (responsabilidad única, inversión de dependencias, etc.).  
- Almacenar datos en un archivo CSV y exportar a TXT.  
- Validar entradas en bucle para evitar errores de formato.  
- Verificar automáticamente las funcionalidades con JUnit 5.

## Clases y funcionalidades principales  
- **`AplicacionContenido`** (lanzador)  
  - Arranca la vista y enlaza repositorio y controlador.  
- **Modelo (`ups.poo.modelo`)**  
  - `ContenidoAudiovisual` (abstracta), subclases: `Pelicula`, `SerieTV`, `Documental`.  
  - `Actor`, `Investigador` y `Temporada` para asociaciones.  
- **Repositorio (`ups.poo.repositorio`)**  
  - `RepositorioContenido` (interfaz) y `RepositorioContenidoCSV` (CSV).  
- **Servicio (`ups.poo.servicio`)**  
  - `ServicioContenido` (interfaz) y `ServicioContenidoImpl` (reglas, clonación de IDs).  
- **Controlador (`ups.poo.controlador`)**  
  - `ContenidoControlador` coordina vista y servicio.  
- **Vista (`ups.poo.vista`)**  
  - `ConsolaVista` muestra menú, lee entradas y valida datos.  
- **Utilidades (`ups.poo.util`)**  
  - `GestorArchivosCSV` (I/O de CSV), `GeneradorId` (secuencia de IDs).  
- **Pruebas (`ups.poo.pruebas`)**  
  - `PeliculaTest`, `SerieTVTest`, `DocumentalTest`, `RepositorioContenidoCSVTest`, `ServicioContenidoTest`.

## Cómo clonar y ejecutar  
1. Clonar repositorio  
   ```bash
   git clone https://github.com/TU_USUARIO/gestor-contenidos-audiovisuales.git
   cd gestor-contenidos-audiovisuales
