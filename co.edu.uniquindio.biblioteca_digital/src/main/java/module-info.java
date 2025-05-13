module co.edu.uniquindio.biblioteca_digital.biblioteca_digital {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;



    opens co.edu.uniquindio.biblioteca_digital.biblioteca_digital to javafx.fxml;
    exports co.edu.uniquindio.biblioteca_digital.biblioteca_digital;


    opens co.edu.uniquindio.biblioteca_digital.biblioteca_digital.viewController;
    exports co.edu.uniquindio.biblioteca_digital.biblioteca_digital.viewController;

    opens co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controller;
    exports co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controller;

    exports co.edu.uniquindio.biblioteca_digital.biblioteca_digital.model;
}