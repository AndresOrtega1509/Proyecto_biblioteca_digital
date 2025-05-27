package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers;

import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers.observador.ObservableLibros;
import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Biblioteca;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CrearLibroController {

    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtAutor;
    @FXML
    private TextField txtAnio;
    @FXML
    private TextField txtCategoria;
    private final Biblioteca biblioteca = Biblioteca.getInstancia();
    private ObservableLibros observableLibros;


    public void agregarLibro(ActionEvent actionEvent) {

        try {
            // Se intenta agregar el usuario
            biblioteca.agregarLibro(txtTitulo.getText(), txtAutor.getText(),
                    txtAnio.getText(), txtCategoria.getText());

            // Se muestra un mensaje de éxito y se cierra la ventana
            crearAlerta("Libro agregado correctamente", Alert.AlertType.INFORMATION);
            observableLibros.mostrarLibrosTabla();
            cerrarVentana();

        } catch (Exception e) {
            crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Método que se encarga de mostrar una alerta en pantalla
     *
     * @param mensaje mensaje a mostrar
     * @param tipo    tipo de alerta
     */
    public void crearAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Método que se encarga de cerrar la ventana actual
     */
    public void cerrarVentana() {
        Stage stage = (Stage) txtAutor.getScene().getWindow();
        stage.close();
    }

    public void inicializarObservable(ObservableLibros observableLibros) {
        this.observableLibros = observableLibros;
    }
}
