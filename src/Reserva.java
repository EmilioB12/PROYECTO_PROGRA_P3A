public class Reserva {
    protected int horasReserva;

    protected int dia;
    protected int mes;
    protected int anio;
    protected Parqueadero parqueadero;
    protected Vehiculo vehiculo;
    protected Persona persona;

    public Reserva(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public Reserva(int horasReserva, int dia, int mes, int anio, Parqueadero parqueadero, Vehiculo vehiculo, Persona persona) {
        this.horasReserva = horasReserva;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
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

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
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
}
