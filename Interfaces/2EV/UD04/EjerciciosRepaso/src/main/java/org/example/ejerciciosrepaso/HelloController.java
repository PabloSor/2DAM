package org.example.ejerciciosrepaso;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class HelloController {

    @FXML
    private TableView<Empleado> tabla;

    @FXML
    private TableColumn<Empleado, String> nombre;

    @FXML
    private TableColumn<Empleado, String> apellidos;

    @FXML
    private TableColumn<Empleado, String> localidad;

    @FXML
    private TextField TextoLocalidad;

    @FXML
    private Button ejecutar;

    ResultSet rs;
    Statement stat;

    @FXML
    public void buscar() throws Exception {
        tabla.getItems().clear();

      sacarTabla();

    }

    @FXML
    public void sacarTabla() throws SQLException {
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        localidad.setCellValueFactory(new PropertyValueFactory<>("localidad"));
        String selectSql = "SELECT Nombre,Apellidos,Localidad from datosdi.empleados WHERE Localidad='" + TextoLocalidad.getText() + "'";
        rs = stat.executeQuery(selectSql);

        rs.beforeFirst();
        while (rs.next()) { //recorrer el ResultSet

            Empleado empleado = new Empleado(rs.getString("Nombre"), rs.getString("Apellidos"), rs.getString("Localidad"));

            tabla.getItems().add(empleado);
        }
    }

    @FXML
    public TableView crearTabla(double wrappingWidth) throws Exception {
        TableView tabla = new TableView(); //crear tabla

        String selectSql = "SELECT Nombre,Apellidos,Localidad from datosdi.empleados WHERE Localidad='" + TextoLocalidad.getText() + "'";
        rs = stat.executeQuery(selectSql);

        rs.beforeFirst();
        rs.first();
        while (rs.next()) { //recorrer el ResultSet
            nombre.setText(rs.getString("Nombre"));
            apellidos.setText(rs.getString("Apellidos"));
            localidad.setText(rs.getString("Localidad"));
        }
        return tabla;
    }

    @FXML
    public void initialize() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/datosdi";
        String user = "root";
        String clave = "";
        Connection con = DriverManager.getConnection(url, user, clave);
        stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

    }
}