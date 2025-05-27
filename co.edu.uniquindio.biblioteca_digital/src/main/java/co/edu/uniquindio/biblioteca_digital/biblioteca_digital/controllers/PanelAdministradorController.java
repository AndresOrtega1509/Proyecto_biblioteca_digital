package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers;

import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers.observador.ObservableLibros;
import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Biblioteca;
import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Lector;
import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Libro;
import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.ListaLector;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

import static co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Biblioteca.listaLectores;
import static co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Biblioteca.listaLibros;

public class PanelAdministradorController implements ObservableLibros {

    private ObservableList<Lector> lectoresObservable = FXCollections.observableArrayList();
    @FXML
    private TableView<Libro> tableLibros;

    @FXML
    private TableView<Lector> tableUsuarios;

    @FXML
    private TableColumn<Libro, String> tcAnioLibro;

    @FXML
    private TableColumn<Lector, String> tcApellidoUsuario;

    @FXML
    private TableColumn<Libro, String> tcAutorLibro;

    @FXML
    private TableColumn<Libro, String> tcCalificacionPromedioLibro;

    @FXML
    private TableColumn<Libro, String> tcCategoriaLibro;

    @FXML
    private TableColumn<Lector, String> tcCedulaUsuario;

    @FXML
    private TableColumn<Lector, String> tcCorreoUsuario;

    @FXML
    private TableColumn<Libro, String> tcEstadoLibro;

    @FXML
    private TableColumn<Lector, String> tcNombreUsuario;

    @FXML
    private TableColumn<Lector, String> tcPassWordUsuario;

    @FXML
    private TableColumn<Libro, String> tcTituloLibro;

    private Libro libroSeleccionado;

    private final Biblioteca biblioteca = Biblioteca.getInstancia();


    @FXML
    public void initialize(){
        tcCedulaUsuario.setCellValueFactory(CellData -> new SimpleStringProperty(CellData.getValue().getCedula()));
        tcNombreUsuario.setCellValueFactory(CellData -> new SimpleStringProperty(CellData.getValue().getNombre()));
        tcApellidoUsuario.setCellValueFactory(CellData -> new SimpleStringProperty(CellData.getValue().getApellido()));
        tcCorreoUsuario.setCellValueFactory(CellData -> new SimpleStringProperty(CellData.getValue().getCorreo()));
        tcPassWordUsuario.setCellValueFactory(CellData -> new SimpleStringProperty(CellData.getValue().getPassWord()));

        tcTituloLibro.setCellValueFactory(CellData -> new SimpleStringProperty(CellData.getValue().getTitulo()));
        tcAutorLibro.setCellValueFactory(CellData -> new SimpleStringProperty(CellData.getValue().getAutor()));
        tcAnioLibro.setCellValueFactory(CellData -> new SimpleStringProperty("" + CellData.getValue().getAnio()));
        tcCategoriaLibro.setCellValueFactory(CellData -> new SimpleStringProperty(CellData.getValue().getCategoria()));
        tcEstadoLibro.setCellValueFactory(CellData -> new SimpleStringProperty(CellData.getValue().getEstado()));
        tcCalificacionPromedioLibro.setCellValueFactory(CellData -> new SimpleStringProperty("" + CellData.getValue().getCalificacionPromedio()));

        mostrarUsuariosTabla();
        mostrarLibrosTabla();

        listenerSelectionLibro();

    }

