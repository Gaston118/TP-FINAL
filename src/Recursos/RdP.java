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
        Arrays.fill(disparar, 0);
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
  }

  public void setSens(Integer[] nuevaTS){
        Tsensibilizada.setSensibilizado(nuevaTS);
  }

  public Integer[] getSens(){
      return Tsensibilizada.getSensibilizado();
  }









}

