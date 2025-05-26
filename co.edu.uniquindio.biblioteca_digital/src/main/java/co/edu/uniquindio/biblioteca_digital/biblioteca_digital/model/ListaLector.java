package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model;

import java.util.ArrayList;
import java.util.List;

public class ListaLector {

    private static NodoLector nodoPrimero;
    private int tamanio;

    public ListaLector(){
        nodoPrimero = null;
        tamanio = 0;
    }

    public int getTamanio(){
        return tamanio;
    }

    public void agregar(Lector lector){
        NodoLector nuevo = new NodoLector(lector);
        if (tamanio == 0){
            nodoPrimero = nuevo;
        }else {
            NodoLector actual = nodoPrimero;

            while (actual.getNodoSiguiente() != null){
                actual = actual.getNodoSiguiente();
            }

            actual.setNodoSiguiente(nuevo);
        }
        tamanio++;
    }

    public Lector buscarLector(String cedula){
        NodoLector actual = nodoPrimero;
        while (actual != null){
            if (actual.getLector().getCedula().equalsIgnoreCase(cedula)){
                return actual.getLector();
            }
            actual = actual.getNodoSiguiente();
        }
        return null;
    }

    public static List<Lector> obtenerLectores() {
        List<Lector> lectores = new ArrayList<>();
        NodoLector actual = nodoPrimero;

        while (actual != null) {
            lectores.add(actual.getLector());
            actual = actual.getNodoSiguiente();
        }

        return lectores;
    }

    public String recorrerLectores(Libro libro){
        NodoLector actual = nodoPrimero;

        while (actual != null) {
            List<Prestamo> historial = actual.getLector().getHistorialPrestamos();

            for (Prestamo prestamo : historial) {
                if (prestamo.getLibro().getTitulo().equalsIgnoreCase(libro.getTitulo())) {
                    return actual.getLector().getNombre(); // Devuelve el nombre del lector que prestó el libro
                }
            }

            actual = actual.getNodoSiguiente();
        }

        return null; // Si ningún lector ha prestado el libro
    }
}
