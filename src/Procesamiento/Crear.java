package Procesamiento;

public class Crear extends Imagen implements Runnable{
    public Crear(){
        Integer[] t = new Integer[]{0};
        setTransiciones(t);
    }

    @Override
    public void run() {
        procesar();
        System.out.println("CREANDO");
    }
}
