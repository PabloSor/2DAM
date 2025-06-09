package org.iesch.cifrado;

import java.security.*;

public class Claves {
    public static void main(String[] args) {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
            SecureRandom numero = SecureRandom.getInstance("SHA1PRNG");
            kpg.initialize(2048, numero);

            KeyPair parClaves = kpg.generateKeyPair();
            PrivateKey clavePrivada = parClaves.getPrivate();
            PublicKey clavePublica = parClaves.getPublic();

            Signature firma = Signature.getInstance("SHA256withDSA");
            firma.initSign(clavePrivada);

            String mensaje = "Este es el mensaje que voy a firmar";
            firma.update(mensaje.getBytes());

            byte[] mensajeFirmado = firma.sign();


            // Verificar que la firma es original
            Signature verificacion = Signature.getInstance("SHA256withDSA");
            verificacion.initVerify(clavePublica);

            verificacion.update(mensaje.getBytes());

            if (verificacion.verify(mensajeFirmado)){
                System.out.println("Firma verificada");
            }else {
                System.out.println("Firma no verificada");
            }



        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new RuntimeException(e);
        }
    }
}
