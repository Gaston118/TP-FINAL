package Recursos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger implements Runnable {

    private static final Object lock = new Object();
    private static FileWriter fileWriter;
    private static Logger logger;
    private static final RdP rdp = new RdP();

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
    public void run(){
        try {
            while(!rdp.Fin()){
                fileWriter.write("T" + rdp.seDisparo());
                fileWriter.flush();
            }
            fileWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
