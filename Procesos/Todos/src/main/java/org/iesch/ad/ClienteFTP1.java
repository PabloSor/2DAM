package org.iesch.ad;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;

public class ClienteFTP1 {
    public static void main(String[] args) throws IOException {

        FTPClient cliente = new FTPClient();
        String servFTP = "ftp.rediris.es";
        System.out.println("Nos conectamos a" + servFTP);
        cliente.connect(servFTP);

        //Respuesta del servidor FTP
        System.out.println(cliente.getReplyString());

        //Código de la respuesta
        int respuesta = cliente.getReplyCode();
        System.out.println("Reespuesta: " + respuesta);

        //Comprobaremos el código de esta respuesta
        if (!FTPReply.isPositiveCompletion(respuesta)) {
            cliente.disconnect();
            System.out.println("Conexión rechazada:" + respuesta);
            System.exit(0);
        }
        //Desconectar el servidor FTP
        cliente.disconnect();
        System.out.println("Conexión finalizada");
    }
}