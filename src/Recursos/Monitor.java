package Recursos;

import java.util.Random;
import java.util.concurrent.*;
import static Recursos.Utilidades.*;

//El señalizado de las condiciones se hizo con la política de “Signal and Exit”

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

    public void tomarMutex() {
        try {
            Mutex.acquire();
            System.out.println(Thread.currentThread().getName() + " tomo el mutex");
            //esto significa que entro al monitor.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void liberarMutex() {
        boolean releasedMutex = false;
        if (!LiberarCola()) {
            if (Mutex.availablePermits() != 0) {
                System.out.println("Error en el mutex");
                System.exit(1); //Si el semaforo deja de ser binario muere aca
            }
            Mutex.release();
            releasedMutex = true;
            System.out.println(Thread.currentThread().getName() + " libero el mutex");
        }
        if (!releasedMutex) {
            Mutex.release();
        }
    }

    //DISPARA Y POR LO TANTO EL ESTADO DE LA RED CAMBIA.
    public void disparar(Integer transicion) {
        tomarMutex();
        boolean seDisparo = rdp.Disparar(transicion);
        liberarMutex();
        if (!seDisparo) {
            try {
                ColaCondition[transicion].acquire();
            } catch (Exception e) {
                throw new RuntimeException(e + " Error en disparar de monitor");
            }
            disparar(transicion);
        }
    }

    //Devuelve los hilos que estan en las colas de condición y que tienen T sens

    public Integer[] transiciones() {
        Integer[] t = new Integer[CANTIDAD_TRANSICIONES];
        for (int i = 0; i < CANTIDAD_TRANSICIONES; i++) {
            if ((ColaCondition[i].getQueueLength() != 0) && (rdp.getSens()[i] == 1)) {
                t[i] = 1;
            } else {
                t[i] = 0;
            }
        }
        return t;
    }

    /*El método hasQueuedThreads() de Semaphore devuelve true si hay hilos esperando en la cola de espera
    para adquirir el semáforo. Devuelve false si no hay hilos en espera y, por lo tanto, el semáforo está
    disponible para ser adquirido sin poner ningún hilo en espera.
     */

    //PROVISORIAMENTE SE IMPLEMENTO ACA EL QUE ELIJA QUE TRANSICION DISPARAR.

    public boolean LiberarCola() {
        Integer[] transicionesSensibilizadas = transiciones();
        Integer minimo = Integer.MAX_VALUE;
        int posMin = 0;
        for (int i = 1; i < CANTIDAD_TRANSICIONES; i++) {
            if ((rdp.getDisparos()[i]< minimo) && (transicionesSensibilizadas[i] != 0)) {
                minimo = rdp.getDisparos()[i];
                posMin = i;
            }
        }
        if(ColaCondition[posMin].hasQueuedThreads()) {
            ColaCondition[posMin].release();
            return true;
        }
        return false;
    }

}


                                  /* OTRA IMPLEMENTACION */
/***************************************************************************************************************************/
/*import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {
    private static final RdP rdp = new RdP();
    private final static Object lock = new Object();
    private static Monitor monitor;
    private static Lock mutexLock = new ReentrantLock();
    private static boolean signaled = false;
    private static Condition[] conditionArray;

    private Monitor() {
    }
*/
    /*
    public static Monitor InstanceMonitor() {
        synchronized (lock) {
            if (monitor == null) {
                monitor = new Monitor();
                conditionArray = new Condition[CANTIDAD_TRANSICIONES];
                for (int i = 0; i < CANTIDAD_TRANSICIONES; i++) {
                    conditionArray[i] = mutexLock.newCondition();
                }
            } else {
                System.out.println("Ya existe una instancia de monitor");
            }
            return monitor;
        }
    }

    public void tomarMutex(){
        mutexLock.lock();
        System.out.println(Thread.currentThread().getName() + " tomo el mutex");
        // Aquí entra al monitor.
    }

    public void liberarMutex() {
        System.out.println(Thread.currentThread().getName() + " libero el mutex");
        signaled = true;
        for (Condition condition : conditionArray) {
            condition.signalAll();
        }
        mutexLock.unlock();
    }

    // DISPARA Y POR LO TANTO EL ESTADO DE LA RED CAMBIA.
    public void disparar(Integer transicion){
        tomarMutex();
        boolean seDisparo = rdp.Disparar(transicion);
        liberarMutex();
        if (!seDisparo) {
            try {
                mutexLock.lock();
                while (!signaled) {
                    conditionArray[transicion].await();
                }
                signaled = false;
                mutexLock.unlock();
            } catch (InterruptedException e) {
                throw new RuntimeException(e + " Error en disparar de monitor");
            }
        }
    }

    // Devuelve los hilos que están en las colas de condición y que tienen T sens.
    public Integer[] transiciones(){
        Integer[] t = new Integer[CANTIDAD_TRANSICIONES];
        for(int i=0; i<CANTIDAD_TRANSICIONES; i++){
            if((rdp.getSens()[i]==1)){
                mutexLock.lock();
                t[i] = conditionArray[i].hasWaiters() ? 1 : 0;
                mutexLock.unlock();
            } else {
                t[i] = 0;
            }
        }
        return t;
    }
}
*/
