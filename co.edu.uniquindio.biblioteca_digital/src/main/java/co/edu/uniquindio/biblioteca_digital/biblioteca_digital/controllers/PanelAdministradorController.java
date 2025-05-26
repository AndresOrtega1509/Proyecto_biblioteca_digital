package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers;

import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers.observador.ObservableLibros;
import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Biblioteca;
import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Lector;
import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Libro;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.List;

import static co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Biblioteca.listaLibros;

public class PanelAdministradorController implements ObservableLibros {

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

    }

    @FXML
    void agregarLibro(ActionEvent event) {

        navegarVentanaAgregarLibros("/co/edu/uniquindio/biblioteca_digital/biblioteca_digital/crearLibro.fxml",
                "Administrador - AgregarLibro", this);
    }

    @FXML
    void agregarUsuario(ActionEvent event) {
        navegarVentanaAgregarUsuarios("/co/edu/uniquindio/biblioteca_digital/biblioteca_digital/registroUsuario.fxml",
                "Biblioteca - Registro del usuario");
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
