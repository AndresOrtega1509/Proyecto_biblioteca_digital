package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers;

import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Lector;
import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Libro;
import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Prestamo;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.HashMap;
import java.util.PriorityQueue;

public class PanelLectorController {

    @FXML
    private TextField txtNombre, txtId, txtTituloPrestamo, txtTituloValoracion, txtEstrellas;
    @FXML private TextArea areaHistorial, areaLibrosDisponibles, areaListaEspera;
    @FXML private TextField txtTituloConsultaEspera, txtNombreEspera, txtIdEspera;
    @FXML
    private Label labelUsuario;


    private Lector usuario;
    private Lector usuarioRegistrado;
    private HashMap<String, Libro> biblioteca = new HashMap<>();

    // Aquí se utiliza una PriorityQueue para simular la cola de espera
    private HashMap<String, PriorityQueue<Lector>> colasEspera = new HashMap<>();

    public void inicializarUsuario(Lector usuario) {
        usuarioRegistrado = usuario;
        labelUsuario.setText("Bienvenido, " + usuario.getNombre());
    }
    @FXML
    public void initialize() {
        // Crear libros de prueba
        biblioteca.put("Cien años de soledad", new Libro("Cien años de soledad"));
        biblioteca.put("1984", new Libro("1984"));

        comboLibros.getItems().addAll(biblioteca.keySet());

        comboLibros.setOnAction(e -> {
            boolean libroSeleccionado = comboLibros.getValue() != null;
            btnPrestar.setDisable(!libroSeleccionado);
            btnValorar.setDisable(!libroSeleccionado);
            btnConsultarCola.setDisable(!libroSeleccionado);
            btnHistorial.setDisable(!libroSeleccionado);
        });

        mostrarLibrosDisponibles();

    }

    @FXML
    public void handlePrestarLibro() {
        if (usuarioRegistrado == null) {
            mostrarAlerta("No hay usuarios registrados");
        }

        String titulo = comboLibros.getValue();
        Libro libro = biblioteca.get(titulo);

        if (libro == null) return;

        if (libro.getEstado().equals("disponible")) {
            usuarioRegistrado.prestarLibro(libro);
            mostrarAlerta("Libro prestado con éxito.");
            mostrarLibrosDisponibles();
            actualizarHistorial();
        } else {
            libro.getListaDeEspera().add(usuarioRegistrado);
            mostrarAlerta("El libro ya está prestado. Has sido agregado a la cola de espera. Tu posición: " + libro.getListaDeEspera().size());
        }

    }

    @FXML
    public void handleValorarLibro() {
        if (usuarioRegistrado == null)  mostrarAlerta("No hay usuarios registrados");

        String titulo = comboLibros.getValue();
        Libro libro = biblioteca.get(titulo);

        if (libro == null) return;

        ChoiceDialog<Integer> dialog = new ChoiceDialog<>(5, 1, 2, 3, 4, 5);
        dialog.setTitle("Valoración");
        dialog.setHeaderText("Selecciona la puntuación para: " + titulo);
        dialog.setContentText("Estrellas:");

        dialog.showAndWait().ifPresent(puntaje -> {
            usuarioRegistrado.valorarLibro(libro, puntaje);
            mostrarAlerta("Valoración registrada: " + puntaje + " estrellas");
        });

    }

