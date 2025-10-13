package Practicas.Practica2;

import java.io.*;
import java.util.*;

public class Worker {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.err.println("Argumentos inválidos.");
            return;
        }
        String ruta = args[0]; //ruta del archivo a procesar
        int inicio = Integer.parseInt(args[1]); // número de línea donde empezar a contar (inicio)
        int fin = Integer.parseInt(args[2]); // número de línea donde terminar de contar (fin)
        String salida = args[3]; // ruta del archivo de salida donde guardar los resultados

        Map<String, Integer> frecuencias = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            String line;
            int lineNum = 1;
            while ((line = reader.readLine()) != null) {
                if (lineNum >= inicio && lineNum <= fin) {
                    String[] words = line.split("\\W+");
                    for (String word : words) {
                        if (!word.isEmpty()) {
                            word = word.toLowerCase();
                            frecuencias.put(word, frecuencias.getOrDefault(word, 0) + 1);
                        }
                    }
                }
                if (lineNum > fin) break;
                lineNum++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(salida))) {
            for (Map.Entry<String, Integer> entry : frecuencias.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}