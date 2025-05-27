package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers;

import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Biblioteca;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

public class EstadisticaLibrosValoradosController {

    @FXML
    private BarChart<String, Number> graficaTopLibros;

    private Biblioteca biblioteca = Biblioteca.getInstancia();

    @FXML
    public void initialize() {
        XYChart.Series<String, Number> serie =
                biblioteca.listaLibros.generarSerieTop3();

        graficaTopLibros.getData().clear();
        graficaTopLibros.getData().add(serie);
    }

}
