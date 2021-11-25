public class Asiento {
    public int id;
    boolean isOcupado;

    public Asiento(int id) {
        this.id = id;
        this.isOcupado = false;
    }

    public int getId() {
        return id;
    }

    public boolean isOcupado() {
        return isOcupado;
    }

    public void setOcupado(boolean ocupado) {
        isOcupado = ocupado;
    }
}
