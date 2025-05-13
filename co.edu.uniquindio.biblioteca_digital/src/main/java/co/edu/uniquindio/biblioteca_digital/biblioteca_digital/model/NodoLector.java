package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model;

public class NodoLector {

    private Lector lector;
    private NodoLector nodoSiguiente;

    public NodoLector(Lector lector){
        this.lector = lector;
        nodoSiguiente = null;
    }

    public Lector getLector() {
        return lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public NodoLector getNodoSiguiente() {
        return nodoSiguiente;
    }

    public void setNodoSiguiente(NodoLector nodoSiguiente) {
        this.nodoSiguiente = nodoSiguiente;
    }
}