    @FXML
    public void handleConsultarListaEspera() {
        String titulo = comboLibros.getValue(); // Usamos el ComboBox
        Libro libro = biblioteca.get(titulo);

        if (libro != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Lista de espera para \"").append(titulo).append("\":\n");

            int pos = 1;
            int miPos = -1;

            for (Lector u : libro.getListaDeEspera()) {
                sb.append(pos).append(". ").append(u.getNombre()).append(" (ID: ").append(u.getCedula()).append(")\n");

                // Comparamos por ID
                if (usuarioRegistrado != null && u.getCedula().equals(usuarioRegistrado.getCedula())) {
                    miPos = pos;
                }
                pos++;
            }

            if (miPos != -1) {
                sb.append("\nTu posición en la cola: ").append(miPos);
            } else if (usuarioRegistrado != null) {
                sb.append("\nActualmente no estás en la cola de espera para este libro.");
            }

            areaListaEspera.setText(sb.toString());
        } else {
            mostrarAlerta("Libro no encontrado en la biblioteca.");
        }
    }


    @FXML
    public void handleAgregarAEspera() {
        if (usuario == null) registrarUsuario();

        String nombreEspera = txtNombreEspera.getText();
        String idEspera = txtIdEspera.getText();
        String titulo = txtTituloConsultaEspera.getText();

        Libro libro = biblioteca.get(titulo);
        if (libro != null) {
            Lector nuevoUsuarioEspera = new Lector(idEspera, nombreEspera);
            colasEspera.get(titulo).add(nuevoUsuarioEspera); // Añadir usuario a la cola de espera
            mostrarAlerta("Usuario agregado a la lista de espera para el libro: " + titulo);
        } else {
            mostrarAlerta("Libro no encontrado en la biblioteca.");
        }
    }


    private void registrarUsuario() {
        String nombre = txtNombre.getText();
        String id = txtId.getText();

        if (nombre == null || nombre.isBlank() || id == null || id.isBlank()) {
            mostrarAlerta("Debes ingresar nombre e ID antes de continuar.");
            return;
        }

        usuario = new Lector(id, nombre);
    }


    private void actualizarHistorial() {
        StringBuilder sb = new StringBuilder();
        usuarioRegistrado.getHistorialPrestamos().forEach(p ->
                sb.append("- ").append(p.getLibro().getTitulo()).append(" (").append(p.getFecha()).append(")\n")
        );
        areaHistorial.setText(sb.toString());
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarLibrosDisponibles() {
        StringBuilder sb = new StringBuilder();
        biblioteca.values().forEach(libro -> {
            sb.append("- ").append(libro.getTitulo()).append(" (").append(libro.getEstado()).append(")\n");
        });
        areaLibrosDisponibles.setText(sb.toString());
    }
    @FXML
    private ComboBox<String> comboLibros;

    @FXML private Button btnPrestar, btnValorar, btnConsultarCola, btnHistorial;

    @FXML
    public void handleVerHistorial() {
        if (usuarioRegistrado == null) {
            mostrarAlerta("Debes registrarte primero para ver el historial.");
            return;
        }

        System.out.println("Historial size: " + usuarioRegistrado.getHistorialPrestamos().size()); // DEBUG

        StringBuilder sb = new StringBuilder();

        if (usuarioRegistrado.getHistorialPrestamos().isEmpty()) {
            sb.append("Aún no tienes libros en el historial de préstamos.");
        } else {
            sb.append("Historial de préstamos de ").append(usuarioRegistrado.getNombre()).append(":\n\n");
            for (Prestamo prestamo : usuarioRegistrado.getHistorialPrestamos()) {
                sb.append("- ").append(prestamo.getLibro().getTitulo())
                        .append(" (").append(prestamo.getFecha()).append(")\n");
            }
        }

        areaHistorial.setText(sb.toString());
    }

    @FXML
    public void handleRegistrarUsuario() {
        String nombre = txtNombre.getText();
        String id = txtId.getText();

        if (nombre == null || nombre.isBlank() || id == null || id.isBlank()) {
            mostrarAlerta("Debes ingresar un nombre y un ID válidos.");
            return;
        }
       /* if (usuario != null) {
            mostrarAlerta("Ya hay un usuario registrado: " + usuario.getNombre());
            return;
        }*/


        usuario = new Lector(id, nombre);
        mostrarAlerta("Usuario registrado con éxito: " + usuario.getNombre());
    }
    public void inicializarValores(Lector usuario) {
    }
}
