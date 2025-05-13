package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model;

public class Main {
    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca();

        Lector lector = biblioteca.registrarLector("1016", "andres", "ortega",
                "andres@gmail.com", "123");

        Lector lector2 = biblioteca.registrarLector("1016", "andres", "ortega",
                "andres@gmail.com", "321");

        System.out.println(biblioteca.iniciarSesion("1007", "123", lector2));
    }
}
