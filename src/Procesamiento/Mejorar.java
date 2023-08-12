package Procesamiento;

public class Mejorar extends Imagen implements Runnable{

    public Mejorar(){
        Integer[] t = new Integer[]{5,6,7,8};
        setTransiciones(t);
    }

    @Override
    public void run() {
        procesar();
        System.out.println("MEJORANDO");
    }
}
