package co.edu.uniquindio.biblioteca_digital.biblioteca_digital;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UsuarioVista.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Biblioteca Digital - Usuario");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
