package Proyecto_biblioteca_digital.logicaArbolLibros;

public class NodoLibroArbol {
    public Libro libro;
    public NodoLibroArbol izquierda, derecha;

    public NodoLibroArbol(Libro libro) {
        this.libro = libro;
        this.izquierda = null;
        this.derecha = null;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public NodoLibroArbol getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoLibroArbol izquierda) {
        this.izquierda = izquierda;
    }

    public NodoLibroArbol getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoLibroArbol derecha) {
        this.derecha = derecha;
    }
}
