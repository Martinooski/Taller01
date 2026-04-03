package taller01;

// Martin Alvarado | 22.330.833-3 | Martinooski
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
      
        do {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║   SISTEMA PROCRASTINACION   ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║  1) Menu de Usuarios        ║");
            System.out.println("║  2) Menu de Analisis        ║");
            System.out.println("║  3) Salir                   ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("Opcion: ");

            opcion = Validador.leerEntero(scanner);

            switch (opcion) {
                case 1:
                    menuUsuarios.mostrar();
                    break;
                case 2:
                    menuAnalisis.mostrar();
                    break;
                case 3:
                    System.out.println("Hasta luego, Sigue procrastinando");
                    break;
                default:
                    System.out.println("Opcion invalida. Ingresa 1, 2 o 3");
            }
        } while (opcion != 3);

      //NO FUNCIONA PQ ME FALTA CODIGO
      
        scanner.close();
    }
