module org.example.distribuciondeapps {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.distribuciondeapps to javafx.fxml;
    exports org.example.distribuciondeapps;
}