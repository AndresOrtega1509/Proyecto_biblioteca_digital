package Proyecto_biblioteca_digital.logicaArbolLibros;

public class NodoLibroLista {
    public Libro libro;
    public NodoLibroLista siguiente;

    public NodoLibroLista(Libro libro) {
        this.libro = libro;
        this.siguiente = null;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public NodoLibroLista getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLibroLista siguiente) {
        this.siguiente = siguiente;
    }
}

