package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers;

import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Biblioteca;
import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Lector;
import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.RedLectores;
import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.ListaLector;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class EstadisticaCaminoCortoController {

    @FXML private ComboBox<Lector> comboOrigen;
    @FXML private ComboBox<Lector> comboDestino;
    @FXML private ListView<String> listaCamino;

    private RedLectores red;

    @FXML
    public void initialize() {
        ListaLector listaLectores = Biblioteca.getInstancia().getListaLectores();
        red = new RedLectores(listaLectores);

        List<Lector> lectores = listaLectores.obtenerLectores();
        comboOrigen.setItems(FXCollections.observableArrayList(lectores));
        comboDestino.setItems(FXCollections.observableArrayList(lectores));
    }

    @FXML
    public void handleBuscarCamino() {
        Lector origen = comboOrigen.getValue();
        Lector destino = comboDestino.getValue();

        listaCamino.getItems().clear();

        if (origen == null || destino == null) {
            listaCamino.getItems().add("Debe seleccionar ambos lectores.");
            return;
        }

        if (origen.equals(destino)) {
            listaCamino.getItems().add("Los lectores seleccionados son el mismo.");
            return;
        }

        List<Lector> camino = red.caminoMasCorto(origen, destino);

        if (camino.isEmpty()) {
            listaCamino.getItems().add("No hay conexión entre los lectores.");
        } else {
            for (Lector lector : camino) {
                listaCamino.getItems().add(lector.getNombre() + " (" + lector.getCedula() + ")");
            }
        }
    }
}

