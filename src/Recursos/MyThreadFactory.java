package Recursos;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {
    private static MyThreadFactory factory; // Instancia única de la fábrica
    private int cont;

    private MyThreadFactory() {
        cont = 0;
    }

    // Método estático para obtener la única instancia de la fábrica
    // Utilizando el patron singleton
    public static synchronized MyThreadFactory InstanceFactory() {
        if (factory == null) {
            factory = new MyThreadFactory();
        }
        else{
         System.out.println("Ya fue creada una vez.");
        }
        return factory;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r,"Thread: " + cont);
        cont++;
        return t;
    }
}

