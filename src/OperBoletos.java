import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class OperBoletos {

    private Scanner scanner = new Scanner(System.in);

    Seccion[] secciones;

    public OperBoletos(Seccion[] secciones) {
        this.secciones = secciones;
    }

    public void venderBoletos() {
        double costo = 0.0;
        List<String> boletosId = new LinkedList<>();

        // Se pide la cantidad de boletos
        System.out.print("Cantidad de boletos a vender: ");
        int boletos = scanner.nextInt();
        scanner.nextLine();

        // Se valida que esa cantidad esté disponible en el teatro.
        int asientosDisponibles = asientosDisponibles();
        if (asientosDisponibles <= boletos) {
            // Si no esta disponible marca error y regresa a menu principal.
            System.out.println("No hay esta cantidad de asientos disponibles es el Teatro");
            System.out.println("Solo se encuentran disponibles " + asientosDisponibles + " asientos");
            return;
        }

        // Si está disponible esta cantidad, se pasa a preguntar la Sección, Fila y Asiento que se quiere.
        for (int count = 1; count <= boletos; count++) {
            // Pregunta por una sección valida.
            Seccion seccion;
            while (true) {
                seccion = preguntarPorSeccion();
                if (seccion.obtenerAsientosDisponibles() == 0) {
                    System.out.println("No hay asientos disponibles en esta sección");
                    continue;
                }
                break;
            }

            Fila fila;
            while (true) {
                fila = preguntarPorFila(seccion);
                if (fila.obtenerCantidadDeAsientosDisponibles() == 0) {
                    System.out.println("No hay asientos disponibles en esta fila");
                    continue;
                }
                break;
            }

            Asiento asiento = preguntarPorAsiento(fila);

            asiento.isOcupado = true;
            costo += seccion.getPrecio();
            String boletoId = seccion.getNombre() + "-" + fila.getId() + asiento.getId();
            boletosId.add(boletoId);
        }

        System.out.println("Se vendieron los boletos: ");
        boletosId.forEach(System.out::println);
        System.out.println("Total a pagar: " + costo);
    }

    private int asientosDisponibles() {
        int asientosDisponibles = 0;
        for (Seccion seccion : secciones) {
            asientosDisponibles += seccion.obtenerAsientosDisponibles();
        }
        return asientosDisponibles;
    }

    private Seccion preguntarPorSeccion() {
        String opcion;
        do {
            System.out.println("Secciones:");
            System.out.println("\t1.- VIP");
            System.out.println("\t2.- Palcos");
            System.out.println("\t3.- Normal");
            System.out.println();
            System.out.println("Presione 'M' para mostrar los boletos disponibles");
            System.out.print("Seleccione la sección donde se dara de alta la fila: ");

            opcion = scanner.nextLine();
            if (opcion.length() != 1) {
                System.out.println("Opción tiene más de 1 caracter, seleccione una de las opciones");
                continue;
            }

            if (opcion.equals("M")) {
                mostrarBoletosDisponibles();
                continue;
            }

            switch (opcion) {
                case "1":
                    return secciones[0];
                case "2":
                    return secciones[1];
                case "3":
                    return secciones[2];
                default:
                    System.out.println("La opción no es valida...");
            }
        } while (true);
    }

    private Fila preguntarPorFila(Seccion seccion) {
        while (true) {
            seccion.mostrarAsientosDisponiblesPorFila();
            System.out.println();
            System.out.print("Seleccione la fila: ");

            String filaIdStr = scanner.nextLine();
            if (filaIdStr.length() != 1) {
                System.out.println("Opción no valida, intente de nuevo");
                continue;
            }

            char filaID = filaIdStr.toUpperCase().charAt(0);
            if (!seccion.obtenerFilasValidas().contains(filaID)) {
                System.out.println("Opción no valida, intente de nuevo");
                continue;
            }

            Fila fila = seccion.obtenerFila(filaID);
            if (fila == null) {
                System.out.println("Opción no valida, intente de nuevo");
                continue;
            }

            return fila;
        }
    }

    private Asiento preguntarPorAsiento(Fila fila) {
        String asientos = fila.listarAsientosDisponibles();
        while (true) {
            System.out.println("Asientos: " + asientos);
            System.out.println();
            System.out.print("Seleccione un asiento: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Opción no valida, intente de nuevo");
                continue;
            }

            int asientoId = scanner.nextInt();
            Asiento asiento = fila.obtenerAsiento(asientoId);
            if (asiento == null) {
                System.out.println("Asiento no valido, escoja uno de la lista");
                continue;
            }

            if (asiento.isOcupado) {
                System.out.println("Este asiento ya ha sido vendido, seleccione uno desocupado");
                continue;
            }
            return asiento;
        }
    }

    private void mostrarBoletosDisponibles() {
        for (Seccion seccion : secciones) {
            System.out.println("ASIENTOS DISPONIBLES EN SECCION: " + seccion.getNombre().toUpperCase());
            System.out.println();
            seccion.mostrarAsientosDisponibles();
            System.out.println();
        }
        System.out.println("\n");
    }
}
