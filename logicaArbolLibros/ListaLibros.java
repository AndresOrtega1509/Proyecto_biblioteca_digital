package Proyecto_biblioteca_digital.logicaArbolLibros;

public class ListaLibros {
    private NodoLibroLista cabeza;

    public ListaLibros() {
        this.cabeza = null;
    }

    // Agrega un libro al final de la lista
    public void agregar(Libro libro) {
        NodoLibroLista nuevo = new NodoLibroLista(libro);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoLibroLista actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    // Imprime todos los libros en la lista
    public void imprimir() {
        NodoLibroLista actual = cabeza;
        while (actual != null) {
            System.out.println(actual.libro);
            actual = actual.siguiente;
        }
    }

    // Verifica si la lista está vacía
    public boolean estaVacia() {
        return cabeza == null;
    }

    // Retorna la cabeza de la lista (útil para iteraciones externas)
    public NodoLibroLista getCabeza() {
        return cabeza;
    }
}

