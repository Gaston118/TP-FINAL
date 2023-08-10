package Recursos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Recursos.Utilidades.*;

public class RdP {
    private int contador=0;
    private static Integer [][] MtzIncidencia;
    private static Integer [] Marcado;
    private static Transicion Tsensibilizada;
    private Integer disparada;

    public RdP(){
        MtzIncidencia = MATRIZ_INCIDENCIA;
        Marcado = MARCADO_INICIAL;
        Integer[] TsensInicial = generarTransicion();
        Tsensibilizada = new Transicion(TsensInicial);
    }

    /* ACA PODRIA HABER UN POSIBLE ERROR - TENER EN CUENTA */
    public static Integer[] generarTransicion(){
        Integer[] nuevaTS = new Integer[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

        //convierto en 0, los punteros a transiciones NO sensibilizadas
        for (int i = 0; i < CANTIDAD_TRANSICIONES; i++) {//busco por cada transición
            for (int j = 0; j < CANTIDAD_PLAZAS; j++) { //si NO esta sensibilizada por sus plazas
                //si al menos le falta un token no esta sensibilizada
                if ((MtzIncidencia[j][i] == -1) && (Marcado[j] < 1)) {
                    nuevaTS[i] = 0;
                    break;
                }
            }
        }

        return nuevaTS;
    }

  public Boolean Disparar(Integer disparo){
        if(Tsensibilizada.esTransicionSensibilizada(disparo)){
            actualizarMarcado(disparo);
            actualizarT();
            if(disparo==14){
                contador++;
                System.out.println("Se completo un invariante.");
            }
            disparada=disparo;
            System.out.println("SE DISPARO");
            return true;
        }
        System.out.println("NO ESTABA SENS");
        actualizarT();
      return false;
  }

  private void actualizarMarcado(Integer disparo){
        Integer[] disparar = new Integer[CANTIDAD_TRANSICIONES];
        Arrays.fill(disparar, 0); //setea todos en 0
        disparar[disparo]=1;
        try{
            Marcado = sumarVectores(Marcado, productoMatricial(MtzIncidencia,disparar));
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException(e + "Error de disparo");
        }
  }

  //Actualizar Transiciones
  public void actualizarT(){
        Integer[] nuevaT = generarTransicion();
        setSens(nuevaT);
        if(!cumpleIP()){
            System.out.println("Se ha violado los invariantes de plaza");
            System.exit(1);
        }
  }

  public void setSens(Integer[] nuevaTS){
        Tsensibilizada.setSensibilizado(nuevaTS);
  }

  public Integer[] getSens(){
      return Tsensibilizada.getSensibilizado();
  }

  private static boolean cumpleIP(){
        boolean ip1,ip2,ip3,ip4,ip5,ip6,ip7,ip8;
        ip1= (Marcado[1]+Marcado[2]==1);
        ip2= (Marcado[4]+Marcado[5]==1);
        ip3= (Marcado[13]+Marcado[14]+Marcado[15]==1);
        ip4= (Marcado[7]+Marcado[8]==1);
        ip5= (Marcado[10]+Marcado[11]==1);
        ip6= (Marcado[9]+Marcado[11]+Marcado[8]==2);
        ip7= (Marcado[18]+Marcado[17]==1);
        ip8= (Marcado[4]+Marcado[3]+Marcado[17]+Marcado[2]==3);

        return (ip1 && ip2 && ip3 && ip4 && ip5 && ip6 && ip7 && ip8);
  }

  public boolean Fin(){
        if(contador==1){
            System.out.println("Se ejecutaron 200 invariantes.");
            return true;
        }
        return false;
  }

  public Integer seDisparo(){
        return disparada;
  }




}

