public class Espacio {
    private int nivel;
    private String numeracion;

    public Espacio(){

    }

    public Espacio(int nivel, String numeracion) {
        this.nivel = nivel;
        this.numeracion = numeracion;

    }

    public Espacio(int nivel) {
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(String numeracion) {
        this.numeracion = numeracion;
    }


}
