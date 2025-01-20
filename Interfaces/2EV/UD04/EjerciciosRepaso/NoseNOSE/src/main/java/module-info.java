module org.example.nosenose {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires net.sf.jasperreports.core;
    requires java.desktop;


    opens org.example.nosenose to javafx.fxml;
    exports org.example.nosenose;
}