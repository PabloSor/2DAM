package org.iesch.ej2MultiCast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Servidor {
    public static void main(String[] args) {
        try {
            MulticastSocket socket = new MulticastSocket(8080);
            InetAddress group = InetAddress.getByName("230.0.0.0");
            socket.joinGroup(group);

            String mensaje = "Mensaje para el grupo de mantecados";
            DatagramPacket packet = new DatagramPacket(mensaje.getBytes(), mensaje.length(), group, 8080);

            socket.send(packet);

            socket.leaveGroup(group);
            socket.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
