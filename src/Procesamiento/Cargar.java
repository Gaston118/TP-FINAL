package Procesamiento;

public class Cargar extends Imagen implements Runnable{

    public Cargar(){
        Integer[] t = new Integer[]{1,2,3,4};
        setTransiciones(t);
    }

    @Override
    public void run() {
        procesar();
        System.out.println("CARGANDO");
    }
}
