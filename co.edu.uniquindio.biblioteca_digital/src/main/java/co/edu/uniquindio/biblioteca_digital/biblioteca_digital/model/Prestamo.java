package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model;

import java.time.LocalDate;

public class Prestamo {

    private Libro libro;
    private LocalDate fecha;

    public Prestamo(Libro libro) {
        this.libro = libro;
        this.fecha = LocalDate.now();
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
