package Procesamiento;

import Recursos.Monitor;
import Recursos.RdP;

public abstract class Imagen {
    public static final Monitor monitor = Monitor.InstanceMonitor();
    private int contadorT0=0;

    public Integer[] transiciones;

    public Imagen(){
    }

    public void setTransiciones(Integer[] t){
        this.transiciones=t;
    }

    public void procesar()
    {
        while(!monitor.finalizar()){
            for(Integer t : transiciones) {
                if (!monitor.finalizar()) {
                    monitor.dispararTransicion(t);
                    //System.out.println("Se disparo T" + t);
                }
            }
        }
    }

    public void procesarT0()
    {
        while(contadorT0<200){
            for(Integer t : transiciones)
            {
                monitor.dispararTransicion(t);
                //System.out.println("Se disparo T"+t);
                contadorT0++;
            }
        }
    }



    public void mostrarTra(){
        monitor.mostrarT();
    }

    public void mostrarM(){
        monitor.mostrarMarcado();
    }

}
