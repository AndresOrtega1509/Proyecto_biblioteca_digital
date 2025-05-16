package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model;

import java.util.LinkedList;

import static co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Biblioteca.listaLectores;

public class Lector {

    private String cedula;
    private String nombre;
    private String apellido;
    private String correo;
    private String passWord;
    public LinkedList<Prestamo> historialPrestamos;
    public LinkedList<Valoracion> valoraciones;

    public Lector(String cedula, String nombre, String apellido, String correo, String passWord) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.passWord = passWord;
        this.historialPrestamos = new LinkedList<>();
        this.valoraciones = new LinkedList<>();
    }

    public Lector(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.historialPrestamos = new LinkedList<>();
        this.valoraciones = new LinkedList<>();
    }


    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


    public LinkedList<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(LinkedList<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }

    public LinkedList<Prestamo> getHistorialPrestamos() {
        return historialPrestamos;
    }

    public void setHistorialPrestamos(LinkedList<Prestamo> historialPrestamos) {
        this.historialPrestamos = historialPrestamos;
    }

    public void prestarLibro(Libro libro) {
        if (libro.estaDisponible()) {
            libro.setPrestado(true);
            historialPrestamos.add(new Prestamo(libro));
            listaLectores.buscarLector("1094").setHistorialPrestamos(historialPrestamos);
            System.out.println(nombre + " ha prestado el libro: " + libro.getTitulo());
        } else {
            libro.agregarAListaDeEspera(this);
        }
    }

    public void valorarLibro(Libro libro, int estrellas) {
        valoraciones.add(new Valoracion(libro, estrellas));
        libro.actualizarValoracion(estrellas);
    }

    public void mostrarHistorialPrestamos() {
        System.out.println("Historial de préstamos de " + nombre + ":");
        for (Prestamo prestamo : historialPrestamos) {
            System.out.println("- " + prestamo.getLibro().getTitulo() + " (fecha: " + prestamo.getFecha() + ")");
        }
    }

}
