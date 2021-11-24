import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        int menu;
        do {
            System.out.println("Menú de opciones");
            System.out.println("1. Dar de alta fila \n2. Dar de alta asiento\n3. Mostrar filas de una sección\n4. Listar asientos disponibles\n5. Asignar precio de boletos\n"
                    + "6. Venta de boletos\n7. Cancelar una venta\n8. Cambiar asiento\n9. Mostrar ventas por sección\n10. Salir");
            menu = leer.nextInt();
            switch (menu) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
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
                    break;
            }
        } while (menu != 10);
    }
}

