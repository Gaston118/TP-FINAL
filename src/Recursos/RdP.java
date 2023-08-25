package Recursos;

import java.util.Arrays;
import java.util.StringJoiner;

import static Recursos.Utilidades.*;

public class RdP {
    private static Integer [][] MtzIncidencia;
    public static Integer [] Marcado;
    private static Tiempo TsensA;
    private Integer[] Disparos = new Integer[CANTIDAD_TRANSICIONES];
    private static Long[] timeStamp;

    public RdP(){
        MtzIncidencia = MATRIZ_INCIDENCIA;
        Marcado = MARCADO_INICIAL;
        Integer [] TsensI = generarTransicion();
        TsensA = new Tiempo(TsensI);
        Arrays.fill(Disparos, 0);
        timeStamp = new Long[CANTIDAD_TRANSICIONES];
        for(int i=0; i<CANTIDAD_TRANSICIONES; i++){
            timeStamp[i] = System.currentTimeMillis();
        }
    }

    public static Integer[] generarTransicion(){
        Integer[] nuevaTS = new Integer[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

        for (int i = 0; i < CANTIDAD_TRANSICIONES; i++) {
            for (int j = 0; j < CANTIDAD_PLAZAS; j++) {
                if ((MtzIncidencia[j][i] == -1) && (Marcado[j] < 1)) {
                    nuevaTS[i] = 0;
                    break;
                }
            }
        }
        return nuevaTS;
    }

  public Boolean Disparar(Integer disparo){
      if(TsensA.estaSensibilizado(disparo)) {
          actualizarMarcado(disparo);
          actualizarT();
          actualizarDisparos(disparo);
          Logger.logTransition(disparo);
          System.out.println("T" + disparo);
          return true;
      }
      System.out.println("NO ESTABA SENS LA T"+disparo);
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
      if(!cumpleIP()){
          System.out.println("FALLA EN LOS INVARIANTES DE PLAZA");
          System.exit(1);
      }
  }

  //Actualizar Transiciones
  public void actualizarT(){
        Integer[] nuevaT = generarTransicion();
        setSens(nuevaT);
        setTimeStamp(nuevaT);
  }

  public void setSens(Integer[] nuevaTS){
        TsensA.setSensibilizado(nuevaTS);
  }

  public Integer[] getSens(){
        return TsensA.getSensibilizada();
  }

  private static boolean cumpleIP(){
      boolean[] invariantes = new boolean[] {
              Marcado[1] + Marcado[2] == 1,
              Marcado[4] + Marcado[5] == 1,
              Marcado[13] + Marcado[14] + Marcado[15] == 1,
              Marcado[7] + Marcado[8] == 1,
              Marcado[10] + Marcado[11] == 1,
              Marcado[9] + Marcado[11] + Marcado[8] == 2,
              Marcado[18] + Marcado[17] == 1,
              Marcado[4] + Marcado[3] + Marcado[17] + Marcado[2] == 3
      };

      for (boolean condicion : invariantes) {
          if (!condicion) {
              return false;
          }
      }

      return true;
  }

  public boolean Fin(){
        if(getDisparos()[14]>=200){
            return true;
        }
        return false;
  }

    public Integer[] getDisparos() {
        return Disparos;
    }

    private void actualizarDisparos(Integer disparo) {
        Disparos[disparo]++;
    }

    public void mostrarDisparos() {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("------------------- CANTIDAD DE TRANSICIONES -----------------------------");
        System.out.println("--------------------------------------------------------------------------");

        for (int i = 0; i < Disparos.length; i++) {
            System.out.println("T" + i + ": " + Disparos[i]);
        }
    }

    public String printMarcado() {
        StringJoiner joiner = new StringJoiner(",", "[", "]\n");
        for (int valor : Marcado) {
            joiner.add(String.valueOf(valor));
        }
        return joiner.toString();
    }

    public static Long[] getTimestamp(){
        return timeStamp;
    }

    public static void setTimeStamp(Integer[] nuevaT){
        for(int i = 0; i < CANTIDAD_TRANSICIONES; i++){
            //VERIFICA QUE NO SEA EL MISMO
            if(!nuevaT[i].equals(TsensA.getSensibilizada()[i])){
                timeStamp[i] = System.currentTimeMillis();
            }
        }
    }




}

