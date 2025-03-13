package yo;

import java.util.random.RandomGenerator;

public class Peluquero implements Runnable{
    private final int id;
    Peluqueria peluqueria;

    public Peluquero(int id, Peluqueria peluqueria) {
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

        while (true){
            if (peluqueria.getColaClientes().size() > 0) {
                int idCliente = peluqueria.getColaClientes().poll();

                System.out.println("El peluquero " + this.getId() + " esta cortando el pelo al cliente " + idCliente);

                try {
                    Thread.sleep(RandomGenerator.getDefault().nextInt(4000, 6000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("El peluquero " + this.getId() + " ha terminado de cortar el pelo al cliente " + idCliente);
                peluqueria.getSillasDisponibles().release();
            }else {
                if (peluqueria.getColaClientes().size() == 0 && !peluqueria.isPeluqueriaAbierta()){
                    break;
                }else {
                    System.out.println("------------------El peluquero "+this.getId()+" está esperando clientes");
                    try {Thread.sleep(1000);} catch (InterruptedException e) {throw new RuntimeException(e);}
                }
            }


        }
        System.out.println("----------------------------El peluquero "+this.getId()+" ha terminado y se va a casa");
    }
}
