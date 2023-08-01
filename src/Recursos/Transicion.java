package Recursos;

//ACA SE VA A HACER EL MANEJO DE TODA LAS TRANSICIONES

import static Recursos.Utilidades.*;

public class Transicion {
    private static Integer[] sensibilizada;
    public static Boolean[] estaEsperando;
    public Transicion(Integer[] transicion){
        sensibilizada=transicion;
        estaEsperando = new Boolean[CANTIDAD_TRANSICIONES];
    }

    public void setSensibilizado(Integer[] nuevaTS) {
        sensibilizada = nuevaTS;
    }

    public Integer[] getSensibilizado() {
        return sensibilizada;
    }

    public boolean esTransicionSensibilizada(int t, Integer[][] matrizIncidencia, Integer[] marcadoActual) {
        for (int plaza = 0; plaza < matrizIncidencia.length; plaza++) {
            if (matrizIncidencia[plaza][t] < 0 && marcadoActual[plaza] < Math.abs(matrizIncidencia[plaza][t])) {
                return false; // La plaza de entrada no tiene suficientes tokens para disparar la transición.
            }
        }
        return true;
    }


}
