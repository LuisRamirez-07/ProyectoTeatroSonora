import java.util.HashMap;
import java.util.Scanner;

public class Principal {
    private static final Scanner leer = new Scanner(System.in);
    private static final Seccion[] secciones = new Seccion[]{
            new Seccion("VIP"),
            new Seccion("Palcos"),
            new Seccion("Normal")
    };
    private static final OperBoletos operBoletos = new OperBoletos(secciones);

    public static void main(String[] args) {
        int opcion;

        do {
            imprimirMenu();
            opcion = leer.nextInt();
            leer.nextLine();
            ejecutarOpcion(opcion);

        } while (opcion != 10);
    }

    /**
     * Función que imprime el menu principal
     */
    private static void imprimirMenu() {
        System.out.println("Menú de opciones");
        System.out.println(
                "1. Dar de alta fila \n" +
                        "2. Dar de alta asiento\n" +
                        "3. Mostrar filas de una sección\n" +
                        "4. Listar asientos disponibles\n" +
                        "5. Asignar precio de boletos\n" +
                        "6. Venta de boletos\n" +
                        "7. Cancelar una venta\n" +
                        "8. Cambiar asiento\n" +
                        "9. Mostrar ventas por sección\n" +
                        "10. Salir");
        System.out.println();
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Función que ejecuta una opción dada por el usuario
     *
     * @param opcion (int) valor del 1 al 10
     */
    private static void ejecutarOpcion(int opcion) {
        Seccion seccion;
        switch (opcion) {
            case 1:
                seccion = preguntarPorSeccion();
                if (seccion != null) {
                    seccion.darAltaFila();
                }
                break;
            case 2:
                seccion = preguntarPorSeccion();
                if (seccion != null) {
                    seccion.darAltaAsientos();
                }
                break;
            case 3:
                seccion = preguntarPorSeccion();
                if (seccion != null) {
                    System.out.println("FILAS EN SECCION: " + seccion.getNombre().toUpperCase());
                    System.out.println();
                    seccion.mostarFilas();
                }
                break;
            case 4:
                seccion = preguntarPorSeccion();
                if (seccion != null) {
                    System.out.println("ASIENTOS DISPONIBLES EN SECCION: " + seccion.getNombre().toUpperCase());
                    System.out.println();
                    seccion.mostrarAsientosDisponibles();
                }
                break;
            case 5:
                seccion = preguntarPorSeccion();
                if (seccion != null) {
                    seccion.asignarPrecio();
                }
                break;
            case 6:
                operBoletos.venderBoletos();
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                System.out.println("Cerrando el programa");
                break;
            default:
                System.out.println("Introduzca una opción valida\n");
        }
    }

    private static Seccion preguntarPorSeccion() {
        System.out.println("Secciones:");
        System.out.println("\t1.- VIP");
        System.out.println("\t2.- Palcos");
        System.out.println("\t3.- Normal");
        System.out.print("Seleccione la sección donde se dara de alta la fila: ");
        int opcion = leer.nextInt();
        leer.nextLine();

        switch (opcion) {
            case 1:
                return secciones[0];
            case 2:
                return secciones[1];
            case 3:
                return secciones[2];
            default:
                System.out.println("La opción no es valida...");
                return null;
        }
    }
}

