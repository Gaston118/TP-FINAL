package Recursos;

import static Recursos.Utilidades.*;

public class Politica {
    private static final RdP rdp = new RdP();
    public Politica(){
    }

    public Integer Politica_1(Integer[] t){
        Integer minimo = Integer.MAX_VALUE; //hacemos que el primer valor de rdp.getDisparos()[i] sea menor que minimo
        int posMin = 0;

        for(int i=0; i<CANTIDAD_TRANSICIONES; i++){ //recorro las transiciones 
            if((rdp.getDisparos()[i]<minimo) && t[i]!=0){ //verifico si el valor del disparo es menor que "minimo"
                minimo=rdp.getDisparos()[i];  //si se cumple actualizo el minimo 
                posMin=i;                     //y actualizo la posicion del minimo 
            }
         }
        return posMin;
    }

}
