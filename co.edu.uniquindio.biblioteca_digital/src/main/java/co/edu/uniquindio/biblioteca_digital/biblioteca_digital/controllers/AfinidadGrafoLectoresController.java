package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers;

import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
//import static co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Biblioteca.listaLectores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AfinidadGrafoLectoresController {

    @FXML
    private Pane grafoPane;

    private final Biblioteca biblioteca = Biblioteca.getInstancia();
    private RedLectores redLectores;

    @FXML
    public void initialize() {
        redLectores = new RedLectores(biblioteca.getListaLectores());
        dibujarGrafo();
    }

    @FXML
    public void handleActualizarGrafo() {
        redLectores.actualizarConexiones();
        grafoPane.getChildren().clear();
        dibujarGrafo();
    }

    private void dibujarGrafo() {
        grafoPane.getChildren().clear(); // Limpiar vista anterior

        List<Lector> lectores = Biblioteca.getInstancia().getListaLectores().obtenerLectores();
        Map<Lector, Circle> nodosGraficos = new HashMap<>();

        double centerX = 300, centerY = 200, radius = 150;
        int total = lectores.size();
        int index = 0;

        for (Lector lector : lectores) {
            // Coordenadas en círculo
            double angle = 2 * Math.PI * index / total;
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);

            Circle nodo = new Circle(x, y, 20);
            nodo.setStyle("-fx-fill: lightblue; -fx-stroke: black;");
            Text texto = new Text(x - 10, y + 5, lector.getNombre());

            grafoPane.getChildren().addAll(nodo, texto);
            nodosGraficos.put(lector, nodo);
            index++;
        }

        // Dibujar conexiones
        for (Lector lector : lectores) {
            Circle origen = nodosGraficos.get(lector);
            for (Lector amigo : lector.getConexiones()) {
                if (nodosGraficos.containsKey(amigo)) {
                    Circle destino = nodosGraficos.get(amigo);
                    Line linea = new Line(
                            origen.getCenterX(), origen.getCenterY(),
                            destino.getCenterX(), destino.getCenterY()
                    );
                    linea.setStyle("-fx-stroke: gray;");
                    grafoPane.getChildren().add(linea);
                }
            }
        }
    }
}

