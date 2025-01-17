package org.example.nosenose;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private ComboBox<Empleado> comboBox;

    @FXML
    private RadioButton informeNormal;

    @FXML
    private RadioButton informeAgrupado;

    @FXML
    private Button mostrarInforme;

    @FXML
    public void initialize() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/datosdi";
        String user = "root";
        String clave = "";
        Connection con = DriverManager.getConnection(url, user, clave);
        Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sql = "select * from datosdi.empleados";
        ResultSet rs = stat.executeQuery(sql);

        String sqlLocalidades = "select localidad from datosdi.empleados";
        ResultSet rsLocalidad = stat.executeQuery(sqlLocalidades);
        ArrayList<String> listaLocalidad = new ArrayList<>();

        while (rsLocalidad.next()) {
            if (!listaLocalidad.contains(rsLocalidad.getString("localidad"))) {
                listaLocalidad.add(rsLocalidad.getString("Localidad"));
            }
        }
        listaLocalidad.forEach(ciudad -> comboBox.getItems().add());

    }
}