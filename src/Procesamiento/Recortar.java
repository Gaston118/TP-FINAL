package Procesamiento;

public class Recortar extends Imagen implements Runnable{

    public Recortar(){
        Integer[] t = new Integer[]{9,10,11,12};
        setTransiciones(t);
    }


    @Override
    public void run() {
        procesar();
        System.out.println("RECORTANDO");
    }
}
