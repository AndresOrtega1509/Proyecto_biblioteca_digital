package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers;

import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Biblioteca;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistroController {

    @FXML
    private TextField txtCedula;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtCorreo;
    @FXML
    private PasswordField txtPassword;

    private final Biblioteca biblioteca = Biblioteca.getInstancia();

    /**
     * Método que se ejecuta al presionar el botón de registrarse
     *
     * @param actionEvent evento de acción
     */
    public void registrarse(ActionEvent actionEvent) {

        try {
            // Se intenta agregar el usuario
            biblioteca.registrarLector(txtCedula.getText(), txtNombre.getText(),
                    txtApellido.getText(), txtCorreo.getText(), txtPassword.getText());

            // Se muestra un mensaje de éxito y se cierra la ventana
            crearAlerta("Usuario registrado correctamente", Alert.AlertType.INFORMATION);
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
        Stage stage = (Stage) txtPassword.getScene().getWindow();
        stage.close();
    }
}
