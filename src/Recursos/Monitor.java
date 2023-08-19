package Recursos;

import java.util.concurrent.*;
import static Recursos.Utilidades.*;

//El señalizado de las condiciones se hizo con la política de “Signal and Exit”

public class Monitor {
    private static final RdP rdp = new RdP();
    private final static Object lock = new Object();
    private static Monitor monitor;
    private static Semaphore Mutex; // Cola de entrada
    private static Semaphore[] ColaCondition; // Representa una cola de condición asociada a cada transición.
    private static final Politica politica = new Politica();

    private Monitor() {
    }

    /*ESTO LO HACEMOS PARA TENER SOLO UNA INSTANCIA DEL MONITOR, USANDO SINGLETON*/

    public static Monitor InstanceMonitor() {
        synchronized (lock) {
            if (monitor == null) {
                monitor = new Monitor();
                Mutex = new Semaphore(1);
                ColaCondition = new Semaphore[CANTIDAD_TRANSICIONES];
                for (int i = 0; i < CANTIDAD_TRANSICIONES; i++)
                    ColaCondition[i] = new Semaphore(0);

            } else {
                System.out.println("Ya existe una instancia de monitor");
            }
            return monitor;
        }
    }

    private void tomarMutex() {
        try {
            Mutex.acquire();
            System.out.println(Thread.currentThread().getName() + " tomo el mutex");
            //esto significa que entro al monitor.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void liberarMutex() {
            if (!LiberarCola()) {
                if (Mutex.availablePermits() != 0) {
                    System.out.println("Error en el mutex");
                    System.exit(1);
                }
                Mutex.release();
                System.out.println(Thread.currentThread().getName() + " libero el mutex");
            }
    }
    public void dispararTransicion(Integer t){
        tomarMutex();
        disparar(t);
    }

    //DISPARA Y POR LO TANTO EL ESTADO DE LA RED CAMBIA.
    private void disparar(Integer transicion) {
        boolean seDisparo = rdp.Disparar(transicion);
        liberarMutex();
        if (!seDisparo && rdp.getDisparos()[14]<200) {
            try {
                //System.out.println("VOY A COLA CONDICION");
                ColaCondition[transicion].acquire();
            } catch (Exception e) {
                throw new RuntimeException(e + " Error en disparar de monitor");
            }
            if(rdp.getDisparos()[14]<200) {
                disparar(transicion);
            }
        }

    }

    //Devuelve los hilos que estan en las colas de condición y que tienen T sens

    private Integer[] transiciones() {
        Integer[] t = new Integer[CANTIDAD_TRANSICIONES];

        for (int i = 0; i < CANTIDAD_TRANSICIONES; i++) {
            if ((ColaCondition[i].getQueueLength() != 0) && (rdp.getSens()[i] == 1)) {
                // Si la cola de condición para esta transición no está vacía y la transición está sensibilizada
                t[i] = 1; // Marcar la transición como sensibilizada
            } else {
                t[i] = 0; // Marcar la transición como no sensibilizada
            }
        }

        return t; // Devuelve el array que indica el estado de sensibilización de cada transición
    }


    /*El método hasQueuedThreads() de Semaphore devuelve true si hay hilos esperando en la cola de espera
    para adquirir el semáforo. Devuelve false si no hay hilos en espera y, por lo tanto, el semáforo está
    disponible para ser adquirido sin poner ningún hilo en espera.
     */


    private boolean LiberarCola() {
        Integer[] transicionesSensibilizadas = transiciones();
        Integer d = politica.Politica_1(transicionesSensibilizadas);
        if(ColaCondition[d].hasQueuedThreads()){
            ColaCondition[d].release();
            //System.out.println("DESPIERTO A T"+d);
            return true;
        }
        return false;
    }

    public boolean finalizar() {
        if (rdp.Fin()) {
            Logger.close();

            //LIBERA LOS RECURSOS

            for (int i=0; i<CANTIDAD_TRANSICIONES; i++){
                if(ColaCondition[i].hasQueuedThreads()){
                    ColaCondition[i].release();
                }
            }

            return true;
        }
        return false;
    }

    public void mostrarTransiciones(){
        rdp.mostrarDisparos();
    }

    public void mostrarMarcado(){
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("------------------------MARCADO FINAL------------------------------------------");
        System.out.println("-------------------------------------------------------------------------------");
        String marcado = rdp.printMarcado();
        System.out.println(marcado);
    }


}
