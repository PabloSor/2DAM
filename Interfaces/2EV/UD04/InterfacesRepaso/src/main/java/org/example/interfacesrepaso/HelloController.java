package org.example.interfacesrepaso;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.*;

public class HelloController {
    @FXML
    private Button Jasper1;

    @FXML
    private Button Jasper2;

    @FXML
    private Button Jasper3;

    @FXML
    Connection con = null;

    @FXML
    public void initialize() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/ventas";
        String user = "root";
        String clave = "";
        con = DriverManager.getConnection(url, user, clave);
        Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        String sql = "select * from ventas.ventas";
        ResultSet rs = stat.executeQuery(sql);
    }

    @FXML
    public void informe1() throws JRException {
        String fileRepo = ".mvn/Reports/totalVentasProducto.jasper";
        JasperPrint jpRepo = JasperFillManager.fillReport(fileRepo, null, con);
        JasperViewer viewer = new JasperViewer(jpRepo,false);
        viewer.setTitle("Ventas por producto");
        viewer.setVisible(true);
    }

    @FXML
    public void informe2() throws JRException {
        String fileRepo = ".mvn/Reports/ventasCategoria.jasper";
        JasperPrint jpRepo = JasperFillManager.fillReport(fileRepo, null, con);
        JasperViewer viewer = new JasperViewer(jpRepo,false);
        viewer.setTitle("Ventas por categoria");
        viewer.setVisible(true);
    }

    @FXML
    public void informe3() throws JRException {
        String fileRepo = ".mvn/Reports/ventasProductoCategoria.jasper";
        JasperPrint jpRepo = JasperFillManager.fillReport(fileRepo, null, con);
        JasperViewer viewer = new JasperViewer(jpRepo,false);
        viewer.setTitle("Ventas Producto Categoría");
        viewer.setVisible(true);
    }

}