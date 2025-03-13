package yo;

public class Main {
    public static void main(String[] args) {
        Peluqueria peluqueria = new Peluqueria();

        Thread thread = new Thread(peluqueria);
        thread.start();
    }
}
