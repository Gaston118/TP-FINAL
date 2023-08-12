package Procesamiento;

import Recursos.Monitor;
import Recursos.RdP;

public abstract class Imagen {
    public static final Monitor monitor = Monitor.InstanceMonitor();
    private static final RdP rdp = new RdP();
    public Integer[] transiciones;

    public Imagen(){
    }

    public void setTransiciones(Integer[] t){
        this.transiciones=t;
    }

    public void procesar()
    {
        while(!rdp.Fin()){
            for(Integer t : transiciones)
            {
                monitor.disparar(t);
                System.out.println("Se disparo T"+t);
            }
        }
    }

}
