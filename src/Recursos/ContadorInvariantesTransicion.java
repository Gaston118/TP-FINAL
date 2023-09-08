package Recursos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContadorInvariantesTransicion {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("transitions.txt")); //BufferedReader para leer el archivo de transiciones
            String line;                                                                   //la uso para almacenar cada linea del archivo

            Map<String, Integer> contadorInvariantes = new HashMap<>();                     // mapa para almacenar el conteo de cada invariante
            
            // expresioon regular 
            Pattern patron = Pattern.compile("((T1 )(.*?)(T3 )(.*?)|(T2 )(.*?)(T4 )(.*?))((T5 )(.*?)(T7 )(.*?)|(T6 )(.*?)(T8 )(.*?))((T9 )(.*?)(T11 )(.*?)|(T10 )(.*?)(T12 )(.*?))(T13 )(.*?)(T14 )");

            while ((line = reader.readLine()) != null) {  // mientras haya una linea para leer, leo
                Matcher matcher = patron.matcher(line);   // busco la expresion regular en la linea leida
                while (matcher.find()) {                  //si encuentro un match
                     // El grupo 1 en la expresión regular coincide con el invariante IT1, el grupo 2 con IT2, y así sucesivamente
                    for (int i = 1; i <= 8; i++) {
                        String invariante = matcher.group(i * 4 - 3);  //obtengo el invariante correspondiente al grupo de captura i en la expresión regular
                                                                        // cada invariante se encuentra en el grupo de captura (Tn ) en la expresión regular
                        if (invariante != null) {                      //Verifico si encontre un invariante en el grupo de captura
                            contadorInvariantes.putIfAbsent("IT" + i, 0); // si el invariante no existe en el mapa, lo agrego con valor 0
                            contadorInvariantes.put("IT" + i, contadorInvariantes.get("IT" + i) + 1);  // incremento el valor del invariante en 1
                        }
                    }
                }
            }
            reader.close();
              // imprimo
            for (Map.Entry<String, Integer> entry : contadorInvariantes.entrySet()) {
                System.out.println("Invariante " + entry.getKey() + " se cumplió " + entry.getValue() + " veces.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
