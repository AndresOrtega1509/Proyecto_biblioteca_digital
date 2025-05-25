module co.edu.uniquindio.biblioteca_digital.biblioteca_digital {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;



    opens co.edu.uniquindio.biblioteca_digital.biblioteca_digital to javafx.fxml;
    exports co.edu.uniquindio.biblioteca_digital.biblioteca_digital;

    exports co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers;
    exports co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model;
    opens co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers;

}