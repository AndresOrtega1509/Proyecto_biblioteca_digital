package biblioteca;

import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.*;
import javafx.scene.control.ComboBox;
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
        Libro libro1 = biblioteca.agregarLibro("Matemáticas avanzadas", "Jorge Montoya", "1996", "Educacion" );
        Libro libro2 = biblioteca.agregarLibro("Matemáticas avanzadas", "Jorge Montoya", "1996", "Educacion" );
        assertNotEquals(libro1.getTitulo(),libro2.getTitulo());
        assertNotEquals( libro1.getAutor(), libro2.getAutor());
    }
    @Test
    void testPrestarLibroDisponible() {

        Lector lector = new Lector("123", "Miguel", "Sanchez", "miguel@gmail.com", "111");
        Libro libroDisponible = new Libro("Matemáticas Discretas", "Jorge Montoya", 1996, "Educacion", false, 0);
        String estadoDelPrestamo = lector.prestarLibro(libroDisponible);
        assertEquals( "prestado con exito", estadoDelPrestamo);
    }

    @Test
    void testPrestarLibroNoDisponible() {

        Lector lector = new Lector("123", "Miguel", "Sanchez", "miguel@gmail.com", "111");
        Libro libroDisponible = new Libro("Matemáticas Discretas", "Jorge Montoya", 1996, "Educacion", true, 0);
        String estadoDelPrestamo = lector.prestarLibro(libroDisponible);
        assertEquals( "No se pudo prestar el libro", estadoDelPrestamo);
    }

    @Test
    void testLectorIdUnico() {
        //arrange
        biblioteca.registrarLector("124", "Miguel", "Sanchez", "miguel@gmail.com", "111");
        //ACT
        try {
            biblioteca.registrarLector("124", "Miguel", "Sanchez", "miguel@gmail.com", "111");
        }catch (Exception e){
            //ASSERT
            assertEquals ("Ya existe un usuario con la cedula: 124",e.getMessage());
        }
    }

    @Test
    void testRegistrarLectorExitosamente() {
        //arrange
        Lector lectorRegistrado = biblioteca.registrarLector("147", "karen", "Sanchez", "karen@gmail.com", "111");

        //ACT

        Lector lectorConsultado = biblioteca.getListaLectores().buscarLector("147");

        //asert
        assertEquals(lectorRegistrado.getCedula(),lectorConsultado.getCedula());
    }

    @Test
    void testNoIniciarSesionConPasswordInconrrecto() {
        //arrange
        Lector lectorRegistrado = biblioteca.registrarLector("147", "karen", "Sanchez", "karen@gmail.com", "111");
        try {
            //ACT
            biblioteca.iniciarSesion("147", "2222");

        }catch (Exception e){
            //ASSERT
            assertEquals ("Los datos de acceso son incorrectos" ,e.getMessage());
        }
    }


    @Test
    void testBuscarLibroPorTitulo() {
        //arrange
        Libro libro = new Libro("Matemáticas Discretas", "Jorge Montoya", 1996, "Educacion", false, 0);
        ArbolLibros arbolLibros = new ArbolLibros();
        arbolLibros.insertar(libro);
        //ACT
        Libro libroBuscado =  arbolLibros.buscarPorTitulo("Matemáticas Discretas");
        //ASSERT
        assertEquals (libro.getTitulo() ,libroBuscado.getTitulo());
        assertEquals (libro.getAutor() ,libroBuscado.getAutor());
        assertEquals (libro.getAnio() ,libroBuscado.getAnio());
        assertEquals (libro.getCategoria() ,libroBuscado.getCategoria());

    }
}

