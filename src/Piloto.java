public class Piloto {
    private String nombre;
    private int puntos;

    public Piloto(String name, int points) {
        this.nombre =name;
        this.puntos =points;
    }

    public int getPuntos() {
        return puntos;
    }

    public void sumPuntos(int points) {
        this.puntos +=points;
    }

    public String getNombre() {
        return nombre;
    }

    public String toString() {
        return nombre +" - "+ puntos;
    }
}