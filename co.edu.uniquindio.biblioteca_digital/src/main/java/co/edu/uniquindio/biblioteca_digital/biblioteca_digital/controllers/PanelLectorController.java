package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers;

import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import static co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Biblioteca.getInstancia;
import static co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Biblioteca.listaLectores;
import static co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.ListaLector.obtenerLectores;

public class PanelLectorController {

    @FXML
    private TextField txtNombre, txtId, txtTituloPrestamo, txtTituloValoracion, txtEstrellas;
    @FXML private TextArea areaHistorial, areaLibrosDisponibles, areaListaEspera;
    @FXML private TextField txtTituloConsultaEspera, txtNombreEspera, txtIdEspera;
    @FXML
    private Label labelUsuario;

    String receptorSelect;
    private Lector usuario;
    private Lector usuarioRegistrado;
    private HashMap<String, Libro> biblioteca = new HashMap<>();

    // Aquí se utiliza una PriorityQueue para simular la cola de espera
    private HashMap<String, PriorityQueue<Lector>> colasEspera = new HashMap<>();

    public void inicializarUsuario(Lector usuario) {
        usuarioRegistrado = usuario;
        labelUsuario.setText("Bienvenido, " + usuario.getNombre());
        comboUsuarios.getItems().addAll(
                obtenerLectores()
                        .stream()
                        .map(Lector::getNombre)
                        .toList()
        );
        iniciarCliente();
        mostrarLibrosDisponibles();

    }

    private DataOutputStream flujoSalida;

