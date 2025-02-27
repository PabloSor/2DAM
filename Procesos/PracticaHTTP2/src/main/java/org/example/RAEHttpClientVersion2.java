package org.example;

import java.io.*; // Para manejar archivos y flujos de escritura
import java.net.URI; // Para manejar URIs en la solicitud HTTP
import java.net.http.HttpClient; // Cliente HTTP para realizar la solicitud
import java.net.http.HttpRequest; // Para construir la solicitud HTTP
import java.net.http.HttpResponse; // Para manejar la respuesta del servidor
import java.util.Scanner; // Para leer la entrada del usuario

public class RAEHttpClientVersion2 {
    public static void main(String[] args) {

        // Crear un Scanner para leer la palabra ingresada por el usuario
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce una palabra que quieras buscar en la RAE:");
        String palabra = scanner.nextLine().trim().replace(" ", "%20"); // Eliminar espacios en blanco extra y reemplazar espacios por "%20"
        scanner.close(); // Cerrar el Scanner después de usarlo para evitar fugas de memoria

        // Crear el cliente HTTP
        HttpClient httpClient = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS) // Configurar para seguir redirecciones automáticamente
                .version(HttpClient.Version.HTTP_1_1) // Usar la versión HTTP 1.1
                .build();

        // Construir la solicitud HTTP GET a la RAE
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create("https://dle.rae.es/" + palabra)) // URL de la RAE con la palabra ingresada
                //.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36") // Agregar un User-Agent para evitar bloqueos del servidor
                .GET() // Método GET
                .build();

        try {
            // Enviar la solicitud y recibir la respuesta en formato String
            HttpResponse<String> respuesta = httpClient.send(solicitud, HttpResponse.BodyHandlers.ofString());

            // Verificar si la respuesta es exitosa (código 200) y si el cuerpo no está vacío
            if (respuesta.statusCode() == 200 && !respuesta.body().isEmpty()) {
                // Crear el directorio "HTML" si no existe
                File directorio = new File("HTML");
                if (!directorio.exists()) {
                    directorio.mkdirs(); // Crear el directorio si no existe
                }

                // Crear el archivo donde se guardará la respuesta
                File file = new File(directorio, "ArchivoClienteBuilder.html");

                // Escribir la respuesta en el archivo
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                    bufferedWriter.write(respuesta.body());
                    System.out.println("Archivo guardado en: " + file.getAbsolutePath()); // Mostrar la ruta del archivo generado
                }
            } else {
                // Si la respuesta no es válida, mostrar un mensaje de error
                System.out.println("Error: la palabra no fue encontrada o la respuesta está vacía.");
            }
        } catch (IOException | InterruptedException e) {
            // Capturar errores de conexión o interrupción de la solicitud
            System.out.println("Error al realizar la solicitud: " + e.getMessage());
        }
    }
}