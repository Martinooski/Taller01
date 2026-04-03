package taller01;

// Martin Alvarado Lafferte | 22.330.833 | ICCI
// Vicente Antonio Muñoz | 22.380.392-K | ICCI
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    static final int max_usuarios = 10;
    static String[] arreglo_usuarios = new String[max_usuarios];
    static String[] arreglo_contrasenas = new String[max_usuarios];
    static int usuarios_registrados = 0;

    static final int max_actividades = 300;
    static String[] usuarios_actividades = new String[max_actividades];
    static String[] fechas_actividades = new String[max_actividades];
    static int[] horas_actividades = new int[max_actividades];
    static String[] descripcion_actividades = new String[max_actividades];
    static int cantidad_actividades = 0;

    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
    	
        cargar_usuarios();
        cargar_registros();

        int opcion = -1;
        while (opcion != 3) {
            System.out.println("--- MENU PRINCIPAL---");
            System.out.println("1) Menu de Usuarios");
            System.out.println("2) Menu de Analisis");
            System.out.println("3) Salir");
            
            while (true) {
                System.out.print("Seleccione una opcion: ");
                try {
                    String entrada = teclado.nextLine();
                    opcion = Integer.parseInt(entrada);
                    break; 
                } catch (Exception e) {
                    System.out.println("Error: Solo se permiten numeros.");
                }
            }

            switch(opcion) {
                case 1:
                    iniciar_sesion();
                    break;
                case 2:
                    System.out.println("Menu analisis en construccion...");
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion invalida, Intente de nuevo.");
                    break;
            }
        }
    }

    public static void cargar_usuarios() {
        try {
            File arch = new File("Usuarios.txt");
            Scanner lector = new Scanner(arch);
            usuarios_registrados = 0;
            while (lector.hasNextLine() && usuarios_registrados < max_usuarios) {
                String linea = lector.nextLine();
                String[] partes = linea.split(";");
                arreglo_usuarios[usuarios_registrados] = partes[0];
                arreglo_contrasenas[usuarios_registrados] = partes[1];
                usuarios_registrados++;
            }
            lector.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontro Usuarios.txt.");
        }
    }

    public static void cargar_registros() {
        try {
            File arch = new File("Registros.txt");
            Scanner lector = new Scanner(arch);
            cantidad_actividades = 0;
            while (lector.hasNextLine() && cantidad_actividades < max_actividades) {
                String linea = lector.nextLine();
                String[] partes = linea.split(";");
                usuarios_actividades[cantidad_actividades] = partes[0];
                fechas_actividades[cantidad_actividades] = partes[1];
                horas_actividades[cantidad_actividades] = Integer.parseInt(partes[2]);
                descripcion_actividades[cantidad_actividades] = partes[3];
                cantidad_actividades++;
            }
            lector.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontro Registros.txt.");
        }
    }

    public static void iniciar_sesion() {
        System.out.print("Usuario: ");
        String usr = teclado.nextLine();
        int identificador = -1;

        for (int i = 0; i < usuarios_registrados; i++) {
            if (arreglo_usuarios[i].equals(usr)) {
                identificador = i;
            }
        }

        if (identificador != -1) {
            System.out.print("Contrasena: ");
            String pass = teclado.nextLine();
            if (arreglo_contrasenas[identificador].equals(pass)) {
                System.out.println("\nBienvenido " + usr + "!");
                System.out.println("Menu CRUD en construccion...");
            } else {
                System.out.println("Contraseña erronea.");
            }
        } else {
            System.out.println("Usuario no existe.");
        }
    }
}