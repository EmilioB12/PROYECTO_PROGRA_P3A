public class ReservaParqueadero extends Reserva {
    private int tiempoReserva;
    private double precio;

    public ReservaParqueadero(int horasReserva, String dia, Parqueadero parqueadero, Vehiculo vehiculo, Persona persona, int tiempoReserva, double precio) {
        super(horasReserva, dia, parqueadero, vehiculo, persona);
        this.tiempoReserva = tiempoReserva;
        this.precio = precio;
    }

    public int getTiempoReserva() {
        return tiempoReserva;
    }

    public void setTiempoReserva(int tiempoReserva) {
        this.tiempoReserva = tiempoReserva;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


}
