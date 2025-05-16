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


    }
}
