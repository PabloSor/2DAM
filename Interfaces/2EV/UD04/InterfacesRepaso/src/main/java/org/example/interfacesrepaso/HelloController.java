package org.example.interfacesrepaso;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HelloController {
    @FXML
    private Button Jasper1;

    @FXML
    private Button Jasper2;

    @FXML
    private TextField minimo;

    @FXML
    private TextField maximo;

    @FXML
    private ComboBox<String> comboBoxLocalidad;

    @FXML
    Connection con = null;

    @FXML
    Statement stat;

    @FXML
    public void initialize() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/ventas";
        String user = "root";
        String clave = "";
        con = DriverManager.getConnection(url, user, clave);
        stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        String sql = "select * from ventas.facturas";
        ResultSet rs = stat.executeQuery(sql);

        cargarLocalidades();
    }


    public void cargarLocalidades() throws SQLException {
        String query = "select DISTINCT(Localidad) from ventas.facturas";

        ResultSet rs = stat.executeQuery(query);
        while (rs.next()){
            comboBoxLocalidad.getItems().add(rs.getString(1));
        }

    }


    public void informe1() throws JRException {
        String fileRepo = "Reports/ExamenEj1.jasper";

        Map<String, Object> parametros = new HashMap<>();

        String localidad = comboBoxLocalidad.getValue();

        parametros.put("SelectLocalidad", localidad);
        parametros.put("imagen", "img/logo.png");

        JasperPrint jpRepo = JasperFillManager.fillReport(fileRepo, parametros, con);
        JasperViewer viewer = new JasperViewer(jpRepo,false);
        viewer.setTitle("Ejercicio 1 Pablo Sorinao");
        viewer.setVisible(true);
    }

    @FXML
    public void informe2() throws JRException {
        String fileRepo = "Reports/ExamenEj2.jasper";

        Map<String, Object> parametros2 = new HashMap<>();

        Float menor = Float.valueOf(minimo.getText());
        Float mayor = Float.valueOf(maximo.getText());

        parametros2.put("minimo", menor);

        parametros2.put("maximo", mayor);
        parametros2.put("imagen", "img/logo.png");

        JasperPrint jpRepo = JasperFillManager.fillReport(fileRepo, parametros2, con);
        JasperViewer viewer = new JasperViewer(jpRepo,false);
        viewer.setTitle("Ejercicio 2 Pablo Soriano");
        viewer.setVisible(true);
    }

}