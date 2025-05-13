package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model;

public class Valoracion {

    private Libro libro;
    private int estrellas;

    public Valoracion(Libro libro, int estrellas) {
        this.libro = libro;
        this.estrellas = estrellas;
    }
}