    public void iniciarCliente() {
        try {
            Socket socketComunicacion = new Socket("localhost", 8081);
            DataInputStream flujoEntrada = new DataInputStream(socketComunicacion.getInputStream());
            flujoSalida = new DataOutputStream(socketComunicacion.getOutputStream());

            // Hilo para recibir mensajes
            HiloClienteEntrada hiloClienteEntrada = new HiloClienteEntrada(flujoEntrada,areaMensajes);
            hiloClienteEntrada.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void enviarMensaje2() {
        String mensaje = txtMensaje.getText();
        if (!mensaje.isEmpty()) {
            try {
                String clienteId = usuarioRegistrado.getNombre();
                String mensajeCompleto = clienteId + ": " + mensaje;
                flujoSalida.writeUTF(mensajeCompleto);

                // Mostrar el mensaje en el propio cliente
                areaMensajes.appendText(mensajeCompleto + "\n");

               // campoMensaje.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

        //mostrarLibrosDisponibles();



    }

    @FXML
    public void handlePrestarLibro() {
        if (usuarioRegistrado == null) {
            mostrarAlerta("No hay usuarios registrados");
        }

        String titulo = comboLibros.getValue();
        Libro libro = biblioteca.get(titulo);


        if (libro == null) return;

        String LibroPrestado = listaLectores.recorrerLectores(libro);

if(LibroPrestado==null) {
    if (libro.getEstado().equals("disponible")) {
        usuarioRegistrado.prestarLibro(libro);
        mostrarAlerta("Libro prestado con éxito.");
        mostrarLibrosDisponiblesDspuesDePrestar();
        actualizarHistorial();

    } else {
        libro.getListaDeEspera().add(usuarioRegistrado);
        mostrarAlerta("El libro ya está prestado. Has sido agregado a la cola de espera. Tu posición: " + libro.getListaDeEspera().size());
    }
}else {
    libro.getListaDeEspera().add(usuarioRegistrado);
    mostrarAlerta("El libro ya está prestado al lector: "+ LibroPrestado  +" Has sido agregado a la cola de espera. Tu posición: " + libro.getListaDeEspera().size());

}
        mostrarLibrosDisponibles();

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
        obtenerLectores().iterator();
        Lector librosDisponiblesLector;
        Libro estadoLibroActual;
        if (listaLectores.getTamanio()>1){
            mostrarLibrosDisponiblesDspuesDePrestar();
        }else {
            biblioteca.values().forEach(libro -> {
                sb.append("- ").append(libro.getTitulo()).append(" (").append(libro.getEstado()).append(")\n");
            });
            areaLibrosDisponibles.setText(sb.toString());
        }
    }

    private void mostrarLibrosDisponiblesDspuesDePrestar() {
        StringBuilder sb = new StringBuilder();
        areaLibrosDisponibles.setText("");

        obtenerLectores().iterator();
        Lector librosDisponiblesLector;
        Libro estadoLibroActual;
        Boolean hayPrestados= false;

        for (int i = 0 ; i <obtenerLectores().size(); i++){
            librosDisponiblesLector = obtenerLectores().get(i);
            if(librosDisponiblesLector.getHistorialPrestamos().size()!= 0) {
                for (int j=0; j< librosDisponiblesLector.getHistorialPrestamos().size();j++) {
                    estadoLibroActual = librosDisponiblesLector.getHistorialPrestamos().get(j).getLibro();
                    if (estadoLibroActual.getEstado().trim().equalsIgnoreCase("prestado")) {

                        Libro finalEstadoLibroActual = estadoLibroActual;
                        int finalJ = j;
                        biblioteca.values().forEach(libro -> {
                            if(libro.getTitulo().equals(finalEstadoLibroActual.getTitulo())){
                                sb.append("- ").append(finalEstadoLibroActual.getTitulo()).append(" (").append(finalEstadoLibroActual.getEstado()).append(")\n");

                            }
                        });
                        hayPrestados = true;
                    }
                }
            }else {

                if (!hayPrestados && obtenerLectores().size()==i+1){

                    biblioteca.values().forEach(libro -> {
                        sb.append("- ").append(libro.getTitulo()).append(" (").append(libro.getEstado()).append(")\n");
                    });

                    areaLibrosDisponibles.setText(sb.toString());
                }

            }
        }



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
        this.usuarioRegistrado = usuario;
    }


    @FXML private ComboBox<String> comboUsuarios;
    @FXML private TextArea areaMensajes;
    @FXML private TextField txtMensaje;

    private String usuarioActual = obtenerLectores().get(0).getNombre(); // Simulación del usuario conectado
    private final HistorialMensajes historial = new HistorialMensajes();


    private void mostrarMensajes(String receptor) {
        List<String> mensajes = historial.obtenerHistorial(receptor);
        areaMensajes.clear();
        for (String m : mensajes) {
            areaMensajes.appendText(m.toString() + "\n");
        }
    }

    @FXML
    public void handleEnviarMensaje() {
        receptorSelect = comboUsuarios.getValue(); // Usuario al que se envía el mensaje
        String texto = txtMensaje.getText(); // Contenido del mensaje

        if (receptorSelect == null || texto == null || texto.trim().isEmpty()) {
            mostrarAlerta("Selecciona un usuario receptor y escribe un mensaje.");
            return;
        }

        // Crear mensaje y agregar al historial
        Mensaje mensaje = new Mensaje(usuarioRegistrado.getNombre(), receptorSelect, texto.trim());
        historial.agregarMensaje(receptorSelect,mensaje.toString());

        //cliente.enviarMensaje(receptorSelect, texto.trim());

        enviarMensaje2();


        // Mostrar el historial actualizado en pantalla
        mostrarMensajes(receptorSelect);
        chatArea.appendText("Yo a " + receptorSelect + ": " + mensaje + "\n");

        // Limpiar campo de entrada
        txtMensaje.clear();
    }



    @FXML private TextArea chatArea;
    @FXML private TextField mensajeField, destinatarioField;
    @FXML private Button enviarBtn;
    private HistorialMensajes gestorHistorial = new HistorialMensajes();

    public void recibirMensaje(String mensaje) {
        Platform.runLater(() -> {
            chatArea.appendText(mensaje + "\n");
            // opcional: extraer nombre y guardar en historial
        });
    }

    private void actualizarUsuariosConectados(List<String> usuarios) {
        Platform.runLater(() -> {
            usuarios.add(usuarioRegistrado.getNombre());
            //comboUsuarios.getItems().setAll(obtenerLectores());

            comboUsuarios.getItems().setAll(
                    obtenerLectores()
                            .stream()
                            .map(Lector::getNombre)
                            .toList()
            );
            //comboUsuarios.getItems().remove(nombreUsuario); // No incluirse a sí mismo
        });
    }
}
