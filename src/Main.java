import Procesamiento.*;

import Recursos.MyThreadFactory;

public class Main {

    public static void main(String[] args) {
        MyThreadFactory factory = new MyThreadFactory();

        Integer [] h0 = {0};
        Integer [] h1 = {1,3};
        Integer [] h2 = {2,4};
        Integer [] h3 = {5,7};
        Integer [] h4 = {6,8};
        Integer [] h5 = {9,11};
        Integer [] h6 = {10,12};
        Integer [] h7 = {13,14};

        Imagen Crear = new Imagen(h0);

        Imagen Cargar = new Imagen(h1);
        Imagen Cargar2 = new Imagen(h2);

        Imagen Mejorar = new Imagen(h3);
        Imagen Mejorar2 = new Imagen(h4);

        Imagen Recortar = new Imagen(h5);
        Imagen Recortar2 = new Imagen(h6);

        Imagen Exportar = new Imagen(h7);

        Thread hilo0 = factory.newThread(Crear);
        Thread hilo1 = factory.newThread(Cargar);
        Thread hilo2 = factory.newThread(Cargar2);
        Thread hilo3 = factory.newThread(Mejorar);
        Thread hilo4 = factory.newThread(Mejorar2);
        Thread hilo5 = factory.newThread(Recortar);
        Thread hilo6 = factory.newThread(Recortar2);
        Thread hilo7 = factory.newThread(Exportar);

        //Thread hilo5_1 = factory.newThread(Recortar);

        hilo0.start();
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        hilo6.start();
        hilo7.start();

        //hilo5_1.start();

        try {
            hilo0.join();
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();
            hilo6.join();
            hilo7.join();

            //hilo5_1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Crear.mostrarTra();
        Crear.mostrarM();
    }
}