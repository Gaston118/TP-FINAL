package Procesamiento;

import Recursos.RdP;

public class Crear extends Imagen implements Runnable {

    public Crear() {
        Integer[] t = new Integer[]{0};
        setTransiciones(t);
    }

    @Override
    public void run() {
            procesarT0();
    }
}