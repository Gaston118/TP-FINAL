package Recursos;

import java.util.*;

import static Recursos.Utilidades.*;

public class RdP {
    private static Integer [][] MtzIncidencia;
    public static Integer [] Marcado;
    private static Tiempo TsensA;
    private final Integer[] Disparos;
    private static Long[] timeStamp;

    public RdP(){
        MtzIncidencia = getMatriz();
        Marcado = MARCADO_INICIAL;
        Disparos = new Integer[CANTIDAD_TRANSICIONES];
        Integer [] TsensI = TransicionSens();
        TsensA = new Tiempo(TsensI);
        Arrays.fill(Disparos, 0);
        timeStamp = new Long[CANTIDAD_TRANSICIONES];
        for(int i=0; i<CANTIDAD_TRANSICIONES; i++){
            timeStamp[i] = System.currentTimeMillis();
        }
    }

    public static Integer[] TransicionSens(){
        Integer[] nuevaTS = new Integer[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        //HAGO 0 LAS T NO SENS
        for (int i = 0; i < CANTIDAD_TRANSICIONES; i++) { //BUSCO POR T
            for (int j = 0; j < CANTIDAD_PLAZAS; j++) { //BUSCO POR P
                //MtzIncidencia[j][i] == -1 DE ENTRADA : CONSUME TOKENS
                if ((MtzIncidencia[j][i] == -1) && (Marcado[j] < 1)) { //SI LE FALTA AL MENOS 1 TOKEN
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
      System.out.println("T"+disparo + " no sensibilizada");
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
            throw new RuntimeException(e + "ERROR EN ACTUALIZAR MARCADO");
        }
      if(!cumpleIP()){
          System.out.println("FALLA EN LOS INVARIANTES DE PLAZA");
          System.exit(1);
      }
  }

  //Actualizar Transiciones
  public void actualizarT(){
        Integer[] nuevaT = TransicionSens();
        setTimeStamp(nuevaT);
        setSens(nuevaT);
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
      return getDisparos()[14] >= 200;
  }

    public Integer[] getDisparos() {
        return Disparos;
    }

    private void actualizarDisparos(Integer disparo) {
        Disparos[disparo]++;
    }

    public void mostrarDisparos() {
        System.out.println();
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("------------------- CANTIDAD DE TRANSICIONES -----------------------------");
        System.out.println("--------------------------------------------------------------------------");

        for (int i = 0; i < Disparos.length; i++) {
            System.out.println("T" + i + ": " + Disparos[i]);
        }
        System.out.println();
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
        for (int i = 0; i < CANTIDAD_TRANSICIONES; i++) {
            if (!nuevaT[i].equals(TsensA.getSensibilizada()[i])) {
                System.out.println("Cambio el timeStamp de T" + i);
                timeStamp[i] = System.currentTimeMillis();
            }
        }
    }

    public void getIT() {
        Integer[] resultado = new Integer[IT.length];
        Integer[] copiaD = Arrays.copyOf(getDisparos(), getDisparos().length);
        Arrays.fill(resultado, 0);

        while (copiaD[14]>0) {

            for (int secuenciaIndex = 0; secuenciaIndex < IT.length; secuenciaIndex++) {

                Integer[] secuenciaActual = IT[secuenciaIndex];
                boolean completo = true;

                for (Integer transicion : secuenciaActual) {
                    if (copiaD[transicion] == 0) {
                       completo=false;
                    }
                }
                if (completo) {
                    for (Integer t : secuenciaActual) {
                        copiaD[t]--;
                    }
                    resultado[secuenciaIndex]++;
                }
            }
        }

            for (int i = 0; i < resultado.length; i++) {
                System.out.println("IT(" + (i + 1) + "): " + Arrays.toString(IT[i]) + " = " + resultado[i]);
            }
            System.out.println();

        int sumaTotal = 0;
        for (Integer valor : resultado) {
            sumaTotal += valor;
        }

        System.out.println("TOTAL IT'S: " + sumaTotal);
        System.out.println();
    }

}

