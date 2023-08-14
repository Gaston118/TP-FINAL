package Recursos;

import Procesamiento.Imagen;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Exchanger;


public class Logger implements Runnable {

    private static final Object lock = new Object();
    private static FileWriter fileWriter;
    private static Logger logger;
    private static final RdP rdp = new RdP();


    public static final Exchanger<Integer> exchangerLogger = new Exchanger<>();

    private Logger() { }

    public static Logger getInstanceOfLogger() {
        synchronized (lock) {
            if(logger == null) {
                try {
                    logger = new Logger();
                    fileWriter = new FileWriter("log.txt", false);
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("Ya se ha creado una instancia de un log.txt");
            }
            return logger;
        }
    }

    @Override
    public void run(){
        try {
            while(rdp.Fin()){
                Integer disparos = exchangerLogger.exchange(null);
                fileWriter.write("T" + disparos + "  ");
                fileWriter.flush();
            }
            fileWriter.close();
        }catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
