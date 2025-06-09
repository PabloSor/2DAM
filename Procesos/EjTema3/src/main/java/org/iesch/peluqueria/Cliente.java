package org.iesch.peluqueria;

import java.util.random.RandomGenerator;

public class Cliente implements Runnable {

    private int id;
    private Peluqueria peluqueria;

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

    public Cliente(int id, Peluqueria peluqueria) {
        this.id = id;
        this.peluqueria = peluqueria;
    }

    @Override
    public void run() {
        System.out.println("Cliente "+id+" ha llegado a la peluquería");

        if (peluqueria.getSillas().offer(this)){
            System.out.println("Cliente "+id+" está esperando en una silla");
        }else {
            System.out.println("Cliente "+id+" no encontró sillas libres y se fué");
        }

    }
}
