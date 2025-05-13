module co.edu.uniquindio.biblioteca_digital.biblioteca_digital {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.biblioteca_digital.biblioteca_digital to javafx.fxml;
    exports co.edu.uniquindio.biblioteca_digital.biblioteca_digital;
    exports co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers;
    opens co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers to javafx.fxml;
}