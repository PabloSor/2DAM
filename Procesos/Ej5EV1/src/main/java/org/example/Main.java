package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        String uri = "https://www.google.com/search?q=Java+21";

        try {
            // Crear objeto URL
            URL url = new URL(uri);

            // Obtener protocolo y dominio
            String protocolo = url.getProtocol();
            String dominio = url.getHost();

            // Obtener la ruta de acceso
            String ruta = url.getPath().substring(1); // Eliminar la primera barra

            // Obtener el término de búsqueda
            String query = url.getQuery();

            // Mostrar resultados
            System.out.println("Protocolo= " + protocolo);
            System.out.println("Dominio= " + dominio);
            System.out.println("Ruta de acceso= " + ruta);
            System.out.println("Término de búsqueda= " + query);

        } catch (Exception e) {
            System.out.println("Error al procesar la URL: " + e.getMessage());
        }



        /*
        //crear un output

        Car car = new Car("7234JNG", 140, 4, 290000);
        try (FileOutputStream out = new FileOutputStream("coche.ser");
         ObjectOutputStream objectout = new ObjectOutputStream(out)){

            objectout.writeObject(car);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //leer un output

        Car car = null;
        try (FileInputStream in = new FileInputStream("coche.ser");
         ObjectInputStream objectIn = new ObjectInputStream(in)){
            car = (Car) objectIn.readObject();
            System.out.println(car.toString());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        */

        // Hacer y leer un json con libreria jacksonCore
        Car car = new Car("7234JNG", 140, 4, 290000);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String json = objectMapper.writeValueAsString(car); // para mostrar por pantalla
            objectMapper.writeValue(new File("car.json"), car); // para crear fichero
            System.out.println(json);

            Car cocheLeido = objectMapper.readValue(json, Car.class); // para leer desde string
            Car cocheArchivo = objectMapper.readValue(new File("car.json"), Car.class);
            System.out.println("CocheLeido "+cocheLeido.toString());
            System.out.println("CocheArchivo "+cocheArchivo.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
}