package Proyecto_biblioteca_digital.logicaArbolLibros;

public class Libro {
    private String titulo;
    private String autor;
    private int anio;
    private String categoria;
    private boolean disponible;
    private double calificacionPromedio;
    private int cantidadValoraciones;

    public Libro(String titulo, String autor, int anio, String categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.categoria = categoria;
        this.disponible = true;
        this.calificacionPromedio = 0.0;
        this.cantidadValoraciones = 0;
    }

    public void valorar(int estrellas) {
        calificacionPromedio = ((calificacionPromedio * cantidadValoraciones) + estrellas) / ++cantidadValoraciones;
    }

    public boolean estaDisponible() {
        return disponible;
    }

    public void prestar() {
        disponible = false;
    }

    public void devolver() {
        disponible = true;
    }

    // Getters y setters...


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public double getCalificacionPromedio() {
        return calificacionPromedio;
    }

    public void setCalificacionPromedio(double calificacionPromedio) {
        this.calificacionPromedio = calificacionPromedio;
    }

    public int getCantidadValoraciones() {
        return cantidadValoraciones;
    }

    public void setCantidadValoraciones(int cantidadValoraciones) {
        this.cantidadValoraciones = cantidadValoraciones;
    }

    @Override
    public String toString() {
        return titulo + " - " + autor + " (" + anio + ") [" + categoria + "]";
    }
}

