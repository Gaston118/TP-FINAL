package Recursos;/* CLASE EN DONDE VAMOS A PONER COSAS QUE NOS HACEN FALTA A LO LARGO DEL PROYECTO*/

public class Utilidades {
    private Utilidades() {}

    //Calcula el producto punto pero solo para los valores de plazas y transiciones definidos
    public static Integer[] productoMatricial(Integer[][] matriz, Integer[] vector) {
        Integer[] productoMatricial = new Integer[CANTIDAD_PLAZAS];
        //plazas filas de la matriz, transiciones columnas
        for (int i = 0; i < CANTIDAD_PLAZAS; i++) {
            int aux = 0;
            for (int j = 0; j < CANTIDAD_TRANSICIONES; j++) {
                aux += matriz[i][j] * vector[j];
            }
            productoMatricial[i] = aux;
        }
        return productoMatricial;
    }


      //Suma vectorial estandar

    public static Integer[] sumarVectores(Integer[] vector1, Integer[] vector2) {
        Integer[] resultante = new Integer[CANTIDAD_PLAZAS];
        for (int i = 0; i < CANTIDAD_PLAZAS; i++) {
            resultante[i] = vector1[i] + vector2[i];
        }

        return resultante;
    }

    public static final Integer[][] MATRIZ_INCIDENCIA = new Integer[][]{
            {1,	-1,	0,	0,	0,	0,	0,	-1,	0,	0,	0,	0,	0,	0,	0},
            {0,	-1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0},
            {0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	-1,	0,	1,	0},
            {0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	-1,	0},
            {0,	0,	-1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	1,	-1},
            {0,	0,	0,	-1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1},
            {0,	0,	-1,	1,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	-1},
            {0,	0,	1,	0,	-1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
            {0,	0,	0,	1,	1,	-1,	0,	0,	0,	0,	0,	0,	0,	0,	0},
            {0,	0,	0,	0,	0,	1,	-1,	0,	0,	0,	0,	0,	0,	0,	0},
            {0,	0,	0,	0,	0,	-1,	1,	0,	0,	0,	0,	0,	0,	0,	0},
            {0,	1,	0,	0,	0,	0,	0,	0,	-1,	0,	0,	0,	0,	0,	0},
            {0,	-1,	0,	0,	0,	-1,	1,	-1,	1,	1,	0,	0,	0,	0,	0},
            {0,	0,	0,	0,	0,	0,	0,	1,	0,	-1,	0,	0,	0,	0,	0},
            {0,	0,	0,	0,	0,	0,	0,	-1,	0,	1,	0,	0,	0,	0,	0},
            {0,	0,	0,	0,	0,	0,	0,	0,	1,	1,	-1,	-1,	0,	0,	0},
            {0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	-1,	0,	1,	0,	0},
            {0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	-1,	0,	0},
            {0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	-1,	-1,	1,	1,	0}
    };

    public static final Integer CANTIDAD_PLAZAS = MATRIZ_INCIDENCIA.length;
    public static final Integer CANTIDAD_TRANSICIONES = MATRIZ_INCIDENCIA[0].length;
    public static final Integer[] MARCADO_INICIAL = {0, 1, 0, 3, 0, 1, 0, 1, 0, 2, 1, 0, 0, 0, 1, 0, 0, 0, 1};

}
