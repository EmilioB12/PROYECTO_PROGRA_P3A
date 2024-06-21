import java.time.LocalDate;

public class Reserva {
    private int horasReserva;
    private String Fecha;

    private Parqueadero parqueadero;
    private Vehiculo vehiculo;
    private Persona persona;

    public Reserva(int horasReserva, Parqueadero parqueadero, Vehiculo vehiculo, Persona persona) {
        this.horasReserva = horasReserva;
        this.Fecha = LocalDate.now().toString();
        this.parqueadero = parqueadero;
        this.vehiculo = vehiculo;
        this.persona = persona;
    }

    public int getHorasReserva() {
        return horasReserva;
    }

    public void setHorasReserva(int horasReserva) {
        this.horasReserva = horasReserva;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public Parqueadero getParqueadero() {
        return parqueadero;
    }

    public void setParqueadero(Parqueadero parqueadero) {
        this.parqueadero = parqueadero;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return  "\nHoras de Reserva: " + horasReserva +
                "\nFecha: " + Fecha +
                "\nNombre del Parqueadero: " + parqueadero.getLugar() +
                "\nNivel del Parqueadero: " + parqueadero.getEspacio().getNivel() +
                "\nNumeracion del Parqueadero: " + parqueadero.getEspacio().getNumeracion() +
                "\nPlaca del Vehiculo: " + vehiculo.getPlaca() +
                "\nTipo de Vehiculo: " + vehiculo.getTipoVehiculo() +
                "\nNombre de la persona: " + persona.getNombre() +
                "\nID de la persona: " + persona.getId() +
                "\nTipo de la persona: " + persona.getTipoPersona();
    }
}
