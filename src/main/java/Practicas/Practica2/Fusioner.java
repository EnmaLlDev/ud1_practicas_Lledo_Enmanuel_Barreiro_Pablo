package Practicas.Practica2;
import java.io.*;
import java.util.*;

public class Fusioner {
    public static void main(String[] args) {
        Map<String, Integer> totalFreq = new HashMap<>();
        for (String archivo : args) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] partes = linea.split(",");
                    if (partes.length == 2) {
                        String palabra = partes[0];
                        int frecuencia = Integer.parseInt(partes[1]);
                        totalFreq.put(palabra, totalFreq.getOrDefault(palabra, 0) + frecuencia);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("5 palabras más frecuentes:");
        totalFreq.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(5)
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));

        for (String archivo : args) {
            new File(archivo).delete();
        }
    }
}