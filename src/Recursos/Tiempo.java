package Recursos;
import static Recursos.Utilidades.*;

public class Tiempo {
    private Integer[] alfa;
    private Integer[] beta;
    private static Integer[] sensibilizada;

    public Tiempo(Integer[] transiciones){
        alfa = new Integer[TTemp.length];
        beta = new Integer[TTemp.length];
        sensibilizada = transiciones;
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
        if(sensibilizada[disparo]>=1){
            if(!esTemporizada(disparo)){
                System.out.println("No es temp");
                return true;
            }
            return testVentanaTiempo(disparo);
        }
        return false;
    }

    //ACA VA LO RESPECTIVO A LA VERIFICACION DEL TIEMPO.
    private boolean testVentanaTiempo(Integer disparo)
    {
        return true;
    }

    public void setSensibilizado(Integer[] nuevaTS) {
        sensibilizada = nuevaTS;
    }

    public Integer[] getSensibilizada() {
        return sensibilizada;
    }

}
