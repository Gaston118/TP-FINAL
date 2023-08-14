package Recursos;

import static Recursos.Utilidades.*;

public class Politica {
    private static final RdP rdp = new RdP();
    public Politica(){
    }

    public Integer Politica_1(Integer[] t){
        int minDisparos = Integer.MAX_VALUE;
        int selectedTransition = 0;

        for (int i = 0; i < t.length; i++) {
            if (t[i] == 1 && rdp.getDisparos()[i] < minDisparos) {
                minDisparos = rdp.getDisparos()[i];
                selectedTransition = i;
            }
        }

        return selectedTransition;
    }

}
