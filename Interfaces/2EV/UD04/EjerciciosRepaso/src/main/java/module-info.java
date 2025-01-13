module org.example.ejerciciosrepaso {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.ejerciciosrepaso to javafx.fxml;
    exports org.example.ejerciciosrepaso;
}