package tooPots.dao;

import org.springframework.jdbc.core.RowMapper;
import tooPots.modelo.Cliente;
import tooPots.modelo.Reserva;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservaRowMapper implements RowMapper<Reserva> {

    public Reserva mapRow(ResultSet rs, int rowNum) throws SQLException {

        Reserva reserva = new Reserva();

        reserva.setId_reserva(rs.getString("id_reserva"));
        reserva.setId_actividad(rs.getInt("id_actividad"));
        reserva.setId_cliente(rs.getInt("id_cliente"));
        reserva.setAsistentes(rs.getInt("asistentes"));
        reserva.setPrecioPersona(rs.getInt("preciopersona"));
        reserva.setPrecioTotal(rs.getInt("preciototal"));
        try {
            reserva.setNumTransaccion(rs.getInt("numtrasaccion"));
            reserva.setObservaciones(rs.getString("observaciones"));
            reserva.setEstado(rs.getString("estado"));
            reserva.setEstado(rs.getString("nombreActividad"));
        }
        catch (Exception e){}

        return reserva;
    }
}
