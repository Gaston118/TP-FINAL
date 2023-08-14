package Procesamiento;

import Recursos.Logger;
import Recursos.Monitor;
import Recursos.RdP;

public abstract class Imagen {
    public static final Monitor monitor = Monitor.InstanceMonitor();
    public Integer[] transiciones;

    public Imagen(){
    }

    public void setTransiciones(Integer[] t){
        this.transiciones=t;
    }

    public void procesar()
    {
        while(!monitor.finalizar()){
            for(Integer t : transiciones)
            {
                monitor.dispararTransicion(t);
                System.out.println("T"+t);
            }
        }
    }

    public void mostrarTra(){
        monitor.mostrarT();
    }

}
