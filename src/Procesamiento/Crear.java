package Procesamiento;

import Recursos.RdP;

public class Crear extends Imagen implements Runnable {

    private static final RdP rdp = new RdP();

    public Crear() {
        Integer[] t = new Integer[]{0};
        setTransiciones(t);
    }

    @Override
    public void run() {
        procesar();
        System.out.println("CREANDO");
    }

    @Override
    public void procesar() {
        while (!rdp.Fin()) {
            for (Integer t : transiciones) {
                monitor.disparar(t);
                System.out.println("Se disparo T" + t);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}