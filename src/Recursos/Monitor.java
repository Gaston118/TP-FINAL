package Recursos;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;
import static Recursos.Utilidades.*;

//El señalizado de las condiciones se hizo con la política de “Signal and Exit”

public class Monitor {
    private static final RdP rdp = new RdP();
    private static Monitor monitor;
    private static Semaphore Mutex; // Cola de entrada
    private static Semaphore[] ColaCondition; // Representa una cola de condición asociada a cada transición.
    private static final Politica politica = new Politica();
    private static int entradaUsuario;

    private Monitor() {
    }

    /*ESTO LO HACEMOS PARA TENER SOLO UNA INSTANCIA DEL MONITOR, USANDO SINGLETON*/

    public synchronized static Monitor InstanceMonitor() {
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
                System.out.println("ERROR EN EL MUTEX");
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
        if (!seDisparo && !rdp.Fin()) {
            try {
                //System.out.println("VOY A COLA CONDICION");
                ColaCondition[transicion].acquire();
            } catch (Exception e) {
                throw new RuntimeException(e + " ERROR EN DISPARAR DE MONITOR");
            }
            if (!rdp.Fin()) {
                disparar(transicion);
            }
        }

    }

    //asQueuedThreads() verifica si hay hilos en espera en la cola del sincronizador específico y devuelve un booleano.
    //getQueueLength() devuelve la cantidad de hilos en espera en la cola del sincronizador específico como un número entero.

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


    private boolean LiberarCola() {
        Integer[] transicionesSensibilizadas = transiciones();
        Integer d = politica.aplicarPolitica(transicionesSensibilizadas, getEntradaUsuario());
        if(ColaCondition[d].hasQueuedThreads()){
            ColaCondition[d].release();
            System.out.println("Despierto a T"+d);
            return true;
        }
        return false;
    }

    public boolean finalizar(){
        if(rdp.Fin()){
            Logger.close();
            for(int i=0; i<CANTIDAD_TRANSICIONES; i++){
                if(ColaCondition[i].hasQueuedThreads()){
                    ColaCondition[i].release();
                }
            }
            return true;
        }
        return false;
    }

    public void mostrarT(){
        rdp.mostrarDisparos();
    }

    public void mostrarMarcado(){
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("------------------------MARCADO FINAL------------------------------------------");
        System.out.println("-------------------------------------------------------------------------------");
        String marcado = rdp.printMarcado();
        System.out.println(marcado);
    }

    public Semaphore getMutex() {
        return Mutex;
    }

    public static void selecPolitica(){
        Scanner scanner = new Scanner(System.in);
        int entrada;
        while (true) {
            System.out.print("Por favor, selecciona la politica: 1/2 ");
            entrada = Integer.parseInt(scanner.nextLine());
            if(entrada==1 || entrada==2){
                break;
            }
            System.out.println("Politica no valida");
        }
        setEntradaUsuario(entrada);
        scanner.close();
    }

    public static int getEntradaUsuario() {
        return entradaUsuario;
    }

    private static void setEntradaUsuario(int entradaUsuario) {
        Monitor.entradaUsuario = entradaUsuario;
    }
}
