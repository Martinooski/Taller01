package taller01;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    	// Martin Alvarado Lafferte | 22.330.833 | ICCI
    	// Vicente Antonio Muñoz | 22.380.392-K | ICCI
    	
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
            System.out.print("Contraseña: ");
            String pass = teclado.nextLine();
            if (arreglo_contrasenas[identificador].equals(pass)) {
                System.out.println("\nBienvenido " + usr + "!");
                menu_crud(usr, identificador);
            } else {
                System.out.println("Contraseña erronea.");
            }
        } else {
            System.out.println("Usuario no existe.");
        }
    }

    public static void menu_crud(String usr_logueado, int indice_usuario) {
        int opcion = 0;
        do {
            System.out.println("---MENU USUARIO---");
            System.out.println("1) Registrar actividad.");
            System.out.println("2) Modificar actividad.");
            System.out.println("3) Eliminar actividad.");
            System.out.println("4) Cambiar contrasena.");
            System.out.println("5) Salir.");

            while (true) {
                System.out.print("Seleccione opcion: ");
                try {
                    String entrada = teclado.nextLine();
                    opcion = Integer.parseInt(entrada);
                    break;
                } catch (Exception e) {
                    System.out.println("Error: Ingrese un numero.");
                }
            }

            switch(opcion) {
                case 1:
                    registrar_actividad(usr_logueado);
                    break;
                case 2:
                    modificar_actividad(usr_logueado);
                    break;
                case 3:
                    eliminar_actividad(usr_logueado);
                    break;
                case 4:
                    cambiar_contrasena(indice_usuario);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Ingrese opcion valida.");
                    break;
            }

        } while (opcion != 5);
    }

    public static void registrar_actividad(String usr) {
        if (cantidad_actividades < max_actividades) {
            String fecha = "";
            while (true) {
                System.out.print("Ingrese fecha (DD/MM/AAAA): ");
                fecha = teclado.nextLine();
                try {
                    String[] partes = fecha.split("/");
                    int dia = Integer.parseInt(partes[0]);
                    int mes = Integer.parseInt(partes[1]);
                    int anio = Integer.parseInt(partes[2]);
                    if (dia > 0 && dia <= 31 && mes > 0 && mes <= 12 && anio >= 2000) {
                        break; 
                    } else {
                        System.out.println("Error: Dia, mes o año fuera de rango.");
                    }
                } catch (Exception e) {
                    System.out.println("Error: Respete el formato DD/MM/AAAA con numeros.");
                }
            }
            
            int horas = 0;
            while (true) {
                System.out.print("Ingrese cantidad de horas: ");
                try {
                    String entrada = teclado.nextLine();
                    horas = Integer.parseInt(entrada);
                    break;
                } catch (Exception e) {
                    System.out.println("Error: Debe ser un numero entero.");
                }
            }
            
            System.out.print("Ingrese actividad: ");
            String act = teclado.nextLine();

            File arch_og = new File("Registros.txt");
            File arch_new = new File("Registros_temporal.txt");
            
            try (BufferedReader br = new BufferedReader(new FileReader(arch_og));
                 BufferedWriter bw = new BufferedWriter(new FileWriter(arch_new))) {
                
                String linea;
                while((linea = br.readLine()) != null) {
                    bw.write(linea);
                    bw.newLine();
                }
                bw.write(usr + ";" + fecha + ";" + horas + ";" + act);
                bw.newLine();
                
            } catch (IOException e){
                System.out.println("Error de lectura/escritura");
            }
            
            if(arch_og.delete()) {
                if(arch_new.renameTo(arch_og)) {
                    System.out.println("Actividad registrada.");
                    cargar_registros(); 
                } else {
                    System.out.println("No se pudo renombrar temporal.");
                }
            } else {
                System.out.println("No se pudo borrar original.");
            }

        } else {
            System.out.println("Cupos llenos.");
        }
    }

    public static void modificar_actividad(String usr) {
        int contador = 0;
        int[] indices = new int[max_actividades];

        System.out.println("Actividades:");
        for (int i = 0; i < cantidad_actividades; i++) {
            if (usuarios_actividades[i].equals(usr)) {
                System.out.println((contador + 1) + ") " + usuarios_actividades[i] + ";" + fechas_actividades[i] + ";" + horas_actividades[i] + ";" + descripcion_actividades[i]);
                indices[contador] = i;
                contador++;
            }
        }

        if (contador == 0) {
            System.out.println("No hay nada que modificar.");
            return;
        }

        int seleccion = -1;
        while (true) {
            System.out.print("Que numero desea modificar? (0 para salir): ");
            try {
                String entrada = teclado.nextLine();
                seleccion = Integer.parseInt(entrada);
                if (seleccion >= 0 && seleccion <= contador) break;
                System.out.println("Numero fuera de rango.");
            } catch (Exception e) {
                System.out.println("Error: Ingrese un numero.");
            }
        }

        if (seleccion > 0) {
            int pos_real = indices[seleccion - 1];
            String linea_vieja = usuarios_actividades[pos_real] + ";" + fechas_actividades[pos_real] + ";" + horas_actividades[pos_real] + ";" + descripcion_actividades[pos_real];
            
            System.out.println("1) Fecha");
            System.out.println("2) Horas");
            System.out.println("3) Actividad");
            
            int sub_op = 0;
            while (true) {
                System.out.print("Opcion: ");
                try {
                    String entrada = teclado.nextLine();
                    sub_op = Integer.parseInt(entrada);
                    if (sub_op >= 1 && sub_op <= 3) break;
                    System.out.println("Opcion no valida.");
                } catch (Exception e) {
                    System.out.println("Error: Ingrese un numero.");
                }
            }

            String n_fecha = fechas_actividades[pos_real];
            int n_horas = horas_actividades[pos_real];
            String n_act = descripcion_actividades[pos_real];

            if (sub_op == 1) {
                while (true) {
                    System.out.print("Nueva fecha (DD/MM/AAAA): ");
                    n_fecha = teclado.nextLine();
                    try {
                        String[] partes = n_fecha.split("/");
                        int dia = Integer.parseInt(partes[0]);
                        int mes = Integer.parseInt(partes[1]);
                        int anio = Integer.parseInt(partes[2]);
                        if (dia > 0 && dia <= 31 && mes > 0 && mes <= 12 && anio >= 2000) {
                            break; 
                        } else {
                            System.out.println("Error: Dia, mes o año fuera de rango.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: Respete el formato DD/MM/AAAA con numeros.");
                    }
                }
            } else if (sub_op == 2) {
                while (true) {
                    System.out.print("Nuevas horas: ");
                    try {
                        String entrada = teclado.nextLine();
                        n_horas = Integer.parseInt(entrada);
                        break;
                    } catch (Exception e) {
                        System.out.println("Error: Ingrese un entero.");
                    }
                }
            } else if (sub_op == 3) {
                System.out.print("Nueva actividad: ");
                n_act = teclado.nextLine();
            }

            String linea_nueva = usr + ";" + n_fecha + ";" + n_horas + ";" + n_act;

            File arch_og = new File("Registros.txt");
            File arch_new = new File("Registros_temporal.txt");
            
            try (BufferedReader br = new BufferedReader(new FileReader(arch_og));
                 BufferedWriter bw = new BufferedWriter(new FileWriter(arch_new))) {
                
                String linea;
                while((linea = br.readLine()) != null) {
                    if (linea.equals(linea_vieja)) {
                        bw.write(linea_nueva);
                    } else {
                        bw.write(linea);
                    }
                    bw.newLine();
                }
                
            } catch (IOException e){
                System.out.println("Error");
            }
            
            if(arch_og.delete()) {
                if(arch_new.renameTo(arch_og)) {
                    System.out.println("Modificado.");
                    cargar_registros();
                } else {
                    System.out.println("No se pudo renombrar.");
                }
            } else {
                System.out.println("No se pudo borrar.");
            }
        }
    }

    public static void eliminar_actividad(String usr) {
        int contador = 0;
        int[] indices = new int[max_actividades];

        System.out.println("Actividades:");
        for (int i = 0; i < cantidad_actividades; i++) {
            if (usuarios_actividades[i].equals(usr)) {
                System.out.println((contador + 1) + ") " + usuarios_actividades[i] + ";" + fechas_actividades[i] + ";" + horas_actividades[i] + ";" + descripcion_actividades[i]);
                indices[contador] = i;
                contador++;
            }
        }

        if (contador == 0) return;

        int seleccion = -1;
        while (true) {
            System.out.print("Cual eliminar? (0 para cancelar): ");
            try {
                String entrada = teclado.nextLine();
                seleccion = Integer.parseInt(entrada);
                if (seleccion >= 0 && seleccion <= contador) break;
                System.out.println("Fuera de rango.");
            } catch (Exception e) {
                System.out.println("Error: Ingrese numero.");
            }
        }

        if (seleccion > 0) {
            int pos_real = indices[seleccion - 1];
            String linea_borrar = usuarios_actividades[pos_real] + ";" + fechas_actividades[pos_real] + ";" + horas_actividades[pos_real] + ";" + descripcion_actividades[pos_real];

            File arch_og = new File("Registros.txt");
            File arch_new = new File("Registros_temporal.txt");
            
            try (BufferedReader br = new BufferedReader(new FileReader(arch_og));
                 BufferedWriter bw = new BufferedWriter(new FileWriter(arch_new))) {
                
                String linea;
                while((linea = br.readLine()) != null) {
                    if (!linea.equals(linea_borrar)) {
                        bw.write(linea);
                        bw.newLine();
                    }
                }
                
            } catch (IOException e){
                System.out.println("Error");
            }
            
            if(arch_og.delete()) {
                if(arch_new.renameTo(arch_og)) {
                    System.out.println("Eliminado.");
                    for(int i = 0; i < cantidad_actividades; i++) {
                        usuarios_actividades[i] = null;
                        fechas_actividades[i] = null;
                        horas_actividades[i] = 0;
                        descripcion_actividades[i] = null;
                    }
                    cantidad_actividades = 0;
                    cargar_registros();
                } else {
                    System.out.println("No se pudo renombrar.");
                }
            } else {
                System.out.println("No se pudo borrar.");
            }
        }
    }

    public static void cambiar_contrasena(int indice) {
        System.out.print("Ingrese nueva contrasena: ");
        String nueva_contrasena = teclado.nextLine();
        
        File arch_og = new File("Usuarios.txt");
        File arch_new = new File("Usuarios_temporal.txt");
        
        try (BufferedReader br = new BufferedReader(new FileReader(arch_og));
             BufferedWriter bw = new BufferedWriter(new FileWriter(arch_new))) {
        
            String linea;
            while((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                String usuario = partes[0];
                
                if (usuario.equals(arreglo_usuarios[indice])) {
                    bw.write(usuario + ";" + nueva_contrasena); 
                    bw.newLine();
                } else {
                    bw.write(linea);
                    bw.newLine();
                }
            }
        } catch (IOException e){
            System.out.println("Error al cambiar contrasena.");
        }
        
        if(arch_og.delete()) {
            if(arch_new.renameTo(arch_og)) {
                System.out.println("Contrasena cambiada.");
                cargar_usuarios();
            } else {
                System.out.println("Error de renombrado.");
            }
        } else {
            System.out.println("Error al borrar.");
        }
    }
}
