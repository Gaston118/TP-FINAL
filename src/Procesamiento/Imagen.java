package Procesamiento;

import Recursos.Monitor;

import java.util.concurrent.TimeUnit;


public class Imagen implements Runnable {
    public static final Monitor monitor = Monitor.InstanceMonitor();
    private int contadorT0 = 0;
    private final Integer[] transiciones;

    public Imagen (Integer[] t) {
        this.transiciones = t;
    }

    @Override
    public void run() {
        if (transiciones.length == 1 && transiciones[0] == 0) {
            procesarT0();
        } else {
            procesar();
        }
    }

    public void procesar() {
        while (!monitor.finalizar()) {
            for (Integer t : transiciones) {
                if (!monitor.finalizar()) {
                    monitor.dispararTransicion(t);
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void procesarT0() {
        while (contadorT0 < 200) {
            for (Integer t : transiciones) {
                monitor.dispararTransicion(t);
                contadorT0++;
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void mostrarTra() {
        monitor.mostrarT();
    }

    public void mostrarM() {
        monitor.mostrarMarcado();
    }
}
