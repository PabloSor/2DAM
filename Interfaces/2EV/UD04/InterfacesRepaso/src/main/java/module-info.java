module org.example.interfacesrepaso {
    requires javafx.controls;
    requires javafx.fxml;
    requires net.sf.jasperreports.core;
    requires java.sql;


    opens org.example.interfacesrepaso to javafx.fxml;
    exports org.example.interfacesrepaso;
}