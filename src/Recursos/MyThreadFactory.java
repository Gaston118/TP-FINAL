package Recursos;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {
    private int cont;

    public MyThreadFactory() {
        cont = 0;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r,"Thread: " + cont);
        cont++;
        return t;
    }
}

