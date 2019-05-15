package tooPots.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tooPots.modelo.Actividad;
import tooPots.modelo.Cliente;
import tooPots.modelo.Monitor;

import javax.sql.DataSource;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
    	
    	System.out.println("estoy buscando: " + id_actividad);
    	
        Actividad actividad = jdbcTemplate.queryForObject("SELECT * from actividad WHERE id_actividad=?", 
                new ActividadRowMapper(), id_actividad);
        
        return actividad;


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

    // Listar actividades
    public List<Actividad> listaActividad() {
        try {
            return jdbcTemplate.query("SELECT * from actividad", new ActividadRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Actividad>();
        }
    }
    

    // Crear actividad
    public void addActividad(Actividad actividad) {
    		jdbcTemplate.update("INSERT INTO actividad (nombre, lugar, hora, duracion, descripcion, precio, asistentesMinimos, asistentesMaximos, observaciones, estado, clasificacion)"
    				+ " VALUES(?, ?, ?, ?,?, ?, ? , ?,?, ?, ?)"
    				, actividad.getNombre(), actividad.getLugar(), actividad.getHora(), actividad.getDuracion(), actividad.getDescripcion(), actividad.getPrecio(),
    				actividad.getAsistentesMaximos(), actividad.getAsistentesMinimos(), actividad.getObservaciones(), actividad.getEstado(), actividad.getClasificacion());
    }


    public List<String> getTipoActividad(){

        List<String> actividad =  jdbcTemplate.queryForList("SELECT descripcion from tipo_actividad", String.class);

        return actividad;
    }


    public List<String> getNiveles(){

        List<String> niveles =  jdbcTemplate.queryForList("SELECT descripcion from tipo_nivel", String.class);

        return niveles;
    }

}
