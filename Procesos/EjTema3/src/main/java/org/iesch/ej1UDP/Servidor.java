package org.iesch.ej1UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.time.LocalDate;
import java.util.List;

public class Servidor {
    public static void main(String[] args) {

        int puerto = 8888;
        byte[] bufer = new byte[1024];

        try {
            DatagramSocket servidor = new DatagramSocket(puerto);

            while (true) {

                DatagramPacket packet = new DatagramPacket(bufer, bufer.length);

                servidor.receive(packet);
                String mensaje = new String(packet.getData(), 0, packet.getLength());

                System.out.println("Mensaje recibido -> "+mensaje);

                if (mensaje.equals("exit")){break;}

                List<String> partes = List.of(mensaje.split("/"));

                int dia = Integer.parseInt(partes.getFirst());
                int mes = Integer.parseInt(partes.get(1));
                int anno = Integer.parseInt(partes.getLast());

                LocalDate date = LocalDate.of(anno, mes, dia);

                String diaSemana = String.valueOf(date.getDayOfWeek().getValue());

                packet = new DatagramPacket(diaSemana.getBytes(), diaSemana.getBytes().length, packet.getAddress(), packet.getPort());
                servidor.send(packet);

            }



            servidor.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
