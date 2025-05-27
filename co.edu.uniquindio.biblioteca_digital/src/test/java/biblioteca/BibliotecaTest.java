package biblioteca;

import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Biblioteca;
import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BibliotecaTest {

    Biblioteca biblioteca;

    @BeforeEach
    public void init() {

        biblioteca = new Biblioteca();
    }

    @Test
    void testLibroDuplicado() {



        Libro libro1 = biblioteca.agregarLibro("Matemáticas Discretas", "Jorge Montoya", "1996", "Educacion" );
        Libro libro2 = biblioteca.agregarLibro("Matemáticas avanzadas", "Jorge Montoya", "1996", "Educacion" );


        boolean resultado = libro1.getTitulo().equalsIgnoreCase(libro2.getTitulo());

        //assertFalse(resultado);
        assertNotEquals(libro1.getTitulo(),libro2.getTitulo());
        assertNotEquals( libro1.getAutor(), libro2.getAutor());
    }
}

