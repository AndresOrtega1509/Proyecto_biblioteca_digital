package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model;



public class Biblioteca {

    public static ListaLector listaLectores;
    public static ArbolLibros listaLibros;
    public static Biblioteca INSTANCIA;
    private final Sesion sesion = Sesion.getInstancia();

    public Biblioteca(){
        listaLectores = new ListaLector();
        listaLibros = new ArbolLibros();
        cargarDatosPrueba();
    }

    private void cargarDatosPrueba() {

        Lector lector = new Lector("123", "Miguel", "Sanchez", "miguel@gmail.com", "111");
        listaLectores.agregar(lector);

        Libro libro = new Libro("Principito", "Antoine", "1947", "Aventura", false, 0);
        listaLibros.insertar(libro);
    }

    public Lector registrarLector(String cedula, String nombre, String apellido, String correo, String passWord){

        if (cedula.isEmpty()){
            throw new RuntimeException("La cedula es obligatoria");
        }
        if (listaLectores.buscarLector(cedula) != null){
            throw new RuntimeException("Ya existe un usuario con la cedula: "+ cedula);
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
            throw new RuntimeException("La contraseña es obligatorio");
        }
        if (passWord.length() < 3) {
            throw new RuntimeException("La contraseña debe tener mínimo 3 caracteres");
        }

        Lector lector = new Lector(cedula,nombre,apellido,correo,passWord);
        listaLectores.agregar(lector);

        return lector;

    }

    public Lector iniciarSesion(String cedula, String passWord){

        Lector lector = listaLectores.buscarLector(cedula);

        if (lector != null){

            if (lector.getPassWord().equalsIgnoreCase(passWord)){
                return lector;
            }
        }
        throw new RuntimeException("Los datos de acceso son incorrectos");

    }

    public Libro agregarLibro(String titulo, String autor, String anio, String categoria){

        if (titulo.isEmpty()){
            throw new RuntimeException("El titulo es obligatorio");
        }
        if (autor.isEmpty()){
            throw new RuntimeException("El autor es obligatorio");
        }
        if (anio.isEmpty()){
            throw new RuntimeException("El año es obligatorio");
        }
        if (categoria.isEmpty()){
            throw new RuntimeException("La categoria es obligatoria");
        }

        Libro libro = new Libro(titulo, autor, anio,categoria, false, 0);
        listaLibros.insertar(libro);

        return libro;

    }

    /**
     * Metodo que se encarga de obtener la instancia de la Biblioteca
     * @return
     */
    public static Biblioteca getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new Biblioteca();
        }
        return INSTANCIA;
    }

}
