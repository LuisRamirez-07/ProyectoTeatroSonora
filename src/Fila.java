import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Fila {
    private final char id;
    private final List<Asiento> asientos;
    private int index = 1;
    private int MAXIMO_ASIENTOS = 100;

    private final Scanner scanner = new Scanner(System.in);

    Fila(char id) {
        this.id = id;
        asientos = new ArrayList<>();
    }

    public void darAltaAsiento() {
        if (index >= MAXIMO_ASIENTOS) {
            System.out.println("Se llego al máximo valor (" + MAXIMO_ASIENTOS + ") de asientos permitidos, no se pueden agregar más asientos.");
            return;
        }

        System.out.print("¿Cuantos asientos desea dar de alta? ");
        int numeroDeAsientos = scanner.nextInt();
        scanner.nextLine();

        if (numeroDeAsientos + index > MAXIMO_ASIENTOS) {
            System.out.println("Con " + numeroDeAsientos + " se excede el máximo número de asientos permitido");
            numeroDeAsientos = MAXIMO_ASIENTOS - index;
            index = MAXIMO_ASIENTOS;
            System.out.println("Solo se darán de alta " + numeroDeAsientos + " asientos.");
        }

        int i = 0;
        while (i < numeroDeAsientos && index < MAXIMO_ASIENTOS) {
            asientos.add(new Asiento(index++));
            i++;
        }
    }

    public int obtenerCantidadDeAsientosOcupados() {
        return (int) asientos.stream().filter(asiento -> asiento.isOcupado).count();
    }

    public String listarAsientosDisponibles() {
        List<Asiento> asientosDisponibles = asientos.stream().filter(asiento -> !asiento.isOcupado).collect(Collectors.toList());
        StringBuilder asientosStr = new StringBuilder();
        for (int i = 0; i < asientosDisponibles.size(); i++) {
            asientosStr.append(asientosDisponibles.get(i).getId());
            if (i < asientosDisponibles.size() - 1)
                asientosStr.append(", ");
        }
        return asientosStr.toString();
    }

    public List<Asiento> obtenerTodosAsientos() {
        return asientos;
    }

    public int asientosEnFila() {
        return asientos.size();
    }

    public char getId() {
        return id;
    }
}
