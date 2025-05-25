package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class InicioController {


    /**
     * Método que permite ir a la vista de Iniciar Sesión
     *
     * @param actionEvent Evento que representa el clic del botón
     */
    public void irIniciarSesion(ActionEvent actionEvent) {
        navegarVentana("/co/edu/uniquindio/biblioteca_digital/biblioteca_digital/loginUsuario.fxml",
                "Biblioteca - Iniciar Sesión");
    }

    /**
     * Método que permite ir a la vista de Registro de Cliente
     *
     * @param actionEvent Evento que representa el clic del botón
     */
    public void irRegistroUsuario(ActionEvent actionEvent) {
        navegarVentana("/co/edu/uniquindio/biblioteca_digital/biblioteca_digital/registroUsuario.fxml",
                "Biblioteca - Registro del usuario");
    }

    /**
     * Método que permite ir a la venana indicada por el nombre del archivo FXML
     *
     * @param nombreArchivoFxml Nombre del archivo FXML
     * @param tituloVentana     Título de la ventana
     */
    public void navegarVentana(String nombreArchivoFxml, String tituloVentana) {
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