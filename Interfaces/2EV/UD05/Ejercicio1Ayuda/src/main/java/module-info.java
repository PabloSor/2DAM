module org.example.ejercicio1ayuda {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejercicio1ayuda to javafx.fxml;
    exports org.example.ejercicio1ayuda;
}