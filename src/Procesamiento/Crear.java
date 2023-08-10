package Procesamiento;

public class Crear extends Imagen implements Runnable{
    public Crear(){
        Integer[] t = new Integer[]{0};
        setTransiciones(t);
    }

    @Override
    public void run() {
        procesar();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
