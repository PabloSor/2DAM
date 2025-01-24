module com.iesch.di.ejerciiorepaso3informebbdd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires net.sf.jasperreports.core;


    opens com.iesch.di.ejerciiorepaso3informebbdd to javafx.fxml;
    exports com.iesch.di.ejerciiorepaso3informebbdd;
}