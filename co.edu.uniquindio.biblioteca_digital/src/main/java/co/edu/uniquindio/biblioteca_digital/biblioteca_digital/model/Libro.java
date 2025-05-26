package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model;


import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers.ComparadorPrioridadUsuario;

import java.util.PriorityQueue;

public class Libro {

    private String titulo;
    private boolean prestado;
    private PriorityQueue<Lector> listaDeEspera;
    private String autor;
    private int anio;
    private String categoria;
    private int totalValoraciones;
    private int sumaEstrellas;
    private double calificacionPromedio;


    public Libro(String titulo, String autor, int anio, String categoria, boolean estado, double calificacionPromedio) {
        this.titulo = titulo;
        this.prestado = estado;
        this.autor = autor;
        this.anio = anio;
        this.categoria = categoria;
        this.calificacionPromedio = calificacionPromedio;
        this.listaDeEspera = new PriorityQueue<>(new ComparadorPrioridadUsuario());
    }

    public boolean estaDisponible() {
        return !prestado;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

    public void agregarAListaDeEspera(Lector usuario) {
        listaDeEspera.offer(usuario);
        System.out.println("Usuario agregado a la lista de espera del libro: " + titulo);
    }

    public void actualizarValoracion(int estrellas) {
        sumaEstrellas += estrellas;
        totalValoraciones++;
        calificacionPromedio = (double) sumaEstrellas / totalValoraciones;
    }

    public double getCalificacionPromedio() {
        return calificacionPromedio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public PriorityQueue<Lector> getListaDeEspera() {
        return listaDeEspera;
    }

    public void setListaDeEspera(PriorityQueue<Lector> listaDeEspera) {
        this.listaDeEspera = listaDeEspera;
    }

    public String getEstado() {
        return prestado ? "prestado" : "disponible";
    }

}
