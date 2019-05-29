package tooPots.servicio;

import org.springframework.beans.factory.annotation.Autowired;

import tooPots.dao.ActividadDao;
import tooPots.dao.ReservaDao;
import tooPots.modelo.Reserva;

public class ReservaServicios implements ReservaSv {
	
    @Autowired
    private ActividadDao actividaddao;
    @Autowired
    private ReservaDao reservadao;

    
	public String nombreActividad(Reserva reserva) {
		
		return (actividaddao.busquedaActividad(reserva.getId_actividad())).getNombre();
		
	}
    

}
