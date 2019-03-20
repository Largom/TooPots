package tooPots.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tooPots.modelo.Actividad;

import javax.sql.DataSource;

@Repository
public class ActividadDao {


    private JdbcTemplate jdbcTemplate;


    // Obté el jdbcTemplate a partir del Data Source
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //Buscamos actividad
    public Actividad busquedaActividad(int id_actividad){
        return jdbcTemplate.queryForObject("SELECT * from actividad WHERE id_actividad=?",
                new ActividadRowMapper() , id_actividad);


    }
    //Actualizar actividad
    public void actualizarActividad(Actividad actividad){
        jdbcTemplate.update("UPDATE actividad SET nombre = ?, lugar=?, hora=?, duracion=?, descripcion=?",
                actividad.getNombre(), actividad.getLugar(), actividad.getHora(),
                actividad.getDuracion(), actividad.getDescripcion());

    }

    // Borrado de actividad
    public void borrarActividad(int id_actividad) {
        jdbcTemplate.update("DELETE from actividad where id_actividad=?", id_actividad);
    }

}
