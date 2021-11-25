import java.util.*;

public class Seccion {

    private final Scanner scanner = new Scanner(System.in);
    private String nombre;
    private final char[] FILAS_VALIDAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private final int MAXIMO_FILAS = FILAS_VALIDAS.length;
    private int index = 0;
    private double precio;

    List<Fila> filas = new ArrayList<>();

    public Seccion(String nombre) {
        this.nombre = nombre;
        this.precio = 100.0;
    }

    public void darAltaFila() {
        if (index >= MAXIMO_FILAS) {
            System.out.println("Se llego al máximo valor de filas permitido, no se pueden agregar más filas.");
            return;
        }

        System.out.print("¿Cuantas filas desea dar de alta? ");
        int numeroDeFilas = scanner.nextInt();
        scanner.nextLine();

        if (numeroDeFilas + index > MAXIMO_FILAS) {
            System.out.println("Con " + numeroDeFilas + " se excede el máximo número de filas permitido");
            numeroDeFilas = MAXIMO_FILAS - index;
            index = MAXIMO_FILAS;
            System.out.println("Solo se darán de alta " + numeroDeFilas + " filas.");
        }

        int i = 0;
        while (i < numeroDeFilas && index < MAXIMO_FILAS) {
            filas.add(new Fila(FILAS_VALIDAS[index++]));
            i++;
        }

    }

    public void darAltaAsientos() {
        if (filas.isEmpty()) {
            System.out.println("No se han dado de alta filas aún para esta sección.\n");
            return;
        }

        System.out.println("¿De que fila son los asientos a dar de alta? ");
        Fila fila = preguntarPorFila();
        if (fila != null) {
            fila.darAltaAsiento();
        }
    }

    private Fila preguntarPorFila() {
        StringBuilder filasDisponibles = new StringBuilder();
        for (int i = 0; i < filas.size(); i++) {
            filasDisponibles.append(filas.get(i).getId());
            if (i < filas.size() - 1) {
                filasDisponibles.append(", ");
            }
        }
        System.out.println("Filas disponibles: " + filasDisponibles);
        System.out.println();
        System.out.print("Seleccione la fila donde se dara de alta los asientos: ");
        String filaStr = scanner.nextLine();
        if (filaStr.length() != 1) {
            System.out.println("Valor no valido, seleccione el valor de una de las filas");
            return null;
        }

        char filaId = filaStr.toUpperCase().charAt(0);
        Fila fila = obtenerFila(filaId);
        if (fila == null) {
            System.out.println("La fila no se encuentra dada de alta, o no es un valor valido");
            return null;
        }
        return fila;
    }

    private Fila obtenerFila(char filaId) {
        for (Fila fila : filas) {
            if (fila.getId() == filaId) {
                return fila;
            }
        }
        return null;
    }

    public void venderBoleto() {

    }

    public void mostarFilas() {
        for(Fila fila : filas) {
            System.out.println("Fila " + fila.getId() + " - " + fila.asientosEnFila() + " asientos");
        }
    }

    public void mostrarAsientosDisponibles() {
        for(Fila fila : filas) {
            System.out.println("Fila " + fila.getId() + " - " + fila.listarAsientosDisponibles());
        }
    }

    public void asignarPrecio() {
        System.out.println("El precio establecido del asiento es de: " + precio);
        System.out.print("Nuevo precio del asiento:  ");
        precio = scanner.nextDouble();
        scanner.nextLine();
    }

    public void cancelarVenta() {

    }

    public void calcularVentasSeccion() {

    }

    public String getNombre() {
        return nombre;
    }
}
