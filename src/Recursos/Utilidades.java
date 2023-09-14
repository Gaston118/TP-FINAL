package Recursos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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


    //Suma vectorial
    public static Integer[] sumarVectores(Integer[] vector1, Integer[] vector2) {
        Integer[] resultante = new Integer[CANTIDAD_PLAZAS];
        for (int i = 0; i < CANTIDAD_PLAZAS; i++) {
            resultante[i] = vector1[i] + vector2[i];
        }

        return resultante;
    }

    public static Integer[][] getMatriz(){
        Integer[][] matriz = new Integer[19][15];
        String archivo = "matriz.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int fila = 0;

            // Leer el archivo línea por línea y dividir los valores por comas
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(",");
                for (int columna = 0; columna < valores.length; columna++) {
                    matriz[fila][columna] = Integer.parseInt(valores[columna]);
                }
                fila++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matriz;
    }
    public static final Integer[][] MATRIZ_INCIDENCIA = new Integer[][]{
            {1,	-1,	-1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
            {0,	-1,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
            {0,	1,	0,	-1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
            {0,	-1,	-1,	1,	1,	0,	0,	0,	0,	0,	0,	0,	0,	-1,	1},
            {0,	0,	1,	0,	-1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
            {0,	0,	-1,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
            {0,	0,	0,	1,	1,	-1,	-1,	0,	0,	0,	0,	0,	0,	0,	0},
            {0,	0,	0,	0,	0,	-1,	0,	1,	0,	0,	0,	0,	0,	0,	0},
            {0,	0,	0,	0,	0,	1,	0,	-1,	0,	0,	0,	0,	0,	0,	0},
            {0,	0,	0,	0,	0,	-1,	-1,	1,	1,	0,	0,	0,	0,	0,	0},
            {0,	0,	0,	0,	0,	0,	-1,	0,	1,	0,	0,	0,	0,	0,	0},
            {0,	0,	0,	0,	0,	0,	1,	0,	-1,	0,	0,	0,	0,	0,	0},
            {0,	0,	0,	0,	0,	0,	0,	1,	1,	-1,	-1,	0,	0,	0,	0},
            {0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	-1,	0,	0,	0},
            {0,	0,	0,	0,	0,	0,	0,	0,	0,	-1,	-1,	1,	1,	0,	0},
            {0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	-1,	0,	0},
            {0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	1,	-1,	0},
            {0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	-1},
            {0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	-1,	1}
    };

    public static final Integer CANTIDAD_PLAZAS = getMatriz().length;
    public static final Integer CANTIDAD_TRANSICIONES = getMatriz()[0].length;
    public static final Integer[] MARCADO_INICIAL = {0, 1, 0, 3, 0, 1, 0, 1, 0, 2, 1, 0, 0, 0, 1, 0, 0, 0, 1};

    public static final Integer[][] IT = {
            {1,3,5,7,9,11,13,14},
            {2,4,6,8,9,11,13,14},
            {1,3,6,8,9,11,13,14},
            {2,4,5,7,9,11,13,14},
            {2,4,5,7,10,12,13,14},
            {1,3,6,8,10,12,13,14},
            {1,3,5,7,10,12,13,14},
            {2,4,6,8,10,12,13,14}
    };

    //TRANSICIONES TEMPORIZADAS
    public static final Integer[] TTemp =  {0, 3, 4, 7, 8, 11, 12, 14};



}
