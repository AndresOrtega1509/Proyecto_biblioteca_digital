package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model;

import java.util.ArrayList;
import java.util.List;

public class NodoRed {

    private Lector lector;
    private List<NodoRed> conexiones;

    public NodoRed(Lector lector) {
        this.lector = lector;
        this.conexiones = new ArrayList<>();
    }

    public Lector getLector() {
        return lector;
    }

    public List<NodoRed> getConexiones() {
        return conexiones;
    }

    public void agregarConexion(NodoRed otro) {
        if (!conexiones.contains(otro)) {
            conexiones.add(otro);
        }
    }
}
