package Recursos;

import static Recursos.Utilidades.*;

public class Politica {
    private static final RdP rdp = new RdP();
    public Politica(){
    }

    public Integer Politica_1(Integer[] t){
        Integer minimo = Integer.MAX_VALUE;
        int posMin = 0;

        for(int i=0; i<CANTIDAD_TRANSICIONES; i++){
            if((rdp.getDisparos()[i]<minimo) && t[i]!=0){
                minimo=rdp.getDisparos()[i];
                posMin=i;
            }
         }
        return posMin;
    }

}
