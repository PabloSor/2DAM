package org.iesch.practicaSerializacionBBDD;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.iesch.practicaSerializacionBBDD.clases.Cerdo;
import org.iesch.practicaSerializacionBBDD.clases.Vendedor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Apartado 2, serializar 2 cerdos a JSON
        Cerdo cerdo1 = new Cerdo(1, "Sanchez", "Politica", "2019-10-9", 480);
        Cerdo cerdo2 = new Cerdo(2, "Yolanda", "Ladron", "2018-1-24", 440);

        List<Cerdo> cerdos = List.of(cerdo1, cerdo2);


        Path archivos = Paths.get("src/main/java/org/iesch/practicaSerializacionBBDD/archivos");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(archivos.resolve("cerdos.json").toFile(), cerdos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // Apartado 3, serializar 2 vendedores a XML
        Vendedor vendedor1 = new Vendedor(1, "Pablo", "Carrefour", "psorianoa@iesch.org");
        Vendedor vendedor2 = new Vendedor(2, "Sanchez", "Gobierno", "ladrones@moros.hdp");

        List<Vendedor> vendedores = List.of(vendedor1, vendedor2);

        XmlMapper xmlMapper = new XmlMapper();

        try {
            xmlMapper.writeValue(archivos.resolve("vendedores.xml").toFile(), vendedores);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // Apartado 4, deserializar JSON
        try {
            List<Cerdo> cerdosDeserializados = objectMapper.readValue(archivos.resolve("cerdos.json").toFile(), new TypeReference<List<Cerdo>>() {});
            cerdosDeserializados.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // Apartado 5, deserializar XML
        try {
            List<Vendedor> vendedoresDeserializados = xmlMapper.readValue(archivos.resolve("vendedores.xml").toFile(), new TypeReference<List<Vendedor>>() {});
            vendedoresDeserializados.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // Apartado 6, implementar conexion a BBDD
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/practicaAngel1", "root", "1234");
             Statement statement = connection.createStatement();){

            // Insertar registros en la tabla cerdos
            String inserccionCerdos = "INSERT INTO cerdos (nombre, raza, fecha_nacimiento, peso) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(inserccionCerdos);

            // Crear cerdo 1
            ps.setString(1, "Cerdo1");
            ps.setString(2, "RazaCerdo1");
            ps.setDate(3, Date.valueOf("2020-01-04"));
            ps.setDouble(4, 435.21);

            //ps.executeUpdate();

            // Crear cerdo 2
            ps.setString(1, "Cerdo2");
            ps.setString(2, "RazaCerdo2");
            ps.setDate(3, Date.valueOf("2020-01-04"));
            ps.setDouble(4, 435.21);

            //ps.executeUpdate();

            // Crear cerdo 3
            ps.setString(1, "Cerdo3");
            ps.setString(2, "RazaCerdo3");
            ps.setDate(3, Date.valueOf("2020-01-04"));
            ps.setDouble(4, 435.21);

            //ps.executeUpdate();

            ps.close();


            // Insertar un vendedor
            String inserccionVendedor = "INSERT INTO vendedores (nombre, empresa, contacto) VALUES (?, ?, ?)";

            ps = connection.prepareStatement(inserccionVendedor);

            // Crear trabajador 1
            ps.setString(1, "Vendedor 1");
            ps.setString(2, "Empresa 1");
            ps.setString(3, "vendedor1@gmail.com");

            //ps.executeUpdate();

            // Crear trabajador 2
            ps.setString(1, "Vendedor 1");
            ps.setString(2, "Empresa 1");
            ps.setString(3, "vendedor1@gmail.com");

            //ps.executeUpdate();
            ps.close();


            // Actualizar el trabajador 2
            inserccionVendedor = "UPDATE vendedores SET nombre = ?, empresa = ?, contacto = ? WHERE id = ?";
            ps = connection.prepareStatement(inserccionVendedor);

            ps.setString(1, "VendedorModificado");
            ps.setString(2, "EmpresaModificada");
            ps.setString(3, "vendedorMod@gmail.com");
            ps.setInt(4, 2);

            //ps.executeUpdate();
            ps.close();


            // Mostrar en consola datos de ambas tablas
            String mostrarCerdos = "SELECT * FROM cerdos";
            String mostrarVendedores = "SELECT * FROM vendedores";

            // Mostrar cerdos
            ResultSet set = statement.executeQuery(mostrarCerdos);

            while (set.next()){
                System.out.println("id -> "+set.getString(1)+
                        " nombre -> "+set.getString(2)+
                        " raza -> "+set.getString(3)+
                        " fecha -> "+set.getString(4)+
                        " peso -> "+set.getString(5));
            }


            // Mostrar vendedores
            set = statement.executeQuery(mostrarVendedores);

            while (set.next()){
                System.out.println("id -> "+set.getString(1)+
                        " nombre -> "+set.getString(2)+
                        " empresa -> "+set.getString(3)+
                        " contacto -> "+set.getString(4));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
