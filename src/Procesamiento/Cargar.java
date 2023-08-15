package Procesamiento;

public class Cargar extends Imagen implements Runnable{

    public Cargar(Integer [] t){
        setTransiciones(t);
    }

    @Override
    public void run() {
        procesar();
    }
}
