package org.example;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;

public class EnviarFTP {
    public static void main(String[] args) {
        // Datos del servidor FTP
        String server = "192.168.50.244";
        int port = 21;
        String user = "DAM2";
        String pass = "contraseña"; // Contraseña para DAM2 filezilla

        FTPClient ftpClient = new FTPClient();

        try {
            // Conectar al servidor FTP
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode(); // Modo pasivo

            // Configurar el modo de transferencia a binario para evitar corrupción de archivos
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // Crear un archivo temporal para subir
            File tempFile = new File("archivo_prueba.txt"); // Cambiar si se usa un archivo real, pongo esto de ejemplo
            try (FileWriter writer = new FileWriter(tempFile)) {
                writer.write("Este es un archivo de prueba subido por FTP en Java.");
            }

            // Convertir el archivo en un InputStream
            try (InputStream inputStream = new FileInputStream(tempFile)) {
                String remoteFilePath = "/archivo_subido.txt"; // Ruta en el servidor FTP
                System.out.println("Subiendo el archivo a " + remoteFilePath);

                // Subir el archivo
                boolean done = ftpClient.storeFile(remoteFilePath, inputStream);
                if (done) {
                    System.out.println("Archivo subido con éxito.");
                } else {
                    System.out.println("Error al subir el archivo.");
                }
            }

            // Eliminar el archivo temporal después de la subida
            tempFile.delete();

            // Cerrar sesión y desconectarse
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
