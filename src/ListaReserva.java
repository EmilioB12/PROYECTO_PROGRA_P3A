import java.util.ArrayList;
import java.util.List;

public class ListaReserva {
    private List<Reserva> reservas;

    public ListaReserva() {
        this.reservas = new ArrayList<>();
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Reserva buscarReserva(String placaVehiculo, String nombrePersona) {
        for (Reserva reserva : reservas) {
            if (reserva.getVehiculo().getPlaca().equals(placaVehiculo) &&
                    reserva.getPersona().getNombre().equals(nombrePersona)) {
                return reserva;
            }
        }
        return null;
    }

    public List<Reserva> listarReservas() {
        List<Reserva> lista = new ArrayList<>();
        for (Reserva reserva : reservas) {
            lista.add(reserva);
        }
        return lista;
    }

    public void agregarReserva(Reserva reserva) throws Exception {
        if (buscarReserva(reserva.getVehiculo().getPlaca(), reserva.getPersona().getNombre()) == null) {
            reservas.add(reserva);
        } else {
            throw new Exception("La reserva para el vehículo con placa " + reserva.getVehiculo().getPlaca() +
                    " y persona " + reserva.getPersona().getNombre() + " ya existe.");
        }
    }

    public void eliminarReserva(String placaVehiculo, String nombrePersona) throws Exception {
        Reserva reserva = buscarReserva(placaVehiculo, nombrePersona);
        if (reserva != null) {
            reservas.remove(reserva);
        } else {
            throw new Exception("No se encontró ninguna reserva para el vehículo con placa " +
                    placaVehiculo + " y persona " + nombrePersona);
        }
    }

    public void editarReserva(String placaVehiculo, String nombrePersona, int nuevasHorasReserva) throws Exception {
        Reserva reserva = buscarReserva(placaVehiculo, nombrePersona);
        if (reserva != null) {
            reserva.setHorasReserva(nuevasHorasReserva);
        } else {
            throw new Exception("No se encontró ninguna reserva para el vehículo con placa " +
                    placaVehiculo + " y persona " + nombrePersona);
        }
    }

    public double calcularCostoReserva(Reserva reserva) {
        // Lógica para calcular el costo de la reserva, por ejemplo:
        double costoBase = 5.0; // Costo base por hora
        return costoBase * reserva.getHorasReserva();
    }

    public int obtenerPuestosLibres(Parqueadero parqueadero) {
        // Obtener la cantidad de puestos libres en el parqueadero
        int puestosOcupados = 0;
        for (Reserva reserva : reservas) {
            if (reserva.getParqueadero().equals(parqueadero)) {
                puestosOcupados++;
            }
        }
        return parqueadero.getCantidadEspacio() - puestosOcupados;
    }
}