    private void listenerSelectionLibro() {
        tableLibros.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            libroSeleccionado = newSelection;
        });

    }

    private void mostrarUsuariosTabla() {
        lectoresObservable.setAll(ListaLector.obtenerLectores());
        tableUsuarios.setItems(lectoresObservable);
    }

    public void mostrarLibrosTabla() {
        List<Libro> libros = listaLibros.listarLibrosInorden();
        ObservableList<Libro> librosObservable = FXCollections.observableArrayList(libros);
        tableLibros.setItems(librosObservable);
    }


    @FXML
    void GenerarEstLectoresMayorConexiones(ActionEvent event) {

    }

    @FXML
    void actualizarUsuario(ActionEvent event) {
        Lector seleccionado = tableUsuarios.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            crearAlerta("Seleccione un usuario para actualizar.", Alert.AlertType.WARNING);
            return;
        }

        // Crear campos prellenados
        TextField nombreField = new TextField(seleccionado.getNombre());
        TextField apellidoField = new TextField(seleccionado.getApellido());
        TextField correoField = new TextField(seleccionado.getCorreo());
        TextField passField = new TextField(seleccionado.getPassWord());

        // Crear layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label("Nombre:"), 0, 0);
        grid.add(nombreField, 1, 0);
        grid.add(new Label("Apellido:"), 0, 1);
        grid.add(apellidoField, 1, 1);
        grid.add(new Label("Correo:"), 0, 2);
        grid.add(correoField, 1, 2);
        grid.add(new Label("Contraseña:"), 0, 3);
        grid.add(passField, 1, 3);

        // Crear el diálogo
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Actualizar Usuario");
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Actualizar atributos
            seleccionado.setNombre(nombreField.getText().trim());
            seleccionado.setApellido(apellidoField.getText().trim());
            seleccionado.setCorreo(correoField.getText().trim());
            seleccionado.setPassWord(passField.getText().trim());

            tableUsuarios.refresh(); // Refresca visualmente la tabla
        }
    }

    @FXML
    void agregarLibro(ActionEvent event) {

        navegarVentanaAgregarLibros("/co/edu/uniquindio/biblioteca_digital/biblioteca_digital/crearLibro.fxml",
                "Administrador - AgregarLibro", this);
    }

    @FXML
    void agregarUsuario(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Agregar Usuario");
        dialog.setHeaderText("Ingrese los datos del nuevo lector");

        try {
            dialog.setContentText("Cédula:");
            Optional<String> cedula = dialog.showAndWait();
            if (!cedula.isPresent()) return;

            dialog.setContentText("Nombre:");
            Optional<String> nombre = dialog.showAndWait();
            if (!nombre.isPresent()) return;

            dialog.setContentText("Apellido:");
            Optional<String> apellido = dialog.showAndWait();
            if (!apellido.isPresent()) return;

            dialog.setContentText("Correo:");
            Optional<String> correo = dialog.showAndWait();
            if (!correo.isPresent()) return;

            dialog.setContentText("Contraseña:");
            Optional<String> pass = dialog.showAndWait();
            if (!pass.isPresent()) return;


            Lector nuevo = biblioteca.registrarLector(cedula.get(), nombre.get(), apellido.get(), correo.get(), pass.get()); //Agrega a la lista enlazada
            lectoresObservable.add(nuevo); // Agrega a la observable para la tabla
        }catch (Exception e) {
            crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    @FXML
    void eliminarLibro(ActionEvent event) {

        if (libroSeleccionado == null){
            crearAlerta("Seleccione un libro para eliminarlo", Alert.AlertType.WARNING);

        }else {
            listaLibros.eliminarPorTitulo(libroSeleccionado.getTitulo());
            mostrarLibrosTabla();
            crearAlerta("El libro ha sido eliminado exitosamente", Alert.AlertType.INFORMATION);

        }
    }

    @FXML
    void eliminarUsuario(ActionEvent event) {
        Lector seleccionado = tableUsuarios.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            boolean eliminado = listaLectores.eliminar(seleccionado.getCedula());
            if (eliminado) {
                lectoresObservable.remove(seleccionado);
            } else {
                crearAlerta("No se pudo eliminar el usuario.", Alert.AlertType.ERROR);
            }
        } else {
            crearAlerta("Seleccione un usuario para eliminar.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void generarEstCaminosCortos(ActionEvent event) {

    }

    @FXML
    void generarEstCantidadPrestamosLector(ActionEvent event) {

    }

    @FXML
    void generarEstGruposAfinidad(ActionEvent event) {

    }

    @FXML
    void generarEstLibrosValorados(ActionEvent event) {

    }

    @FXML
    void visualizarGrafoLectores(ActionEvent event) {

    }

    public void navegarVentanaAgregarLibros(String nombreArchivoFxml, String tituloVentana, ObservableLibros observableLibros) {
        try {

            // Cargar la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreArchivoFxml));
            Parent root = loader.load();

            // Obtener el controlador de la nueva ventana
            CrearLibroController controller = loader.getController();
            controller.inicializarObservable(observableLibros);


            // Crear la escena
            Scene scene = new Scene(root);

            // Crear un nuevo escenario (ventana)
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(tituloVentana);

            // Mostrar la nueva ventana
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void navegarVentanaAgregarUsuarios(String nombreArchivoFxml, String tituloVentana) {
        try {

            // Cargar la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreArchivoFxml));
            Parent root = loader.load();

            // Crear la escena
            Scene scene = new Scene(root);

            // Crear un nuevo escenario (ventana)
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(tituloVentana);

            // Mostrar la nueva ventana
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que se encarga de mostrar una alerta en pantalla
     *
     * @param mensaje mensaje a mostrar
     * @param tipo    tipo de alerta
     */
    public void crearAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
