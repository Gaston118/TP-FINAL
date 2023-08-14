package Recursos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static Recursos.RdP.seDisparo;


public class Logger implements Runnable {

    private static final Object lock = new Object();
    private static FileWriter fileWriter;
    private static Logger logger;
    public static boolean finalizar = false;

    public static Logger InstanceLogger() {
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
    public void run() {
        try {
            while (!finalizar) {
                fileWriter.write("T" + seDisparo());
                fileWriter.flush();
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
