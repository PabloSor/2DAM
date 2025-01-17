module org.example.nosenose {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.nosenose to javafx.fxml;
    exports org.example.nosenose;
}