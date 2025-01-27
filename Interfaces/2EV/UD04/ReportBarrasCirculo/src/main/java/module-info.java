module org.example.reportbarrascirculo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.reportbarrascirculo to javafx.fxml;
    exports org.example.reportbarrascirculo;
}