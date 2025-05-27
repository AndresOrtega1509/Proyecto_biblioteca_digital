package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model;

import java.util.ArrayList;
import java.util.List;

public class NodoConexionGrafo {

    private Lector lector;
    private List<NodoConexionGrafo> conexiones;

    public NodoConexionGrafo(Lector lector) {
        this.lector = lector;
        this.conexiones = new ArrayList<>();
    }

    public Lector getLector() {
        return lector;
    }

    public List<NodoConexionGrafo> getConexiones() {
        return conexiones;
    }

    public void agregarConexion(NodoConexionGrafo otro) {
        if (!conexiones.contains(otro)) {
            conexiones.add(otro);
        }
    }
}
