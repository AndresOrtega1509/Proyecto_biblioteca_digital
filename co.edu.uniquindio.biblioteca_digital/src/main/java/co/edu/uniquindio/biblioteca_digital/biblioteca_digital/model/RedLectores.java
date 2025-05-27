package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model;

import java.util.*;
import static co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.ListaLector.*;

public class RedLectores {

    private ListaLector listaLectores;

    public RedLectores(ListaLector listaLectores) {
        this.listaLectores = listaLectores;
        crearConexionesSimilares();
    }

    private void crearConexionesSimilares() {
        NodoLector actual = listaLectores.getNodoPrimero();
        while (actual != null) {
            NodoLector otro = actual.getNodoSiguiente();
            while (otro != null) {
                if (sonSimilares(actual.getLector(), otro.getLector())) {
                    actual.getLector().agregarConexion(otro.getLector());
                    otro.getLector().agregarConexion(actual.getLector());
                }
                otro = otro.getNodoSiguiente();
            }
            actual = actual.getNodoSiguiente();
        }
    }

    private boolean sonSimilares(Lector a, Lector b) {
        int comunes = 0;
        for (Valoracion va : a.getValoraciones()) {
            for (Valoracion vb : b.getValoraciones()) {
                if (va.getLibro().getTitulo().equalsIgnoreCase(vb.getLibro().getTitulo()) &&
                        Math.abs(va.getEstrellas() - vb.getEstrellas()) <= 1) {
                    comunes++;
                    if (comunes >= 3) return true;
                }
            }
        }
        return false;
    }

    public ListaLector sugerirAmigos(Lector lector) {
        ListaLector sugerencias = new ListaLector();
        for (Lector amigo : lector.getConexiones()) {
            for (Lector conocido : amigo.getConexiones()) {
                if (!lector.equals(conocido) && !lector.getConexiones().contains(conocido)) {
                    sugerencias.agregar(conocido);
                }
            }
        }
        return sugerencias;
    }

    public List<Lector> caminoMasCorto(Lector origen, Lector destino) {
        Map<Lector, Lector> padre = new HashMap<>();
        Queue<Lector> cola = new LinkedList<>();

        cola.add(origen);
        padre.put(origen, null);

        while (!cola.isEmpty()) {
            Lector actual = cola.poll();
            if (actual.equals(destino)) {
                List<Lector> camino = new ArrayList<>();
                while (actual != null) {
                    camino.add(0, actual);
                    actual = padre.get(actual);
                }
                return camino;
            }
            for (Lector vecino : actual.getConexiones()) {
                if (!padre.containsKey(vecino)) {
                    padre.put(vecino, actual);
                    cola.add(vecino);
                }
            }
        }
        return new ArrayList<>();
    }

    private void limpiarConexiones() {
        NodoLector actual = listaLectores.getNodoPrimero();
        while (actual != null) {
            actual.getLector().getConexiones().clear();
            actual = actual.getNodoSiguiente();
        }
    }

    public void actualizarConexiones() {
        crearConexionesSimilares();
    }
}
