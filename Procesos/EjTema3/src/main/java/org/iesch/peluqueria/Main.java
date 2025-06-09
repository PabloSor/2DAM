package org.iesch.peluqueria;

public class Main {
    public static void main(String[] args) {
        Peluqueria peluqueria = new Peluqueria();

        Thread pelucas = new Thread(peluqueria);

        pelucas.start();
    }
}
