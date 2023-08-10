package Recursos;

//ACA SE VA A HACER EL MANEJO DE TODA LAS TRANSICIONES

import static Recursos.Utilidades.*;

public class Transicion {
    private static Integer[] sensibilizada;
    public static Boolean[] estaEsperando;
    public Transicion(Integer[] transicion){
        sensibilizada=transicion;
        estaEsperando = new Boolean[CANTIDAD_TRANSICIONES]; //ESTO SE UTILIZA PARA LA SEMANTICA TEMPORAL
    }


    //------------------------------------POSIBLE ERROR-----------------------------------------------------

    public void setSensibilizado(Integer[] nuevaTS) {
        sensibilizada = nuevaTS;
    }

    public Integer[] getSensibilizado() {
        return sensibilizada;
    }

    public boolean esTransicionSensibilizada(Integer disparo) {
        if(sensibilizada[disparo]>0){
            return true;
        }
        return false;
    }


}
