package org.iesch.ej3TCP;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(8080);
            Socket cliente = socket.accept();

            PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
            Scanner in = new Scanner(cliente.getInputStream());

            String mensaje = in.nextLine();
            String respuesta = "Hola!! " +mensaje+" ¿Cómo estás?";
            out.println(respuesta);

            out.close();
            in.close();
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
