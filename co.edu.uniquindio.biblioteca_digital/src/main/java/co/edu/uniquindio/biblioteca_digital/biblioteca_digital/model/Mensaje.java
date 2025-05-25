package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model;

// Clase Mensaje.java
public class Mensaje {
    private final String emisor;
    private final String receptor;
    private final String contenido;

    public Mensaje(String emisor, String receptor, String contenido) {
        this.emisor = emisor;
        this.receptor = receptor;
        this.contenido = contenido;
    }

    public String getEmisor() { return emisor; }
    public String getReceptor() { return receptor; }
    public String getContenido() { return contenido; }

    @Override
    public String toString() {
        return emisor + ": " + contenido;
    }
}

