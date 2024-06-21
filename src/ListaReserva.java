import java.util.ArrayList;
import java.util.List;

public class ListaReserva {
    private List<Reserva> reservas;

    public ListaReserva() {
        this.reservas = new ArrayList<>();
    }

    public void agregarReserva(Reserva reserva) {
        if (reserva.asignarEspacio()) {
            reservas.add(reserva);
            System.out.println("Reserva agregada con éxito.");
        } else {
            System.out.println("No se pudo agregar la reserva. No hay espacios disponibles.");
        }
    }

    public void mostrarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas.");
        } else {
            for (Reserva reserva : reservas) {
                System.out.println("Reserva para el día " + reserva.getDia() +
                        ", Horas: " + reserva.getHorasReserva() +
                        ", Parqueadero: " + reserva.getParqueadero().getLugar() +
                        ", Vehículo: " + reserva.getVehiculo().getPlaca() +
                        ", Persona: " + reserva.getPersona().getNombre());
            }
        }
    }
}
