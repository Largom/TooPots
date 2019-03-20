package tooPots.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tooPots.modelo.Reserva;

import javax.sql.DataSource;

@Repository
public class ReservaDao {


    private JdbcTemplate jdbcTemplate;


    // Obté el jdbcTemplate a partir del Data Source
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    //Buscamos id solicitud monitor
    public Reserva busquedaReserva(int id_reserva){
        return jdbcTemplate.queryForObject("SELECT * from reserva WHERE id_reserva=?",
                new ReservaRowMapper() , id_reserva);


    }
    //Actualizar reserva
    public void actualizarReserva(Reserva reserva){
        jdbcTemplate.update("UPDATE reserva SET asistentes = ?, preciofinal=?, preciototal=?, numtransaccion=?, observaciones=?, estado=?",
                reserva.getAsistentes(), reserva.getPrecioFinal(), reserva.getPrecioTotal(), reserva.getNumTransaccion(),
                reserva.getObservaciones(), reserva.getEstado());
    }

    // Borrado de reserva
    public void borrarReserva(int id_reserva) {
        jdbcTemplate.update("DELETE from resera where id_reserva=?", id_reserva);
    }

}
