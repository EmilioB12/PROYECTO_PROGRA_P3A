public class ReservaParqueadero extends Reserva {
    private int tiempoReserva;
    private double precio;
    private int espacioAsignado; // Agregamos un campo para almacenar el espacio asignado

    public ReservaParqueadero(int horasReserva, int dia, int mes, int anio, Parqueadero parqueadero, Vehiculo vehiculo, Persona persona, int tiempoReserva, double precio) {
        super(horasReserva, dia, mes, anio, parqueadero, vehiculo, persona);
        this.tiempoReserva = tiempoReserva;
        this.precio = precio;
        this.espacioAsignado = parqueadero.asignarEspacioDisponible(); // Asignar espacio disponible al crear la reserva
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

    @Override
    public String toString() {
        return  "Tiempo de Reserva: " + tiempoReserva + " horas\n" +
                "Precio a pagar: " + precio + "\n" +
                "Fecha de Reserva: " + getDia() + "/" + getMes() + "/" + getAnio() + "\n" +
                "Parqueadero: " + getParqueadero().getLugar() + "\n" +
                "Vehiculo: " + getVehiculo().getPlaca() + "\n" +
                "Persona: " + getPersona().getNombre() + "\n" +
                "Espacio asignado: " + espacioAsignado;
    }
}
