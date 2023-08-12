package Procesamiento;

public class Exportar extends Imagen implements Runnable{

    public Exportar(){
        Integer[] t = new Integer[]{13,14};
        setTransiciones(t);
    }

    @Override
    public void run() {
        procesar();
        System.out.println("EXPORTANDO");
    }
}
