package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model;

public class Biblioteca {

    private ListaEnlazadaSimple listaLectores;

    public Biblioteca(){
        listaLectores = new ListaEnlazadaSimple();
    }

    public Lector registrarLector(String cedula, String nombre, String apellido, String correo, String passWord){

        if (cedula.isEmpty()){
            throw new RuntimeException("La cedula es obligatoria");
        }
        if (nombre.isEmpty()){
            throw new RuntimeException("El nombre es obligatorio");
        }
        if (apellido.isEmpty()){
            throw new RuntimeException("El apellido es obligatorio");
        }
        if (correo.isEmpty()){
            throw new RuntimeException("El correo es obligatorio");
        }
        if(passWord.isEmpty()){
            throw new RuntimeException("La contrasena es obligatorio");
        }

        if (listaLectores.buscarLector(cedula) == null){
            throw new RuntimeException("Ya existe un usuario con la cedula: "+ cedula);
        }

        Lector lector = new Lector(cedula,nombre,apellido,correo,passWord);
        listaLectores.agregar(lector);

        return lector;

    }

    public boolean iniciarSesion(String cedula, String passWord, Lector lector){

        return lector.getCedula().equalsIgnoreCase(cedula) && lector.getPassWord().equalsIgnoreCase(passWord);

    }


}
