package tooPots.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tooPots.dao.ClienteDao;
import tooPots.dao.ReservaDao;
import tooPots.modelo.Reserva;
import tooPots.servicio.ClienteSv;

@Service
public class ClienteServicios implements ClienteSv {


    @Autowired
    private ClienteDao clientedao;
    @Autowired
    private ReservaDao reservadao;


    @Override
    public List<Reserva> reservasCliente(int id_cliente) {
        List<Reserva> reservas= reservadao.listaReservas(id_cliente);
        return reservas;
    }

    @Override
    public void anyadirReserva(int idClienteReserva, Reserva reserva) {
        // TODO Auto-generated method stub

    }

    @Override
    public void eliminarReserva(int idCliente, Reserva reserva) {
        // TODO Auto-generated method stub

    }

}

