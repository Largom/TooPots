package tooPots.servicio;

import java.util.List;

import tooPots.modelo.Reserva;

public interface ClienteSv {

	public List<Reserva> reservasCliente(int idCliente);
	public void anyadirReserva (int idCliente, Reserva reserva);
	public void eliminarReserva (int idCliente, Reserva reserva); // Siempre antes de 10 dias
	
}
