package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers;

import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Biblioteca;
import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Lector;
import co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Libro;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import static co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model.Biblioteca.listaLectores;

public class PanelAdministradorController {

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


    @FXML
    public void initializable(){
        tcCedulaUsuario.setCellValueFactory(CellData -> new SimpleStringProperty(CellData.getValue().getCedula()));
        tcNombreUsuario.setCellValueFactory(CellData -> new SimpleStringProperty(CellData.getValue().getNombre()));
        tcApellidoUsuario.setCellValueFactory(CellData -> new SimpleStringProperty(CellData.getValue().getApellido()));
        tcCorreoUsuario.setCellValueFactory(CellData -> new SimpleStringProperty(CellData.getValue().getCorreo()));
        tcPassWordUsuario.setCellValueFactory(CellData -> new SimpleStringProperty(CellData.getValue().getPassWord()));

        tcTituloLibro.setCellValueFactory(CellData -> new SimpleStringProperty(CellData.getValue().getTitulo()));
       // tcAutorLibro.setCellValueFactory(CellData -> new SimpleStringProperty(CellData.getValue().ge()));
       // tcAnioLibro.setCellValueFactory(CellData -> new SimpleStringProperty(CellData.getValue().ge()));
       // tcCategoriaLibro.setCellValueFactory(CellData -> new SimpleStringProperty(CellData.getValue().getCorreo()));
        tcEstadoLibro.setCellValueFactory(CellData -> new SimpleStringProperty(CellData.getValue().getEstado()));
        tcCalificacionPromedioLibro.setCellValueFactory(CellData -> new SimpleStringProperty("" + CellData.getValue().getCalificacionPromedio()));
    }


    @FXML
    void GenerarEstLectoresMayorConexiones(ActionEvent event) {

    }

    @FXML
    void actualizarUsuario(ActionEvent event) {

    }

    @FXML
    void agregarLibro(ActionEvent event) {

    }

    @FXML
    void agregarUsuario(ActionEvent event) {

    }

    @FXML
    void eliminarLibro(ActionEvent event) {

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
}
