package org.iesch.cifrado;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CifrarMensaje {
    public static void main(String[] args) {
        String texto = "Este es el texto que voy a cifrar";
        byte[] textoBites = texto.getBytes();

        try {
            FileOutputStream fos = new FileOutputStream("src/main/java/org/iesch/cifrado/archivos/CifrarMensaje.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(textoBites);
            byte[] textoCifrado = md.digest();

            oos.writeObject(textoCifrado);

            oos.close();
            fos.close();
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
