package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model;

public class Valoracion {

    private Libro libro;
    private int estrellas;

    public Valoracion(Libro libro, int estrellas) {
        this.libro = libro;
        this.estrellas = estrellas;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }
}
