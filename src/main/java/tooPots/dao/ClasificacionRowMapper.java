package tooPots.dao;

import org.springframework.jdbc.core.RowMapper;
import tooPots.modelo.Clasificacion;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClasificacionRowMapper implements RowMapper<Clasificacion> {


    public Clasificacion mapRow(ResultSet rs, int rowNum) throws SQLException {

        Clasificacion clasificacion = new Clasificacion();
        clasificacion.setId_tipo(rs.getString("id_tipo"));
        clasificacion.setId_nivel(rs.getInt("id_nivel"));


        return clasificacion;
    }
}
