package org.iesch;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.random.RandomGenerator;

import static java.lang.Thread.sleep;


public class Main {

    public static void main(String[] args) {

        Thread hilo = new Thread(new Peluqueria(), "Peluqueria");
        hilo.start();

    }

}

class Peluqueria implements Runnable {

    private static final int NUM_PELUQUEROS = 2;
    private static final int NUM_SILLAS = 5;
    private static final int TIEMPO_OPERACION = 4;

    public Semaphore clientesEsperando = new Semaphore(0);
    private final Semaphore sillasDisponibles = new Semaphore(NUM_SILLAS);
    public Semaphore peluquerosFinalizados = new Semaphore(0);

    public boolean peluqueriaAbierta = true;
    private final ConcurrentLinkedQueue<Integer> colaClientes = new ConcurrentLinkedQueue<>();

    public synchronized boolean ocuparSilla(Integer id){
        if (peluqueriaAbierta){
            if (sillasDisponibles.availablePermits()>0){
                try {
                    sillasDisponibles.acquire();
                    clientesEsperando.release(1);
                    colaClientes.add(id);
                    return true;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        return false;
    }

    public synchronized Integer pelarCabeza(Integer id){
        if (clientesEsperando.availablePermits()>0 && !colaClientes.isEmpty()){
            try {
                clientesEsperando.acquire();
                sillasDisponibles.release();
                return colaClientes.poll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return -1;
    }


    @Override
    public void run() {

        Peluquero peluquero1 = new Peluquero(1,this);
        Thread hilopeluquero1 = new Thread(peluquero1,"1");
        Peluquero peluquero2 = new Peluquero(2,this);
        Thread hilopeluquero2 = new Thread(peluquero2,"2");

        hilopeluquero1.start();
        hilopeluquero2.start();

        System.out.println("La peluquería ha abierto.");
        long inicio = System.currentTimeMillis();
        boolean aviso = false;
        AtomicInteger atomicInteger = new AtomicInteger(0);
        while (true){

            Long tiempo = (System.currentTimeMillis()-inicio)/1000;

            if (tiempo>=TIEMPO_OPERACION) peluqueriaAbierta = false;

            if (!peluqueriaAbierta && sillasDisponibles.availablePermits()==5 && peluquerosFinalizados.availablePermits()==2){
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Todos los peluqueros han terminado. La peluquería está cerrada");
                break;
            }

            if (!aviso && !peluqueriaAbierta){
                System.out.println("Fin de horario de atención al cliente. No se aceptarán más clientes.");
                aviso=true;
            }

            if(peluqueriaAbierta){
                Cliente cliente = new Cliente(atomicInteger.getAndIncrement(),this);
                Thread hilo = new Thread(cliente, String.valueOf(atomicInteger.get()));
                hilo.start();
            }

            try {sleep(1000);} catch (InterruptedException e) {throw new RuntimeException(e);}
        }

    }

}

class Cliente extends Persona implements Runnable{
    private final Peluqueria peluqueria;

    public Cliente(Integer id, Peluqueria peluqueria) {
        super(id);
        this.peluqueria = peluqueria;
    }

    @Override
    public void run() {
        try {
            sleep(RandomGenerator.getDefault().nextInt(1000,2000));} catch (InterruptedException e) {throw new RuntimeException(e);}
        System.out.println("Cliente "+Thread.currentThread().getName()+" ha llegado a la peluquería.");
        boolean sitio = peluqueria.ocuparSilla(Integer.valueOf(Thread.currentThread().getName()));
        if (!sitio) System.out.println("Cliente "+Thread.currentThread().getName()+" no encontró una silla libre y se fue.");
        else System.out.println("Cliente "+Thread.currentThread().getName()+" está esperando en una silla.");
    }
}

class Peluquero extends Persona implements Runnable{
    private final Peluqueria peluqueria;

    public Peluquero(Integer id, Peluqueria peluqueria) {
        super(id);
        this.peluqueria = peluqueria;
    }

    @Override
    public void run() {
        while (true){

            try {sleep(1000);}
            catch (InterruptedException e) {throw new RuntimeException(e);}

            Integer cliente = peluqueria.pelarCabeza(getId());

            if (cliente!=-1){
                System.out.println("Peluquero "+getId()+" está cortando el pelo a "+cliente);

                try {sleep(RandomGenerator.getDefault().nextInt(4000,6000));}
                catch (InterruptedException e) {throw new RuntimeException(e);}

                System.out.println("Peluquero "+getId()+" terminó de cortar el pelo a "+cliente);
            }
            else {

                if (peluqueria.clientesEsperando.availablePermits()==0 && !peluqueria.peluqueriaAbierta){
                    System.out.println("Peluquero "+getId()+" se va. No hay más clientes esperando.");

                    try {peluqueria.peluquerosFinalizados.acquire();}
                    catch (InterruptedException e) {throw new RuntimeException(e);}

                    break;
                }

                System.out.println("Peluquero "+getId()+" está esperando nuevos clientes.");

            }





        }
    }
}

class Persona{
    private Integer id;

    public Persona(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}