package Recursos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Recursos.Utilidades.*;

public class RdP {
    private static Integer [][] MtzIncidencia;
    private static Integer [] Marcado;
    private static Transicion Tsensibilizada;

    public RdP(){
        MtzIncidencia = MATRIZ_INCIDENCIA;
        Marcado = MARCADO_INICIAL;
        List<Integer> TsensInicial = generarTransicion();

    }

    /* ACA PODRIA HABER UN POSIBLE ERROR - TENER EN CUENTA */
    public List<Integer> generarTransicion(){
        List<Integer> NuevaT = new ArrayList<>();
        for(int i=0; i<CANTIDAD_TRANSICIONES; i++){
            boolean sensibilizada = true;
            for (int j=0; j<CANTIDAD_PLAZAS; j++){
                if (MtzIncidencia[j][i] < 0 && (Marcado[j] < Math.abs(MtzIncidencia[j][i]))){
                    sensibilizada=false;
                    break;
                }
            }
            if (sensibilizada) {
                NuevaT.add(i);
            }
        }
        return NuevaT;
    }

  public Boolean Disparar(Integer disparo){
        if(Tsensibilizada.esTransicionSensibilizada(disparo, MtzIncidencia, Marcado)){
            actualizarMarcado(disparo);
            actualizarT();
            return true;
        }
        actualizarT();
      return false;
  }

  public void actualizarMarcado(Integer disparo){
        Integer[] disparar = new Integer[CANTIDAD_TRANSICIONES];
        Arrays.fill(disparar, 0); //setea todos en 0
        disparar[disparo]=1;
        try{
            Marcado = sumarVectores(Marcado, productoMatricial(MtzIncidencia,disparar));
        } catch (Exception e) {
            throw new RuntimeException(e + "Error de disparo");
        }
  }

  //Actualizar Transiciones
  public void actualizarT(){
        Integer[] nuevaT = generarTransicion().toArray(new Integer[0]);
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
        ip1= (Marcado[0]+Marcado[1]==1);
        ip2= (Marcado[3]+Marcado[4]==1);
        ip3= (Marcado[12]+Marcado[13]+Marcado[14]==1);
        ip4= (Marcado[6]+Marcado[7]==1);
        ip5= (Marcado[9]+Marcado[10]==1);
        ip6= (Marcado[8]+Marcado[10]+Marcado[7]==2);
        ip7= (Marcado[17]+Marcado[16]==1);
        ip8= (Marcado[3]+Marcado[2]+Marcado[16]+Marcado[1]==3);

        return (ip1 && ip2 && ip3 && ip4 && ip5 && ip6 && ip7 && ip8);
  }







}

