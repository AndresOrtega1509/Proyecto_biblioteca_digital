package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model;

// HistorialMensajes.java
import java.util.*;

public class HistorialMensajes {
        private Map<String, LinkedList<String>> historialPorUsuario = new HashMap<>();

        public void agregarMensaje(String usuario, String mensaje) {
            historialPorUsuario.putIfAbsent(usuario, new LinkedList<>());
            historialPorUsuario.get(usuario).add(mensaje);
        }

        public List<String> obtenerHistorial(String usuario) {
            return historialPorUsuario.getOrDefault(usuario, new LinkedList<>());
        }
    }


