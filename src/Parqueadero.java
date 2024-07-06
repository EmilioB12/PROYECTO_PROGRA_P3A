import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Parqueadero {
    private String lugar;
    private int cantidadEspacio;
    private Espacio espacio;
    private Espacio[][] matrizEspacios;
    private boolean[][] disponibilidad;
    private List<Bitacora> matricesBitacora;


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

    public Espacio[][] getMatrizEspacios() {
        return matrizEspacios;
    }

    public void setMatrizEspacios(Espacio[][] matrizEspacios) {
        this.matrizEspacios = matrizEspacios;
    }

    public boolean[][] getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean[][] disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public List<Bitacora> getMatricesBitacora() {
        return matricesBitacora;
    }

    public void setMatricesBitacora(List<Bitacora> matricesBitacora) {
        this.matricesBitacora = matricesBitacora;
    }

    public Espacio[][] calcularEspacios() {
        int niveles = espacio.getNivel();
        int espaciosPorNivel = cantidadEspacio;
        Espacio[][] matrizEspacios = new Espacio[niveles][espaciosPorNivel];

        int numeracion = 1;
        for (int i = 0; i < niveles; i++) {
            for (int j = 0; j < espaciosPorNivel; j++) {
                matrizEspacios[i][j] = new Espacio(i + 1, numeracion++);
            }
        }
        return matrizEspacios;
    }

    public void imprimirEspacios() {
        for (Espacio[] nivel : matrizEspacios) {
            for (Espacio espacio : nivel) {
                System.out.print(espacio.getNumeracion() + " ");
            }
            System.out.println();
        }
    }

    // Método para asignar un espacio disponible y marcarlo como ocupado

    /*public int asignarEspacioDisponible() {
        // Crear una lista de espacios disponibles
        List<Espacio> espaciosDisponibles = new ArrayList<>();

        // Agregar todos los espacios disponibles a la lista
        for (int i = 0; i < matrizEspacios.length; i++) {
            for (int j = 0; j < matrizEspacios[i].length; j++) {
                if (disponibilidad[i][j]) {
                    espaciosDisponibles.add(matrizEspacios[i][j]);
                }
            }
        }

        // Verificar si hay espacios disponibles
        if (espaciosDisponibles.isEmpty()) {
            // No hay espacios disponibles
            return -1; // Puedes devolver -1 o algún valor que indique no disponibilidad
        }

        // Elegir un espacio aleatorio de los disponibles
        Random random = new Random();
        int indiceEspacio = random.nextInt(espaciosDisponibles.size());
        Espacio espacioElegido = espaciosDisponibles.get(indiceEspacio);

        // Marcar el espacio elegido como ocupado
        marcarEspacioOcupado(espacioElegido);

        return espacioElegido.getNumeracion();
    }*/

    public Espacio asignarEspacioDisponible() {
        for (int i = 0; i < matrizEspacios.length; i++) {
            for (int j = 0; j < matrizEspacios[i].length; j++) {
                if (disponibilidad[i][j]) {
                    Espacio espacioAsignado = matrizEspacios[i][j];
                    disponibilidad[i][j] = false;
                    return espacioAsignado;
                }
            }
        }
        return null; // No hay espacios disponibles
    }


    // Método privado para marcar un espacio como ocupado
    private void marcarEspacioOcupado(Espacio espacioOcupado) {
        for (int i = 0; i < espacio.getNivel(); i++) {
            for (int j = 0; j < cantidadEspacio; j++) {
                if (matrizEspacios[i][j].getNumeracion() == espacioOcupado.getNumeracion()) {
                    disponibilidad[i][j] = false; // Marcar como no disponible
                    return;
                }
            }
        }
    }

    @Override
    public String toString() {
        return  "Nombre Parqueadero: " + lugar +
                "\nEspacio de parqueadero: " + cantidadEspacio +
                "\nNiveles: " + getEspacio().getNivel() +
                "\nNumero total de espacios: " + (cantidadEspacio * espacio.getNivel());
    }
}