package org.iesch.cifrado;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class DescrifrarMensaje {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("src/main/java/org/iesch/cifrado/archivos/CifrarMensaje.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            String textoOriginal = "Este es el texto que voy a cifrar";
            byte[] textoCifrado = (byte[]) ois.readObject();

            byte[] bytesOriginal = textoOriginal.getBytes();
            md.update(bytesOriginal);

            byte[] textoOriginalCifrado = md.digest();

            if (MessageDigest.isEqual(textoOriginalCifrado, textoCifrado)){
                System.out.println("El texto cifrado no fue modificado");
            }else {
                System.out.println("El texto cifrado fue modificado");
            }


            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
