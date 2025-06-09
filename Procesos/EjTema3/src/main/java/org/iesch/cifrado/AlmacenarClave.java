package org.iesch.cifrado;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class AlmacenarClave {
    public static void main(String[] args) {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
            SecureRandom numero = SecureRandom.getInstance("SHA1PRNG");
            kpg.initialize(2048, numero);

            KeyPair parClaves = kpg.generateKeyPair();
            PrivateKey clavePrivada = parClaves.getPrivate();
            PublicKey clavePublica = parClaves.getPublic();

            PKCS8EncodedKeySpec encodedPrivada = new PKCS8EncodedKeySpec(clavePrivada.getEncoded());
            X509EncodedKeySpec encodePublica = new X509EncodedKeySpec(clavePublica.getEncoded());

            FileOutputStream fosPrivate = new FileOutputStream("src/main/java/org/iesch/cifrado/archivos/ClavePrivada.txt");
            FileOutputStream fosPublic = new FileOutputStream("src/main/java/org/iesch/cifrado/archivos/ClavePublica.txt");

            fosPrivate.write(encodedPrivada.getEncoded());
            fosPublic.write(encodePublica.getEncoded());

            fosPrivate.close();
            fosPublic.close();

        } catch (NoSuchAlgorithmException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
