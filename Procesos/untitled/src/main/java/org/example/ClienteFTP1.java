package org.example;


import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;

public class ClienteFTP1 {
    public static void main(String[] args) {
        FTPClient cliente = new FTPClient();
        String serverFTP = "ftp.rediris.es";
        System.out.println("Nos conectamos a "+serverFTP);

        // Respuesta del servidor FTP
        System.out.println(cliente.getReplyString());

        // Código de la respuesta
        int respuesta = cliente.getReplyCode();
        System.out.println("Código respuesta: "+respuesta);

        // Comprobamos el código que la respuesta
        if (!FTPReply.isPositiveCompletion(respuesta)){
            try {cliente.disconnect();} catch (IOException e) {throw new RuntimeException(e);}
            System.out.println("Conexión rechazada "+respuesta);
            System.exit(0);
        }

        // Desconectar el servidor FTP
        try {cliente.disconnect();} catch (IOException e) {throw new RuntimeException(e);}
        System.out.println("Conexión finalizada");
    }
}