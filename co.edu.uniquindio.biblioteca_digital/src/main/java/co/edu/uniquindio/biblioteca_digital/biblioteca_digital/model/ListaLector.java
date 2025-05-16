package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model;

public class ListaLector {

    private NodoLector nodoPrimero;
    private int tamanio;

    public ListaLector(){
        nodoPrimero = null;
        tamanio = 0;
    }

    public int getTamanio(){
        return tamanio;
    }

    public void agregar(Lector lector){
        NodoLector nuevo = new NodoLector(lector);
        if (tamanio == 0){
            nodoPrimero = nuevo;
        }else {
            NodoLector actual = nodoPrimero;

            while (actual.getNodoSiguiente() != null){
                actual = actual.getNodoSiguiente();
            }

            actual.setNodoSiguiente(nuevo);
        }
        tamanio++;
    }

    public Lector buscarLector(String cedula){
        NodoLector actual = nodoPrimero;
        while (actual != null){
            if (actual.getLector().getCedula().equalsIgnoreCase(cedula)){
                return actual.getLector();
            }
            actual = actual.getNodoSiguiente();
        }
        return null;
    }
}
