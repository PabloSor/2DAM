package org.iesch.ej3TCP;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8080);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner in = new Scanner(socket.getInputStream());

            String mensaje = JOptionPane.showInputDialog("Introduce tu nombre");
            out.println(mensaje);

            String respuesta = in.nextLine();
            JOptionPane.showMessageDialog(null, respuesta);


            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
