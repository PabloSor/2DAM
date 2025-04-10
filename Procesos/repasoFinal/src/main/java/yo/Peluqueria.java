package yo;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.random.RandomGenerator;

class Peluqueria implements Runnable{
    // Parámetros de la peluquería
    private static final int NUM_PELUQUEROS = 2;
    private static final int NUM_SILLAS = 5;
    private static final int TIEMPO_OPERACION = 30;

    private Semaphore clientesEsperando = new Semaphore(0);
    private Semaphore sillasDisponibles = new Semaphore(NUM_SILLAS);
    private Semaphore peluquerosFinalizados = new Semaphore(NUM_PELUQUEROS);

    private boolean peluqueriaAbierta = true;
    private ConcurrentLinkedQueue<Integer> colaClientes = new ConcurrentLinkedQueue<>();

    public Semaphore getClientesEsperando() {
        return clientesEsperando;
    }

    public void setClientesEsperando(Semaphore clientesEsperando) {
        this.clientesEsperando = clientesEsperando;
    }

    public Semaphore getSillasDisponibles() {
        return sillasDisponibles;
    }

    public void setSillasDisponibles(Semaphore sillasDisponibles) {
        this.sillasDisponibles = sillasDisponibles;
    }

    public Semaphore getPeluquerosFinalizados() {
        return peluquerosFinalizados;
    }

    public void setPeluquerosFinalizados(Semaphore peluquerosFinalizados) {
        this.peluquerosFinalizados = peluquerosFinalizados;
    }

    public boolean isPeluqueriaAbierta() {
        return peluqueriaAbierta;
    }

    public void setPeluqueriaAbierta(boolean peluqueriaAbierta) {
        this.peluqueriaAbierta = peluqueriaAbierta;
    }

    public ConcurrentLinkedQueue<Integer> getColaClientes() {
        return colaClientes;
    }

    public void setColaClientes(ConcurrentLinkedQueue<Integer> colaClientes) {
        this.colaClientes = colaClientes;
    }



    public void iniciarPeluqueros(){

        for (int i = 0; i < NUM_PELUQUEROS; i++) {
            Peluquero peluquero = new Peluquero(i+1, this);

            Thread thread = new Thread(peluquero);
            thread.start();
        }
    }

    @Override
    public void run() {
        long mometoInicio = System.currentTimeMillis();
        iniciarPeluqueros();

        int idCliente = 0;
        while (peluqueriaAbierta){
            idCliente ++;

            Cliente cliente = new Cliente(idCliente, this);
            Thread thread = new Thread(cliente);
            thread.start();

            try {
                Thread.sleep(RandomGenerator.getDefault().nextInt(1000, 2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (System.currentTimeMillis() - mometoInicio > TIEMPO_OPERACION*1000){
                this.setPeluqueriaAbierta(false);
                System.out.println("-------------------------------------La peluquería ha dejado de admitir clientes");
            }
        }

    }
}
