package Proyecto_biblioteca_digital.logicaArbolLibros;

public class Main {
    public static void main(String[] args) {
        ArbolLibros arbol = new ArbolLibros();

        arbol.insertar(new Libro("El Hobbit", "J.R.R. Tolkien", 1937, "Fantasía"));
        arbol.insertar(new Libro("1984", "George Orwell", 1949, "Distopía"));
        arbol.insertar(new Libro("Cien Años de Soledad", "Gabriel García Márquez", 1967, "Realismo Mágico"));

        System.out.println("Buscar por título:");
        System.out.println(arbol.buscarPorTitulo("1984"));

        System.out.println("\nBuscar por autor:");
        ListaLibros porAutor = arbol.buscarPorAutor("Tolkien");
        porAutor.imprimir();

        System.out.println("\nBuscar por categoría:");
        ListaLibros porCategoria = arbol.buscarPorCategoria("Fantasía");
        porCategoria.imprimir();

        System.out.println("\nCatálogo completo (inorden):");
        arbol.imprimirInorden();
    }
}

