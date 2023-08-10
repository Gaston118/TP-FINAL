import Recursos.Monitor;
import Recursos.MyThreadFactory;

public class Main {

    private static final MyThreadFactory factory = MyThreadFactory.InstanceFactory();
    //public static final Logger logger = Logger.InstanceLogger();

    public static void main(String[] args) {
        Crear crearr = new Crear();
        Cargar cargarr = new Cargar();
        Mejorar mejorarr = new Mejorar();
        Recortar recortarr = new Recortar();
        Exportar exportarr = new Exportar();

        //Thread log = factory.newThread(logger);


        Thread crear = factory.newThread(crearr);
        Thread cargar = factory.newThread(cargarr);
        Thread cargar2 = factory.newThread(cargarr);
        Thread mejorar = factory.newThread(mejorarr);
        Thread mejorar2 = factory.newThread(mejorarr);
        Thread recortar = factory.newThread(recortarr);
        Thread recortar2 = factory.newThread(recortarr);
        Thread exportar = factory.newThread(exportarr);

        crear.start();
        cargar.start();
        cargar2.start();
        mejorar.start();
        mejorar2.start();
        recortar.start();
        recortar2.start();
        exportar.start();
        //log.start();
    }
}