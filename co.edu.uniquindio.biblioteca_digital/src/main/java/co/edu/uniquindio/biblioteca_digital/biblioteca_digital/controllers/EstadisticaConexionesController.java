package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers;

import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Biblioteca;
import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Lector;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

public class EstadisticaConexionesController {

    @FXML
    private BarChart<String, Number> barChart;

    private final Biblioteca biblioteca = Biblioteca.getInstancia();

    @FXML
    public void initialize() {
        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        dataSeries.setName("Conexiones");

        for (Lector lector : biblioteca.getListaLectores().obtenerLectores()) {
            int cantidadConexiones = lector.getConexiones().size();
            dataSeries.getData().add(new XYChart.Data<>(lector.getNombre(), cantidadConexiones));
        }

        barChart.getData().add(dataSeries);
    }
}

