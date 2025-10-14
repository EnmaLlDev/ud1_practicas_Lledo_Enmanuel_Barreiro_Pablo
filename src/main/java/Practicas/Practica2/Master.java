package Practicas.Practica2;

import java.io.*;
import java.util.*;

public class Master {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Master iniciado");
        System.out.print("Ruta del archivo: ");
        String ruta = scanner.nextLine();
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            System.err.println("El archivo no existe.");
            return;
        }
        System.out.print("Número de procesos: ");
        int n = scanner.nextInt();
        if (n <= 0) {
            System.err.println("Número de procesos inválido.");
            return;
        }

        int totalLineas = contarLineas(ruta);
        int bloque = totalLineas / n;
        int resto = totalLineas % n;
        List<String> archivosTemp = new ArrayList<>();
        int inicio = 1;

        for (int i = 0; i < n; i++) {
            int fin = inicio + bloque - 1;
            if (i < resto) fin++;
            String temp = "temp_salida_" + i + ".txt";
            archivosTemp.add(temp);

            ProcessBuilder pb = new ProcessBuilder(
                    "java", "-cp", System.getProperty("java.class.path"),
                    "Practicas.Practica2.Worker",
                    ruta, String.valueOf(inicio), String.valueOf(fin), temp
            );
            try {
                Process p = pb.start();
                p.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
            inicio = fin + 1;
        }

        // Lanzar Fusioner
        List<String> argsFusioner = new ArrayList<>();
        argsFusioner.add("java");
        argsFusioner.add("-cp");
        argsFusioner.add(System.getProperty("java.class.path"));
        argsFusioner.add("Practicas.Practica2.Fusioner");
        argsFusioner.addAll(archivosTemp);

        try {
            Process fusioner = new ProcessBuilder(argsFusioner).inheritIO().start();
            fusioner.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Master finalizado.");
    }

    private static int contarLineas(String ruta) {
        int lineas = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            while (br.readLine() != null) lineas++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineas;
    }
}
