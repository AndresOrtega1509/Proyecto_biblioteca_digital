package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers;

import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Biblioteca;
import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Lector;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

public class EstadisticaPrestamosLectorController {

    @FXML
    private BarChart<String, Number> graficoPrestamos;

    private final Biblioteca biblioteca = Biblioteca.getInstancia();

    @FXML
    public void initialize() {
        cargarDatos();
    }

    private void cargarDatos() {
        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        serie.setName("Préstamos");

        for (Lector lector : biblioteca.getListaLectores().obtenerLectores()) {
            int cantidadPrestamos = lector.getHistorialPrestamos().size();
            serie.getData().add(new XYChart.Data<>(lector.getNombre(), cantidadPrestamos));
        }

        graficoPrestamos.getData().add(serie);
    }
}

