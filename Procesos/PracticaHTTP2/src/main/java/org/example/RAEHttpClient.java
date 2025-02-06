package org.example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class RAEHttpClient {
    public static void main(String[] args) {
        // Pedir una palabra al usuario
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce la palabra a buscar: ");
        String palabra = scanner.nextLine();
        scanner.close();

        // Usar la url de la RAE seguida de la palabra del usuario
        String urlString = "https://dle.rae.es/" + palabra;

        try {
            // Crear la conexión HTTP utilizando el metodo GET
            URL url = new URL(urlString);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");

            // Muchas páginas web bloquean peticiones GET para evitar ataques,
            // para evitar el error 403, utilizo un User-Agent, que básicamente simula
            // que la petición viene de un navegador.
            conexion.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win 64; x64 AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36)");

            // Leer la respuesta y almacenarla en un StringBuilder
            BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            StringBuilder contenido = new StringBuilder();
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
            reader.close();

            // Guardar en un archivo los datos del StringBuilder
            String nombreArchivo = "resultado.html";
            BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo));
            writer.write(contenido.toString());
            writer.close();

            System.out.println("Resultados guardados");

        } catch (IOException e) {
            System.out.println("Error al obtener la página: " + e.getMessage());
        }
    }
}