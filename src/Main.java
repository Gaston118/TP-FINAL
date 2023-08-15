import Procesamiento.*;
import Recursos.Logger;
import Recursos.Monitor;
import Recursos.MyThreadFactory;
import Recursos.RdP;

import java.util.concurrent.Exchanger;

public class Main {
    private static final MyThreadFactory factory = MyThreadFactory.InstanceFactory();
    public static void main(String[] args) {


        Integer [] h1 = {1,3};
        Integer [] h2 = {2,4};
        Integer [] h3 = {5,7};
        Integer [] h4 = {6,8};
        Integer [] h5 = {9,11};
        Integer [] h6 = {10,12};
        Integer [] h7 = {13,14};

        Crear Hilo_0 = new Crear();

        Cargar Hilo_1 = new Cargar(h1);
        Cargar Hilo_2 = new Cargar(h2);

        Mejorar Hilo_3 = new Mejorar(h3);
        Mejorar Hilo_4 = new Mejorar(h4);

        Recortar Hilo_5 = new Recortar(h5);
        Recortar Hilo_6 = new Recortar(h6);

        Exportar Hilo_7 = new Exportar(h7);

        Thread hilo0 = factory.newThread(Hilo_0);
        Thread hilo1 = factory.newThread(Hilo_1);
        Thread hilo2 = factory.newThread(Hilo_2);
        Thread hilo3 = factory.newThread(Hilo_3);
        Thread hilo4 = factory.newThread(Hilo_4);
        Thread hilo5 = factory.newThread(Hilo_5);
        Thread hilo6 = factory.newThread(Hilo_6);
        Thread hilo7 = factory.newThread(Hilo_7);

        Thread hilo5_1 = factory.newThread(Hilo_5); //HILO PARA POLITICA 2


        hilo0.start();
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        hilo5_1.start();
        hilo6.start();
        hilo7.start();


        try {
            hilo0.join();
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();
            hilo5_1.join();
            hilo6.join();
            hilo7.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e + "ERROR EN LOS JOINS");
        }

        Hilo_0.mostrarTra();
        Logger.close();
    }
}