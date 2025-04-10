package Tema4.FTP;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import org.apache.commons.net.ftp.*;


public class FTPManager {

    private FTPClient FTPClient;
    private static final String SERVER = "ftp.dlptest.com";
    private static final int PORT = 21;
    private static final String USER = "dlpuser";
    private static final String PASSWORD = "rNrKYTX9g7z3RgJRmxWuGHbeu";

    public FTPManager() {
        FTPClient = new FTPClient();
    }

    private void connect() throws SocketException, IOException {
        FTPClient.connect(SERVER, PORT);
        int answer = FTPClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(answer)) {
            FTPClient.disconnect();
            throw new IOException("Error connecting to FTP Server");
        }
        boolean credentials = FTPClient.login(USER, PASSWORD);
        if (!credentials) {
            throw new IOException("Error connecting to FTP. Wrong credentials");
        }
        FTPClient.setFileType(FTP.BINARY_FILE_TYPE);
    }

    private void desconectar() throws IOException {
        FTPClient.disconnect();
    }

    private boolean uploadFile(String path) throws IOException {
        File localFile = new File(path);
        boolean sent;
        try ( InputStream is = new FileInputStream(localFile)) {
            sent = FTPClient.storeFile(localFile.getName(), is);
        }
        return sent;
    }

    public static void main(String[] args) {
        FTPManager ftpManager = new FTPManager();
        try {
            ftpManager.connect();
            System.out.println("Connected");
            boolean uploaded = ftpManager.uploadFile("pez.html");
            if (uploaded) {
                System.out.println("File successfully uploaded.");
            } else {
                System.err.println("Something went wrong uploading file.");
            }
            ftpManager.desconectar();
            System.out.println("Disconnected");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}