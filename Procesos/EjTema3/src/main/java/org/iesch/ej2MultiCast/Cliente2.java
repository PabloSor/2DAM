package org.iesch.ej2MultiCast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Cliente2 {
    public static void main(String[] args) {
        try {
            MulticastSocket socket = new MulticastSocket(8080);
            InetAddress group = InetAddress.getByName("230.0.0.0");
            socket.joinGroup(group);

            byte[] buff = new byte[256];
            DatagramPacket packet = new DatagramPacket(buff, buff.length);

            while (true){
                socket.receive(packet);
                String mensaje = new String(packet.getData(), 0, packet.getLength());
                System.out.println(mensaje);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
