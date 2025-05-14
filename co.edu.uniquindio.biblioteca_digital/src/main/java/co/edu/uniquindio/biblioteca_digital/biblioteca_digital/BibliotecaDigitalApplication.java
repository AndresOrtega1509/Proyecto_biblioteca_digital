package co.edu.uniquindio.biblioteca_digital.biblioteca_digital;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BibliotecaDigitalApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BibliotecaDigitalApplication.class.getResource("inicioBiblioteca.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Biblioteca digital");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}