package org.iesch.peluqueria;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.random.RandomGenerator;

public class Peluqueria implements Runnable{
    private final int num_peluqueros = 2;
    private final int num_sillas = 5;
    private final int tiempo_apertura = 30;
    private boolean abierto = true;

    private LinkedBlockingQueue<Cliente> sillas = new LinkedBlockingQueue<>(num_sillas);
    
    public Peluqueria() {
    }

    public int getNum_sillas() {
        return num_sillas;
    }

    public boolean isAbierto() {
        return abierto;
    }

    public void setAbierto(boolean abierto) {
        this.abierto = abierto;
    }

    public LinkedBlockingQueue<Cliente> getSillas() {
        return sillas;
    }

    public void setSillas(LinkedBlockingQueue<Cliente> sillas) {
        this.sillas = sillas;
    }

    public void generarPeluqueros(){
        for (int i = 1; i <= num_peluqueros; i++) {
            Peluquero peluquero = new Peluquero(i, this);
            Thread hiloPeluquero = new Thread(peluquero);
            hiloPeluquero.start();
        }
    }

    public void generarClientes(){
        int i = 1;

        while (isAbierto()){
            Cliente cliente = new Cliente(i, this);
            Thread hiloCliente = new Thread(cliente);
            hiloCliente.start();

            try {
                Thread.sleep(RandomGenerator.getDefault().nextInt(1000, 2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            i++;
        }
    }



    @Override
    public void run() {
        setAbierto(true);
        System.out.println("La peluquería ha habierto");

        generarPeluqueros();

        Thread generarClientes = new Thread(this::generarClientes);
        generarClientes.start();

        try {
            Thread.sleep(tiempo_apertura*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        setAbierto(false);
        System.out.println("La peluquería ha cerrado-----------------------");

    }
}
