package com.iesch.di.ejerciiorepaso3informebbdd;

import javafx.fxml.FXML;

import net.sf.jasperreports.engine.*;

import net.sf.jasperreports.view.JasperViewer;

import java.sql.*;

public class HelloController {

    Connection con;
    ResultSet rs;

    @FXML
    public void initialize(){
        String url = "jdbc:mysql://localhost:3306/datosdi";
        String user = "root";
        String clave = "";
        try {
            con = DriverManager.getConnection(url, user, clave);
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "select * from datosdi.empleados";
            rs = stat.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void mostrarInforme() throws JRException, SQLException {
        String fileRepo = "Informes/Blank_A4.jasper";
        JasperPrint jpRepo = JasperFillManager.fillReport(fileRepo, null, con);
        JasperViewer viewer = new JasperViewer(jpRepo, false);
        viewer.setTitle("TITULO INFORME");
        viewer.setVisible(true);
    }
}