package ups.poo.modelo;

import java.time.LocalDate;

public abstract class ContenidoAudiovisual {

    private final int id;
    private String titulo;
    private int duracionMinutos;
    private LocalDate fechaEstreno;

    // Constructor protegido para evitar instanciación directa
    protected ContenidoAudiovisual(int id, String titulo,
                                   int duracionMinutos, LocalDate fechaEstreno) {
        this.id = id;
        this.titulo = titulo;
        this.duracionMinutos = duracionMinutos;
        this.fechaEstreno = fechaEstreno;
    }


    // Getters y setters
    public int getId()                        { return id; }
    public String getTitulo()                 { return titulo; }
    public void   setTitulo(String titulo)    { this.titulo = titulo; }

    // Duración en minutos
    public int  getDuracionMinutos()                 { return duracionMinutos; }
    public void setDuracionMinutos(int duracion)     { this.duracionMinutos = duracion; }

    // Fecha de estreno
    public LocalDate getFechaEstreno()                { return fechaEstreno; }
    public void      setFechaEstreno(LocalDate fecha) { this.fechaEstreno = fecha; }

	// Métodos abstractos que deben ser implementados por las subclases
    public abstract String aCSV();
    
    // Método para mostrar detalles del contenido audiovisual
    public abstract String mostrarDetalles();
}
