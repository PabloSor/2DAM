package Tema4.mail;

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import java.io.IOException;
import java.io.Writer;
import java.net.SocketException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

public class EnvioSMTP {
    public static void main(String[] args) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {

        AuthenticatingSMTPClient client = new AuthenticatingSMTPClient();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Introduce servidor SMTP:");
            String server = scanner.next();
            System.out.println("¿Necesita negociación TLS (S/N)?");
            String tls = scanner.next().toUpperCase();
            System.out.println("Introduce usuario:");
            String usuario = scanner.next();
            System.out.println("Introduce contraseña:");
            String password = scanner.next();
            System.out.println("Introduce el puerto:");
            int puerto = scanner.nextInt();
            System.out.println("Introduce correo del remitente:");
            String correoRemitente = scanner.next();
            System.out.println("Introduce correo del destinatario:");
            String correoDestinatario = scanner.next();
            System.out.println("Introduce asunto:");
            String asuntoRecibo = scanner.next();
            System.out.println("Introduce el mensaje, finalizará cuando se introduzca '*':");
            StringBuilder mensajeProcesar = new StringBuilder();
            while (true) {
                String linea = scanner.nextLine();
                if (linea.equals("*")) {
                    break; // Termina la lectura cuando el usuario ingresa "*"
                }
                mensajeProcesar.append(linea).append("\n"); // Agrega la línea al mensaje
            }
            String mensajeRecibido = String.valueOf(mensajeProcesar);


            int respuesta;
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(null, null);
            KeyManager km = kmf.getKeyManagers()[0];

            // Conectamos con el servidor SMTP
            client.connect(server, puerto);
            // Establecemos conexión segura
            client.setKeyManager(km);

            respuesta = client.getReplyCode();
            if (!SMTPReply.isPositiveCompletion(respuesta)) {
                client.disconnect();
                System.err.println("CONEXIÓN RECHAZADA");
                System.exit(1);
            }
            client.ehlo(server);

            if (tls.equals("S") && client.execTLS()) {

                if (client.auth(AuthenticatingSMTPClient.AUTH_METHOD.PLAIN, usuario, password)) {

                    // Creamos cabecera
                    SimpleSMTPHeader cabecera = new SimpleSMTPHeader(correoRemitente, correoDestinatario, asuntoRecibo);

                    // El nombre de usuario y el email de origen coinciden
                    client.setSender(correoRemitente);
                    client.addRecipient(correoDestinatario);

                    // Se envía DATA
                    Writer writer = client.sendMessageData();
                    if (writer == null) {
                        System.out.println("Fallo al enviar data.");
                        System.exit(1);
                    }

                    writer.write(cabecera.toString());
                    writer.write(mensajeRecibido.toString());
                    writer.close();

                    boolean exito = client.completePendingCommand();
                    if (!exito) {
                        System.out.println("Fallo al finalizar transacción");
                        System.exit(1);
                    } else {
                        System.out.println("Mensaje enviado con éxito");
                    }
                } else {
                    System.out.println("Usuario no autenticado");
                }
            } else {
                System.out.println("Error al ejecutar STARTTLS");
            }

        } catch (SocketException e) {
            System.err.println("Error de socket: " + e.getMessage());
        } catch (IOException | InvalidKeyException | InvalidKeySpecException e) {
            System.err.println("Error de entrada/salida o clave: " + e.getMessage());
        }
    }
}