import java.util.Arrays;

public class Parqueadero {
    private String lugar;
    private int cantidadEspacio;
    private Espacio espacio;
    private int[][] matrizEspacios;
    private boolean[][] disponibilidad;

    public Parqueadero(String lugar, int cantidadEspacio, Espacio espacio) {
        this.lugar = lugar;
        this.cantidadEspacio = cantidadEspacio;
        this.espacio = espacio;
        this.matrizEspacios = calcularEspacios();
        this.disponibilidad = new boolean[espacio.getNivel()][cantidadEspacio];
        for (boolean[] fila : disponibilidad) {
            Arrays.fill(fila, true); // Todos los espacios están disponibles inicialmente
        }
    }

    // Getters y Setters
    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getCantidadEspacio() {
        return cantidadEspacio;
    }

    public void setCantidadEspacio(int cantidadEspacio) {
        this.cantidadEspacio = cantidadEspacio;
    }

    public Espacio getEspacio() {
        return espacio;
    }

    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    public int[][] getMatrizEspacios() {
        return matrizEspacios;
    }

    public void setMatrizEspacios(int[][] matrizEspacios) {
        this.matrizEspacios = matrizEspacios;
    }

    public boolean[][] getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean[][] disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public int[][] calcularEspacios() {
        int niveles = espacio.getNivel();
        int espaciosPorNivel = cantidadEspacio;
        int[][] matrizEspacios = new int[niveles][espaciosPorNivel];

        int numeracion = 1;
        for (int i = 0; i < niveles; i++) {
            for (int j = 0; j < espaciosPorNivel; j++) {
                matrizEspacios[i][j] = numeracion++;
            }
        }
        return matrizEspacios;
    }

    public void imprimirEspacios() {
        for (int[] nivel : matrizEspacios) {
            for (int espacio : nivel) {
                System.out.print(espacio + " ");
            }
            System.out.println();
        }
    }

}
