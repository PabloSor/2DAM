package org.example;

import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPSClient;
import org.apache.commons.net.smtp.SimpleSMTPHeader;
import java.io.Writer;
import java.util.Scanner;

public class EnviarCorreo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Lectura de datos
        System.out.print("Servidor SMTP: ");
        String smtpServer = sc.nextLine();

        System.out.print("¿Necesita negociación TLS (si/no)? ");
        String tlsInput = sc.nextLine();
        boolean needTLS = tlsInput.equalsIgnoreCase("si");

        System.out.print("Puerto: ");
        int port = Integer.parseInt(sc.nextLine());

        System.out.print("Nombre de usuario: ");
        String usuario = sc.nextLine();

        System.out.print("Contraseña: ");
        String password = sc.nextLine();

        System.out.print("Correo del remitente: ");
        String remitente = sc.nextLine();

        System.out.print("Correo del destinatario: ");
        String destinatario = sc.nextLine();

        System.out.print("Asunto: ");
        String asunto = sc.nextLine();

        System.out.println("Escribe el mensaje (varias líneas, finaliza con '*' en una línea):");
        StringBuilder mensajeBuilder = new StringBuilder();
        while (true) {
            String linea = sc.nextLine();
            if (linea.equals("*")) {
                break;
            }
            mensajeBuilder.append(linea).append("\n");
        }
        String mensaje = mensajeBuilder.toString().trim();

        if (mensaje.isEmpty()) {
            System.out.println("El mensaje no puede estar vacío.");
            return;
        }

        // Conexión y envío
        // Se utiliza SMTPSClient si se requiere TLS, de lo contrario SMTPClient.
        try {
            SMTPClient cliente;
            if (needTLS) {
                // En modo explícito, la negociación TLS se realiza después de conectar.
                SMTPSClient clienteTLS = new SMTPSClient(false); // false -> modo explícito
                clienteTLS.connect(smtpServer, port);
                System.out.println("Conectado al servidor SMTP: " + clienteTLS.getReplyString());

                if (clienteTLS.execTLS()) {
                    System.out.println("Negociación TLS establecida.");
                } else {
                    System.out.println("Negociación TLS no establecida.");
                }
                cliente = clienteTLS;
            } else {
                cliente = new SMTPClient();
                cliente.connect(smtpServer, port);
                System.out.println("Conectado al servidor SMTP: " + cliente.getReplyString());
            }

            // Enviar el saludo HELO
            // Se obtiene el nombre del host local (podrías obtenerlo dinámicamente)
            String localHost = "localhost";
            if (cliente.login(localHost)) {
                System.out.println("HELO enviado: " + cliente.getReplyString());
            } else {
                System.out.println("Error al enviar HELO: " + cliente.getReplyString());
            }

            // "Verificar" el usuario (los apuntes indican el método verify, aunque no autentica la contraseña)
            if (cliente.verify(usuario)) {
                System.out.println("Usuario verificado: " + cliente.getReplyString());
            } else {
                System.out.println("Usuario no verificado: " + cliente.getReplyString());
            }

            // Establecer el remitente
            if (cliente.setSender(remitente)) {
                System.out.println("Remitente establecido: " + cliente.getReplyString());
            } else {
                System.out.println("Error estableciendo remitente: " + cliente.getReplyString());
            }

            // Agregar el destinatario
            if (cliente.addRecipient(destinatario)) {
                System.out.println("Destinatario agregado: " + cliente.getReplyString());
            } else {
                System.out.println("Error agregando destinatario: " + cliente.getReplyString());
            }

            // Iniciar el envío del mensaje
            Writer writer = cliente.sendMessageData();
            if (writer == null) {
                System.out.println("Error al iniciar el envío de datos del mensaje: " + cliente.getReplyString());
            } else {
                // Crear la cabecera mínima usando SimpleSMTPHeader
                SimpleSMTPHeader header = new SimpleSMTPHeader(remitente, destinatario, asunto);
                writer.write(header.toString());
                writer.write(mensaje);
                writer.close();

                if (cliente.completePendingCommand()) {
                    System.out.println("Mensaje enviado correctamente.");
                } else {
                    System.out.println("Error enviando el mensaje: " + cliente.getReplyString());
                }
            }

            // Finalizar la sesión y desconectar
            cliente.logout();
            cliente.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
