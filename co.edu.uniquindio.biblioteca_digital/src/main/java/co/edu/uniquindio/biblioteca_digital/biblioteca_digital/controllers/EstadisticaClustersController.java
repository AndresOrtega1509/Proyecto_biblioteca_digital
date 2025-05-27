package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers;

import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Biblioteca;
import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Lector;
import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.ListaLector;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.*;

public class EstadisticaClustersController {

    @FXML
    private ListView<String> listaClusters;

    private final ListaLector listaLectores = Biblioteca.getInstancia().getListaLectores();

    @FXML
    public void handleDetectarClusters() {
        Set<Lector> visitados = new HashSet<>();
        List<Set<Lector>> clusters = new ArrayList<>();

        for (Lector lector : listaLectores.obtenerLectores()) {
            if (!visitados.contains(lector)) {
                Set<Lector> cluster = new HashSet<>();
                dfs(lector, visitados, cluster);
                clusters.add(cluster);
            }
        }

        mostrarClusters(clusters);
    }

    private void dfs(Lector actual, Set<Lector> visitados, Set<Lector> cluster) {
        visitados.add(actual);
        cluster.add(actual);

        for (Lector conectado : actual.getConexiones()) {
            if (!visitados.contains(conectado)) {
                dfs(conectado, visitados, cluster);
            }
        }
    }

    private void mostrarClusters(List<Set<Lector>> clusters) {
        listaClusters.getItems().clear();

        int i = 1;
        for (Set<Lector> cluster : clusters) {
            StringBuilder sb = new StringBuilder("Grupo " + i + ": ");
            for (Lector lector : cluster) {
                sb.append(lector.getNombre()).append(", ");
            }
            listaClusters.getItems().add(sb.substring(0, sb.length() - 2)); // Quitar la última coma
            i++;
        }
    }
}

