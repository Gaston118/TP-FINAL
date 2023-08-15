package Recursos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Exchanger;

public class Logger{
    private static final String LOG_FILE = "transitions.txt";
    private static FileWriter fileWriter;

    static {
        try {
            fileWriter = new FileWriter(LOG_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logTransition(int transition) {
        try {
            fileWriter.write("T" + transition + " " + System.lineSeparator());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
