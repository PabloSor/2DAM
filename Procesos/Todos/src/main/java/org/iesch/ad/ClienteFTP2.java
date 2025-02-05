package org.iesch.ad;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;
import java.net.SocketException;

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

            if (login)
                System.out.println("Login correcto");
            else {
                System.out.println("Login incorrecto");
                cliente.disconnect();
                System.exit(0);
            }

            //cambiar de directorio



            
            System.out.println("Directorio actual:" + cliente.printWorkingDirectory());
            FTPFile [] files = cliente.listFiles();
            System.out.println("Ficheros en el directorio actual: " + files.length);

            String tipos[] = {"Fichero", "Directorio", "Enlace simb."};

            for (int i = 0; i < files.length; i++) {
                System.out.println("\t" + files[i].getName() + " =>" + tipos[files[i].getType()]);
            }

            boolean logout = cliente.logout();

            if (login)
                System.out.println("Logout correcto");
            else {
                System.out.println("Logout incorrecto");
            }

            cliente.disconnect();
            System.out.println("Desconectado");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
