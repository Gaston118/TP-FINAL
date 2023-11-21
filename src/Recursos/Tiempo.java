package Recursos;

import Procesamiento.Imagen;

import java.util.Scanner;

import static Recursos.Utilidades.*;

public class Tiempo {
    private static Integer[] alfa;
    private static Integer[] beta;
    private static Integer[] sensibilizada;
    private static Boolean[] Esperando;

    public Tiempo(Integer[] transiciones){
        alfa = new Integer[CANTIDAD_TRANSICIONES];
        beta = new Integer[CANTIDAD_TRANSICIONES];
        Esperando = new Boolean[CANTIDAD_TRANSICIONES];
        sensibilizada = transiciones;
        setTiempos();
        for(int i=0; i<CANTIDAD_TRANSICIONES; i++)
        {
            Esperando[i]=false;
        }
    }

    private boolean esTemporizada(Integer disparo){
        for(Integer t : TTemp){
            if(t.equals(disparo)){
                return true;
            }
        }
        return false;
    }

    public boolean estaSensibilizado(Integer disparo){
        if(sensibilizada[disparo]>=1 && !Esperando[disparo]){
            if(!esTemporizada(disparo)){
                //System.out.println("No es temporizada");
                return true;
            }
            return testVentanaTiempo(disparo);
        }
        return false;
    }

    public void setSensibilizado(Integer[] nuevaTS) {
        sensibilizada = nuevaTS;
    }

    public Integer[] getSensibilizada() {
        return sensibilizada;
    }

    //VERIFICACION DEL TIEMPO.
    private boolean testVentanaTiempo(Integer disparo) {
        Long[] timeStamp = RdP.getTimestamp();
        long tiempoAct = System.currentTimeMillis();
        int a = alfa[disparo];
        long tiempoMin = timeStamp[disparo] + alfa[disparo];
        boolean antesDeAlfa = (tiempoAct - timeStamp[disparo]) < alfa[disparo];
        if ((tiempoAct - timeStamp[disparo] >= alfa[disparo]) && (tiempoAct - timeStamp[disparo] < beta[disparo])) {
            return true;
        }
        antesDeLaVentana(antesDeAlfa, tiempoAct, tiempoMin, disparo);
        //SI SALGO DE DORMIR Y ESTA SENS
        if(sensibilizada[disparo]>=1){
            return true;
        }
        return false;
    }


    //SETEAMOS EL TIEMPO SOLO PARA LAS TEMPORALES
    private void setTiempos(){
        for(int i = 0; i < CANTIDAD_TRANSICIONES; i++) {
            if (esTemporizada(i)){
                alfa[i] = 10;
                beta[i] = 10000000;
            }
        }
    }
    public static Integer getTiempo(Integer d){
        return alfa[d];
    }

    //SI ESTA ANTES DEL ALFA, DEJA EL MUTEX Y SE VA A DORMIR
    private void antesDeLaVentana(boolean antesA, long tiempoAct, long tiempoMin, Integer disparo) {
        if (antesA) {
            Esperando[disparo]=true;
            System.out.println("T" + disparo + " se va a dormir");
            Imagen.monitor.liberarMutex();
            long aDormir = tiempoMin - tiempoAct;
            try {
                Thread.sleep(aDormir);
                Imagen.monitor.getMutex().acquire();
                Esperando[disparo]=false;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
