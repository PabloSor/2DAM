package yo;

import java.util.Random;
import java.util.random.RandomGenerator;

public class Cliente implements Runnable{
    private final int id;
    Peluqueria peluqueria;

    public Cliente(int id, Peluqueria peluqueria) {
        this.id = id;
        this.peluqueria = peluqueria;
    }

    public int getId() {
        return id;
    }

    public Peluqueria getPeluqueria() {
        return peluqueria;
    }

    public void setPeluqueria(Peluqueria peluqueria) {
        this.peluqueria = peluqueria;
    }

    @Override
    public void run() {
        System.out.println("El cliente "+this.getId()+" ha llegado");

        if (peluqueria.isPeluqueriaAbierta()) {

            if (peluqueria.getSillasDisponibles().tryAcquire())
            {
                peluqueria.getColaClientes().add(this.getId());
                System.out.println("----------------El cliente " + this.getId() + " está en una silla");

            } else {
                System.out.println("----------------El cliente " + this.getId() + " no encontro sillas libres y se fue");
            }
        }else {
            System.out.println("La peluquería está cerrada, el cliente "+this.getId()+" no ha podido entrar");
        }
    }
}
