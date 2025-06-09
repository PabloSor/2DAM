package org.iesch.peluqueria;

import java.util.random.RandomGenerator;

public class Peluquero implements Runnable{
    private int id;
    private Peluqueria peluqueria;

    public Peluquero(int id, Peluqueria peluqueria) {
        this.id = id;
        this.peluqueria = peluqueria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Peluqueria getPeluqueria() {
        return peluqueria;
    }

    public void setPeluqueria(Peluqueria peluqueria) {
        this.peluqueria = peluqueria;
    }

    @Override
    public void run() {
        while (true){
            Cliente clienteActual = peluqueria.getSillas().poll();

            if (clienteActual != null){

                System.out.println("El peluquero "+getId()+" está cortando el pelo al cliente "+clienteActual.getId());

                try {
                    Thread.sleep(RandomGenerator.getDefault().nextInt(4000, 6000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else {
                if (peluqueria.isAbierto()) {
                    System.out.println("El peluquero " + getId() + " está esperando clientes");
                }else {
                    break;
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        System.out.println("El peluquero "+getId()+" ha terminado y se va");
    }
}
