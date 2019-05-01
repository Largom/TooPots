package tooPots.dao;

import org.springframework.jdbc.core.RowMapper;
import tooPots.modelo.Actividad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

public class ActividadRowMapper implements RowMapper<Actividad> {

    public Actividad mapRow(ResultSet rs, int rowNum) throws SQLException {

        Actividad actividad = new Actividad();

        actividad.setId_actividad(rs.getInt("id_actividad"));
        actividad.setNombre(rs.getString("nombre"));
        actividad.setLugar(rs.getString("lugar"));
        try {
            //actividad.setHora(rs.getObject("hora", LocalTime.class));
            //actividad.setDuracion(rs.getObject("duracion", LocalTime.class));
            actividad.setDescripcion(rs.getString("descripcion"));
            actividad.setPrecio(rs.getInt("precio"));
            actividad.setAsistentesMinimos(rs.getInt("asistentesminimos"));
            actividad.setAsistentesMaximos(rs.getInt("asistentesmaximos"));
            actividad.setObservaciones(rs.getString("observaciones"));
            actividad.setEstado(rs.getString("estado"));

        }
        catch (Exception e){}

        return actividad;
    }
}
