module org.example.tablajasper {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.tablajasper to javafx.fxml;
    exports org.example.tablajasper;
}