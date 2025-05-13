package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model;

/**
 * Clase que representa la instancia de sesion, la cual guarda al usuario
 */
public class Sesion {

    public static Sesion INSTANCIA;

    private Lector usuario;

    private Sesion() {
    }

    public Lector getUsuario() {
        return usuario;
    }

    public void setUsuario(Lector usuario) {
        this.usuario = usuario;
    }

    public static Sesion getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new Sesion();
        }
        return INSTANCIA;
    }

    /**
     * Metodo que se encarga de volver nulo al usuario al momento de cerrar sesion
     */
    public void cerrarSesion() {
        usuario = null;
    }
}
