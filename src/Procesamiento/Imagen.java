package Procesamiento;

import Recursos.Monitor;
import Recursos.Tiempo;

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
        }
        else {
            procesar();
        }
    }

    private void procesar() {
        while (!monitor.finalizar()) {
            for (Integer t : transiciones) {
                if (!monitor.finalizar()) {
                    monitor.dispararTransicion(t);
                }
            }
        }
    }

    private void procesarT0() {
        while (contadorT0 < 200) {
            for (Integer t : transiciones) {
                monitor.dispararTransicion(t);
                contadorT0++;
            }
        }
    }

    public void mostrarTra() {
        monitor.mostrarT();
    }

}
