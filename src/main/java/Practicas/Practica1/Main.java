package Practicas.Practica1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        boolean existsNotePad = false;
        boolean existsCalculatorApp = false;

        try {
            Process process = null;
            process = new ProcessBuilder("tasklist").start();
            List<String> outputLines = getProcessOutputLines(process);
            for (String line : outputLines) {
                if (line.toLowerCase().contains("notepad.exe")) {
                    existsNotePad = true;
                    System.out.println("Se ha encontrado: notepad.exe" );
                }
                if (line.toLowerCase().contains("calculatorapp.exe")) {
                    existsCalculatorApp = true;
                    System.out.println("Se ha encontrado: calculatorapp.exe" );
                }
            }
            for (String line : outputLines) {
                System.out.println(line);
            }

            if (!existsCalculatorApp) {
                System.out.println("No se encontraron instancias de calc.exe");
            }
            if (!existsNotePad){
                System.out.println("No se encontraron instancias de notepad.exe");
            }

            if (existsNotePad) {
                new ProcessBuilder("taskkill", "/IM", "notepad.exe", "/F").start();
                System.out.println("Proceso Notepad terminado");
            }
            if (existsCalculatorApp) {
                new ProcessBuilder("taskkill", "/IM", "calculatorapp.exe", "/F").start();
                System.out.println("Proceso CalculatorApp terminado");
            }

        } catch ( IOException eIO) {
            System.out.println("No se ha podido ejecutar el comando");

        }
        catch (Exception e) {
            System.out.println("Se ha producido un error inesperado");
        }
    }

    private static List<String> getProcessOutputLines(Process process) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            return reader.lines().toList();
        }
    }
}


