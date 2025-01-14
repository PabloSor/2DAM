package org.example;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;

public class ClienteFTP2 {
    public static void main(String[] args) {
        FTPClient cliente = new FTPClient();
        String servFTP = "ftp.rediris.es";
        String usuario = "anonymous";
        String clave = "anonymous";

        try {
            cliente.connect(servFTP);
            cliente.enterLocalPassiveMode();

            boolean login = cliente.login(usuario, clave);

            if (login){
                System.out.println("Login correcto");
            }else {
                System.out.println("Login incorrecto");
                cliente.disconnect();
                System.exit(0);
            }

            System.out.println("Directorio actual: "+cliente.printWorkingDirectory());
            FTPFile [] files = cliente.listFiles();
            System.out.println("Cantidad de ficheros: "+files.length);

            String [] tipos = {"Fichero", "Directorio", "Enlace simb"};

            for (int i = 0; i < files.length; i++) {
                System.out.println("\t"+ files[i].getName()+" => "+tipos[files[i].getType()]);
            }

            boolean logout = cliente.logout();
            if (logout){
                System.out.println("LogOut correcto");
            }else {
                System.out.println("LogOut incorrecto");
                cliente.disconnect();
                System.exit(0);
            }

            cliente.disconnect();
            System.out.println("Desconectado");
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
