package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers;

import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Biblioteca;
import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Lector;
import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.ListaLector;
import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class LoginController {

    @FXML
    private TextField txtCedula;
    @FXML
    private TextField txtPassword;

    private final Biblioteca biblioteca = Biblioteca.getInstancia();

    /**
     * Metodo que permite iniciar sesion al validar el usuario
     * @param actionEvent
     */
    public void logiarse(ActionEvent actionEvent) {

        try {

            if (txtCedula.getText().equalsIgnoreCase("333")
                    && txtPassword.getText().equalsIgnoreCase("333")){
                navegarVentanaAdm("/co/edu/uniquindio/biblioteca_digital/biblioteca_digital/panelAdministrador.fxml",
                        "Biblioteca - Panel Administrador");
                return;
            }

            Lector usuario = biblioteca.iniciarSesion(
                    txtCedula.getText(),
                    txtPassword.getText());

            Sesion sesion = Sesion.getInstancia();
            sesion.setUsuario(usuario);

            crearAlerta("Inicio de sesión exitoso", Alert.AlertType.INFORMATION);
            navegarVentana("/co/edu/uniquindio/biblioteca_digital/biblioteca_digital/panelLector.fxml",
                    "Biblioteca - Panel principal", usuario, biblioteca.getListaLectores());
            cerrarVentana();

        } catch (Exception e) {
            crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Metodo para cerrar la ventana de inicio de sesion
     */
    private void cerrarVentana() {

        Stage stage = (Stage) txtCedula.getScene().getWindow();
        stage.close();
    }

    /**
     * Metodo para crear y mostrar la alerta
     * @param mensaje
     * @param tipo
     */
    private void crearAlerta(String mensaje, Alert.AlertType tipo) {

        Alert alert = new Alert(tipo);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Metodo para dirigirse a la ventana del panel principal una vez validado el usuario
     * @param nombreArchivoFxml
     * @param tituloVentana
     * @param usuario
     */
    private void navegarVentana(String nombreArchivoFxml, String tituloVentana, Lector usuario, ListaLector listaLectores) {

        try {

            // Cargar la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreArchivoFxml));
            Parent root = loader.load();


            // Obtener el controlador de la nueva ventana
            PanelLectorController controller = loader.getController();
            controller.inicializarValores(usuario);

            controller.inicializarUsuario(usuario);

            // Crear la escena
            Scene scene = new Scene(root);

            // Crear un nuevo escenario (ventana)
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(tituloVentana);

            // Mostrar la nueva ventana
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void navegarVentanaAdm(String nombreArchivoFxml, String tituloVentana) {

        try {

            // Cargar la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreArchivoFxml));
            Parent root = loader.load();


            // Crear la escena
            Scene scene = new Scene(root);

            // Crear un nuevo escenario (ventana)
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(tituloVentana);

            // Mostrar la nueva ventana
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
