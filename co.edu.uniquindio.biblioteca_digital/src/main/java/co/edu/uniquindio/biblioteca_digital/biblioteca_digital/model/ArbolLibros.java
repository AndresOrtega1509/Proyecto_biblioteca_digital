package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model;

public class ArbolLibros {

    private NodoLibroArbol raiz;

    // Inserta un libro en el ABB ordenado por título
    public void insertar(Libro libro) {
        raiz = insertarRec(raiz, libro);
    }

    private NodoLibroArbol insertarRec(NodoLibroArbol nodo, Libro libro) {
        if (nodo == null) return new NodoLibroArbol(libro);

        if (libro.getTitulo().compareToIgnoreCase(nodo.libro.getTitulo()) < 0) {
            nodo.izquierda = insertarRec(nodo.izquierda, libro);
        } else {
            nodo.derecha = insertarRec(nodo.derecha, libro);
        }
        return nodo;
    }

    // Busca un libro por título (exacto)
    public Libro buscarPorTitulo(String titulo) {
        return buscarRec(raiz, titulo);
    }

    private Libro buscarRec(NodoLibroArbol nodo, String titulo) {
        if (nodo == null) return null;

        int comparacion = titulo.compareToIgnoreCase(nodo.libro.getTitulo());
        if (comparacion == 0) {
            return nodo.libro;
        } else if (comparacion < 0) {
            return buscarRec(nodo.izquierda, titulo);
        } else {
            return buscarRec(nodo.derecha, titulo);
        }
    }

    // Busca libros por autor (puede haber varios)
    public ListaLibros buscarPorAutor(String autor) {
        ListaLibros resultado = new ListaLibros();
        buscarRecAutor(raiz, autor.toLowerCase(), resultado);
        return resultado;
    }

    private void buscarRecAutor(NodoLibroArbol nodo, String autor, ListaLibros resultado) {
        //   if (nodo != null) {
        //     buscarRecAutor(nodo.izquierda, autor, resultado);
        //   if (nodo.libro.getAutor().toLowerCase().contains(autor)) {
        //      resultado.agregar(nodo.libro);
        //   }
        //    buscarRecAutor(nodo.derecha, autor, resultado);
        // }
    }

    // Busca libros por categoría (puede haber varios)
    public ListaLibros buscarPorCategoria(String categoria) {
        ListaLibros resultado = new ListaLibros();
        buscarRecCategoria(raiz, categoria.toLowerCase(), resultado);
        return resultado;
    }

    private void buscarRecCategoria(NodoLibroArbol nodo, String categoria, ListaLibros resultado) {
       // if (nodo != null) {
        //     buscarRecCategoria(nodo.izquierda, categoria, resultado);
        //    if (nodo.libro.getCategoria().toLowerCase().contains(categoria)) {
        //        resultado.agregar(nodo.libro);
        //    }
        //    buscarRecCategoria(nodo.derecha, categoria, resultado);
        //  }
    }

    // Imprime todos los libros en orden alfabético (inorden)
    public void imprimirInorden() {
        imprimirRec(raiz);
    }

    private void imprimirRec(NodoLibroArbol nodo) {
        if (nodo != null) {
            imprimirRec(nodo.izquierda);
            System.out.println(nodo.libro);
            imprimirRec(nodo.derecha);
        }
    }
}
