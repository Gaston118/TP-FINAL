package Recursos;

public class Politica {
    /*
        TOMAR LA DECISION DE QUE POLITICA VAMOS A IMPLEMENTAR OBVIANDO QUE VAMOS A TENER 2 CASOS
        EXPLICADOS EN EL PDF.
        1-BALANCEADO
        2-SEGMENTO IZQ ET3

        SIGNAL AND EXIT: En esta disciplina se obliga a que la instruccion de reactivacion,
        sea siempre la ultima que ejecuta el proceso reactivador antes de salir
        del monitor.
        El comportamiento del monitor es sencillo: el proceso reactivador sale del monitor
        y entra el reactivado. Si no hay ningun proceso que reactivar, entra un proceso de la cola
        de entrada al monitor.

        SIGNAL AND URGENT WAIT: El proceso reactivador no
        se transfiere a la cola de entrada, sino a una cola especial, llamada de
        urgentes, formada por procesos que tienen prioridad para entrar en el
        monitor sobre los que est´an en la cola de entrada.
        ESTE TIPO ES MAS JUSTO.
    */
}
