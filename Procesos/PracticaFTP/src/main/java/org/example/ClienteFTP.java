package org.example;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;

public class ClienteFTP {

    public static void main(String[] args) {
        String server = "ftp.rediris.es";
        int port = 21;
        String user = "anonymous";
        String pass = ""; // Se puede usar el correo como contraseña o dejarlo vacío.

        FTPClient ftpClient = new FTPClient();

        try {
            // Conexión al servidor FTP
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);

            // Verificar conexión exitosa
            if (ftpClient.isConnected()) {
                System.out.println("Conectado a " + server);

                // Obtener la lista de directorios y archivos en el directorio raíz
                ftpClient.enterLocalPassiveMode();
                printDirectoryContents(ftpClient, "/");
            } else {
                System.out.println("No se pudo conectar al servidor FTP.");
            }

            // Cerrar sesión y desconectarse
            ftpClient.logout();
            ftpClient.disconnect();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void printDirectoryContents(FTPClient ftpClient, String path) throws IOException {
        // Listar archivos y directorios en el directorio actual
        FTPFile[] files = ftpClient.listFiles(path);

        System.out.println("Contenido de " + path + ":");

        for (FTPFile file : files) {
            if (file.isFile()) {
                System.out.println("Archivo: " + file.getName());
            } else if (file.isDirectory()) {
                System.out.println("Directorio: " + file.getName());

                // Recursivamente entrar a cada subdirectorio
//                String subPath = path + file.getName() + "/";
//                printDirectoryContents(ftpClient, subPath);
            }
        }
    }
}
