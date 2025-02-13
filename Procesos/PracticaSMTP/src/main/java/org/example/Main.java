package org.example;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Base64;
import java.util.Scanner;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar parámetros al usuario
        System.out.print("Servidor SMTP: ");
        String smtpServer = scanner.nextLine().trim();

        System.out.print("¿Se necesita confirmación TLS? (si/no): ");
        String tlsInput = scanner.nextLine().trim().toLowerCase();
        boolean useTLS = tlsInput.equals("si");

        System.out.print("Puerto: ");
        int port = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Nombre de usuario: ");
        String username = scanner.nextLine().trim();

        System.out.print("Contraseña: ");
        String password = scanner.nextLine().trim();

        System.out.print("Correo remitente: ");
        String remitente = scanner.nextLine().trim();

        System.out.print("Correo destino: ");
        String destino = scanner.nextLine().trim();

        System.out.print("Asunto: ");
        String asunto = scanner.nextLine().trim();

        System.out.println("Mensaje (finaliza con una línea que contenga solo '*'):");
        StringBuilder mensajeBuilder = new StringBuilder();
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("*")) {
                break;
            }
            mensajeBuilder.append(line).append("\r\n");
        }
        String mensaje = mensajeBuilder.toString().trim();

        // Verificar que el mensaje no esté vacío
        if (mensaje.isEmpty()) {
            System.out.println("Error: No se puede enviar un mensaje vacío.");
            return;
        }

        Socket socket = null;
        BufferedReader reader = null;
        PrintWriter writer = null;

        try {
            // Conectar al servidor SMTP
            socket = new Socket(smtpServer, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            // Leer y mostrar el mensaje de bienvenida del servidor
            String response = readResponse(reader);
            System.out.println("Servidor: " + response);

            // Enviar EHLO
            String ehloCommand = "EHLO localhost";
            System.out.println("Cliente: " + ehloCommand);
            writer.println(ehloCommand);
            response = readResponse(reader);
            System.out.println("Servidor: " + response);

            // Si TLS es requerido, enviar STARTTLS
            if (useTLS) {
                String starttlsCommand = "STARTTLS";
                System.out.println("Cliente: " + starttlsCommand);
                writer.println(starttlsCommand);
                response = readResponse(reader);
                System.out.println("Servidor: " + response);
                if (response.startsWith("220")) {
                    System.out.println("Negociación TLS establecida.");
                    // Elevar la conexión a TLS
                    SSLSocketFactory sslFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
                    SSLSocket sslSocket = (SSLSocket) sslFactory.createSocket(socket, smtpServer, port, true);
                    sslSocket.startHandshake();

                    // Reasignar la conexión a la versión segura
                    socket = sslSocket;
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    writer = new PrintWriter(socket.getOutputStream(), true);

                    // Se recomienda reenviar EHLO después de establecer TLS
                    System.out.println("Cliente: " + ehloCommand);
                    writer.println(ehloCommand);
                    response = readResponse(reader);
                    System.out.println("Servidor: " + response);
                } else {
                    System.out.println("Negociación TLS no establecida.");
                    // Se puede optar por abortar aquí o continuar sin TLS.
                }
            }

            // Autenticación
            String authCommand = "AUTH LOGIN";
            System.out.println("Cliente: " + authCommand);
            writer.println(authCommand);
            response = readResponse(reader);
            System.out.println("Servidor: " + response);
            if (!response.startsWith("334")) {
                System.out.println("Error en el inicio de autenticación.");
                return;
            }

            // Enviar nombre de usuario codificado en Base64
            String encodedUsername = Base64.getEncoder().encodeToString(username.getBytes());
            System.out.println("Cliente: (usuario en Base64)");
            writer.println(encodedUsername);
            response = readResponse(reader);
            System.out.println("Servidor: " + response);
            if (!response.startsWith("334")) {
                System.out.println("Error en la autenticación del usuario.");
                return;
            }

            // Enviar contraseña codificada en Base64
            String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
            System.out.println("Cliente: (contraseña en Base64)");
            writer.println(encodedPassword);
            response = readResponse(reader);
            System.out.println("Servidor: " + response);
            if (!response.startsWith("235")) {
                System.out.println("Autenticación fallida.");
                return;
            }

            // Enviar MAIL FROM
            String mailFromCommand = "MAIL FROM:<" + remitente + ">";
            System.out.println("Cliente: " + mailFromCommand);
            writer.println(mailFromCommand);
            response = readResponse(reader);
            System.out.println("Servidor: " + response);
            if (!response.startsWith("250")) {
                System.out.println("Error en MAIL FROM.");
                return;
            }

            // Enviar RCPT TO
            String rcptToCommand = "RCPT TO:<" + destino + ">";
            System.out.println("Cliente: " + rcptToCommand);
            writer.println(rcptToCommand);
            response = readResponse(reader);
            System.out.println("Servidor: " + response);
            if (!response.startsWith("250")) {
                System.out.println("Error en RCPT TO.");
                return;
            }

            // Iniciar la transmisión del mensaje con DATA
            String dataCommand = "DATA";
            System.out.println("Cliente: " + dataCommand);
            writer.println(dataCommand);
            response = readResponse(reader);
            System.out.println("Servidor: " + response);
            if (!response.startsWith("354")) {
                System.out.println("Error al iniciar DATA.");
                return;
            }

            // Enviar encabezados y cuerpo del mensaje
            writer.println("From: " + remitente);
            writer.println("To: " + destino);
            writer.println("Subject: " + asunto);
            writer.println(); // línea en blanco para separar encabezados de cuerpo
            writer.println(mensaje);
            writer.println("."); // línea que indica el final del mensaje
            response = readResponse(reader);
            System.out.println("Servidor: " + response);
            if (!response.startsWith("250")) {
                System.out.println("Error al enviar el mensaje.");
                return;
            }

            // Enviar QUIT
            String quitCommand = "QUIT";
            System.out.println("Cliente: " + quitCommand);
            writer.println(quitCommand);
            response = readResponse(reader);
            System.out.println("Servidor: " + response);

            System.out.println("Correo enviado exitosamente.");

        } catch (UnknownHostException e) {
            System.out.println("Error: Servidor no encontrado.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error de E/S.");
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (writer != null) writer.close();
                if (reader != null) reader.close();
                if (socket != null && !socket.isClosed()) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Lee la respuesta del servidor teniendo en cuenta respuestas multilínea.
     * Una respuesta multilínea en SMTP tiene la forma "XYZ-..." y termina cuando se
     * recibe una línea que comienza por "XYZ " (con espacio).
     */
    private static String readResponse(BufferedReader reader) throws IOException {
        StringBuilder response = new StringBuilder();
        String line = reader.readLine();
        if (line == null) return "";
        response.append(line);
        while (line.length() >= 4 && line.charAt(3) == '-') {
            line = reader.readLine();
            if (line == null) break;
            response.append("\n").append(line);
        }
        return response.toString();
    }
}