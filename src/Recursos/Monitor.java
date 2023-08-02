package Recursos;

import java.util.Random;
import java.util.concurrent.*;
import static Recursos.Utilidades.*;


public class Monitor {
    private static final RdP rdp = new RdP();
    private final static Object lock = new Object();
    private static Monitor monitor;
    private static Semaphore Mutex; // Cola de entrada
    private static Semaphore[] ColaCondition; // Representa una cola de condición asociada a cada transición.

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

    public void tomarMutex(){
        try{
            Mutex.acquire();
            System.out.println(Thread.currentThread().getName() + " tomo el mutex");
            //esto significa que entro al monitor.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void liberarMutex() {
        Mutex.release();
        System.out.println(Thread.currentThread().getName() + " libero el mutex");
    }

    //DISPARA Y POR LO TANTO EL ESTADO DE LA RED CAMBIA.
    public void disparar(Integer transicion){
        tomarMutex();
        boolean seDisparo = rdp.Disparar(transicion);
        liberarMutex();
        if(!seDisparo){
            try {
                ColaCondition[transicion].acquire();
            } catch (Exception e) {
                throw new RuntimeException(e + " Error en disparar de monitor");
            }
        }
    }

    //Devuelve los hilos que estan en las colas de condición y que tienen T sens

    public Integer[] transiciones(){
        Integer[] t = new Integer[CANTIDAD_TRANSICIONES];
        for(int i=0; i<CANTIDAD_TRANSICIONES; i++){
            if((ColaCondition[i].getQueueLength()>0) && (rdp.getSens()[i]==1)){
                t[i]=1;
            }else{
                t[i]=0;
            }
        }
        return t;
    }

    /*El método hasQueuedThreads() de Semaphore devuelve true si hay hilos esperando en la cola de espera
    para adquirir el semáforo. Devuelve false si no hay hilos en espera y, por lo tanto, el semáforo está
    disponible para ser adquirido sin poner ningún hilo en espera.
     */
    public boolean LiberarCola(){
        Integer [] Tposibles = transiciones();
        Random random = null; //USAMOS UN RANDOM POR QUE NO ESTA LA POLITICA.
        int indiceAleatorio = random.nextInt(Tposibles.length);
        if(ColaCondition[indiceAleatorio].hasQueuedThreads()){
            ColaCondition[indiceAleatorio].release();
            return true;
        }
        return false;
    }





}
