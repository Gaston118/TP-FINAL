package Procesamiento;

import Recursos.Logger;
import Recursos.Monitor;

public abstract class Imagen {
    public static final Monitor monitor = Monitor.InstanceMonitor();
    public Integer[] transiciones;
    private int contadorT0=0;

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
                    //System.out.println("T" + t);
                }
            }
        }
    }

    public void procesarT0(){
        while(contadorT0<200){
            for(Integer t : transiciones)
            {
                monitor.dispararTransicion(t);
                //System.out.println("T"+t);
                contadorT0++;
            }
        }

    }

    public void mostrarT(){
        monitor.mostrarTransiciones();
    }
    public void mostrarM(){
         monitor.mostrarMarcado();
    }

}
