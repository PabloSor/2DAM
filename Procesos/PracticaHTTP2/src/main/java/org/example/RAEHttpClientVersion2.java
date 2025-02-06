package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.time.Duration;
import java.util.Scanner;

public class RAEHttpClientVersion2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce la palabra a buscar: ");
        String palabra = scanner.nextLine().trim();
        scanner.close();

        String urlString = "https://dle.rae.es/" + palabra;

        // Crear el cliente con HttpClient.Builder
        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10)) // Establece un timeout
                .followRedirects(HttpClient.Redirect.ALWAYS) // Sigue redirecciones
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .header("Accept-Language", "es-ES,es;q=0.9")
                //.header("Connection", "keep-alive")
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String nombreArchivo = "resultadoV2.html";
                Files.write(Paths.get(nombreArchivo), response.body().getBytes());
                System.out.println("Página guardada en: " + nombreArchivo);
            } else {
                System.out.println("Error al obtener la página: Código " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}