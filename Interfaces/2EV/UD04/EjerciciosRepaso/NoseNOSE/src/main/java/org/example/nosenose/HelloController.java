package org.example.nosenose;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HelloController {
    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private RadioButton informeNormal;

    @FXML
    private RadioButton informeAgrupado;

    @FXML
    private Button mostrarInforme;

    @FXML
    Connection con;

    @FXML
    public void initialize() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/datosdi";
        String user = "root";
        String clave = "";
        con = DriverManager.getConnection(url, user, clave);
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
        listaLocalidad.forEach(ciudad -> comboBox.getItems().add(ciudad));
    }

    @FXML
    public void comprobar(){
        if ((informeNormal.isSelected() || informeAgrupado.isSelected()) && !comboBox.getSelectionModel().isEmpty()){
            String ciudad = comboBox.getValue();
            String informe = "";
            if (informeNormal.isSelected()){
                informe = "Quenose";
            }else {
                informe = "agrupado";
            }

            try {informe(informe, ciudad);} catch (SQLException e) {throw new RuntimeException(e);} catch (JRException e) {throw new RuntimeException(e);}
        } else  {
            JOptionPane.showMessageDialog(null, "RELLENE LOS CAMPOS CIUDAD Y SELECCIONE TIPO DE INFORME");
        }
    }

    public void informe(String fichero, String ciudad) throws SQLException, JRException {


        JasperDesign d = JRXmlLoader.load("Informes/"+fichero+".jrxml");

        HashMap<String,Object> param = new HashMap<>();
        param.put("RutaImagen","file:imagenes/logo.png");

        JRDesignQuery jq = new JRDesignQuery();
        jq.setText("SELECT * FROM datosdi.empleados WHERE Localidad='"+ciudad+"'");

        d.setQuery(jq);
        JasperReport jr = JasperCompileManager.compileReport(d);
        JasperPrint jp = JasperFillManager.fillReport(jr,param,con);
        JasperViewer.viewReport(jp,false);
    }


}