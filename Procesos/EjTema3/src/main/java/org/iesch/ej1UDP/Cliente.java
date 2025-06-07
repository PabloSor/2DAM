package org.iesch.ej1UDP;

import javax.swing.*;
import java.io.IOException;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        int puertoServidor = 8888;
        byte[] buffer = new byte[1024];

        try {
            DatagramSocket cliente = new DatagramSocket();
            InetAddress servidor = InetAddress.getByName("localhost");

            while (true) {

                String mensaje = JOptionPane.showInputDialog("Introduce dia/mes/año o - exit - para salir");
                byte[] bites = mensaje.getBytes();


                DatagramPacket packet = new DatagramPacket(bites, bites.length, servidor, puertoServidor);
                cliente.send(packet);

                if (mensaje.equals("exit")){break;}

                packet = new DatagramPacket(buffer, bites.length);
                cliente.receive(packet);

                String respuesta = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Respuesta del servidor -> " + respuesta);

            }


            cliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
