package Recursos;

import Procesamiento.Imagen;

import java.util.Random;

import static Recursos.Utilidades.*;

public class Politica {
    private static final RdP rdp = new RdP();

    public Politica(){
    }

    private Integer Politica_1(Integer[] t){
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

    //PRIORIZAR EL SEG IZQ EN LA ETAPA3 {T9, T11}
    private Integer Politica_2(Integer[] t){

        // Generar un número aleatorio entre 0 y 99
        Random random = new Random();
        if (random.nextInt(100) < 80) {
            if (t[9] >= 1) {
                return 9;
            } else if (t[11] >= 1) {
                return 11;
            }
        }
        return Politica_1(t);
    }

    public Integer aplicarPolitica(Integer[] t, int politica) {
        if (politica == 1) {
            return Politica_1(t);
        } else if (politica == 2) {
            return Politica_2(t);
        }
        throw new IllegalArgumentException("POLITCA NO VALIDA");
    }

}
