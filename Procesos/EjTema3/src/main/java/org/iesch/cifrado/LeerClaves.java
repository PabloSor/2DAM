package org.iesch.cifrado;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class LeerClaves {
    public static void main(String[] args) {
        try {
            FileInputStream fisPrivada = new FileInputStream("src/main/java/org/iesch/cifrado/archivos/ClavePrivada.txt");
            FileInputStream fisPublica = new FileInputStream("src/main/java/org/iesch/cifrado/archivos/ClavePublica.txt");

            byte [] bufferPrivada = new byte[fisPrivada.available()];
            byte [] bufferPublica = new byte[fisPublica.available()];

            fisPrivada.read(bufferPrivada);
            fisPublica.read(bufferPublica);

            KeyFactory keyFactory = KeyFactory.getInstance("DSA");

            PKCS8EncodedKeySpec encodedPrivada = new PKCS8EncodedKeySpec(bufferPrivada);
            X509EncodedKeySpec encodedPublica = new X509EncodedKeySpec(bufferPublica);

            PrivateKey clavePrivada = keyFactory.generatePrivate(encodedPrivada);
            PublicKey clavePublica = keyFactory.generatePublic(encodedPublica);

            System.out.println(clavePrivada.toString());
            System.out.println(clavePublica.toString());

            fisPrivada.close();
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
