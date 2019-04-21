package tooPots.dao;

import tooPots.modelo.Certificado;
import tooPots.modelo.Clasificacion;
import tooPots.modelo.Monitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class MonitorDao {

    private JdbcTemplate jdbcTemplate;


    // Obté el jdbcTemplate a partir del Data Source
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //Dar de alta nueva solicitud de monitor
    public void nuevasolicitudMonitor(Monitor monitor) {
        jdbcTemplate.update("INSERT INTO solicitud_monitor (nombre, nif, email, observaciones) VALUES(?, ?, ?, ?)", monitor.getNombre(), monitor.getNif(), monitor.getEmail(), monitor.getObservaciones());
    }

    //Buscamos id solicitud monitor
    public int busquedaID(Monitor monitor) {
        Monitor m = jdbcTemplate.queryForObject("SELECT * from solicitud_monitor WHERe nif=?",
                new MonitorRowMapper(), monitor.getNif());
        return m.getId_monitor();

    }

    //Buscamos solicitud monitor
    public Monitor busquedaSolicitud(int id_monitor) {
        return jdbcTemplate.queryForObject("SELECT * from solicitud_monitor WHERE id_monitor=?",
                new MonitorRowMapper(), id_monitor);
    }


    //Dar de alta nuevo monitor
    public void añadeMonitor(Monitor monitor) {
        jdbcTemplate.update("INSERT INTO monitor VALUES(?, ?, ?, ?, ?, ?)",
                monitor.getId_monitor(), monitor.getNombre(),
                monitor.getNif(), monitor.getEmail(), monitor.getCuentaBancaria(), monitor.getObservaciones());
    }


    // Borrado de un monitor
    public void borraMonitor(int monitor) {
        jdbcTemplate.update("DELETE from monitor where id_monitor=?", monitor);
    }

    // Borrado de un monitor en una solicitud
    public void borrarSolicitud(int monitor) {
        jdbcTemplate.update("DELETE from solicitud_monitor where id_monitor=?", monitor);
    }


    //Actualización datos monitor
    public void actualizaMonitor(Monitor monitor) {
        jdbcTemplate.update("UPDATE monitor SET nombre=?, nif=?, email=?, cuentabancaria=?, observaciones=? where id_monitor=?",
                monitor.getNombre(), monitor.getNif(), monitor.getEmail(),
                monitor.getCuentaBancaria(), monitor.getObservaciones(), monitor.getId_monitor());
    }

    //Busqueda y seleccion monitor en MONITOR
    public Monitor consultaMonitor(int id_monitor) {
        try {
            return jdbcTemplate.queryForObject("SELECT * from monitor WHERE id_monitor=?",
                    new MonitorRowMapper(), id_monitor);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //Busqueda y seleccion monitor por correo electronico
    public Monitor consultaMonitor(String correo) {
        try {
            return jdbcTemplate.queryForObject("SELECT * from monitor WHERE email=?",
                    new MonitorRowMapper(), correo);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //Lista monitores
    public List<Monitor> listaMonitores() {
        try {
            return jdbcTemplate.query("SELECT * from monitor",
                    new MonitorRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Monitor>();
        }
    }

    //Lista solicitudes de monitores
    public List<Monitor> listaSolicitudesMonitor() {
        try {
            return jdbcTemplate.query("SELECT * from solicitud_monitor",
                    new MonitorRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Monitor>();
        }
    }
  /* ##########............... Metodos gestion clasificaciones ..........#########*/

    public void añadirClasificacion(int monitor, String tipo, int nivel) {
        jdbcTemplate.update("INSERT INTO categoria_solicitudes_monitor VALUES (?,?,?)", monitor, tipo, nivel);
    }

    public void borrarClasificaciones(int monitor){

        jdbcTemplate.update("DELETE FROM categoria_solicitudes_monitor WHERE id_monitor=?",monitor);

    }

    public void añadirClasificacionesMonitor(int monitor, List<Clasificacion> clasificaciones){

        for(Clasificacion c: clasificaciones){
            jdbcTemplate.update("INSERT INTO categoria_monitor VALUES(?, ?, ?)",monitor, c.getId_tipo(), c.getId_nivel());
        }
    }


    public List<Clasificacion> getClasificacion(int id_monitor) {
        try {
            List<Clasificacion> clasificacion = jdbcTemplate.query("SELECT * FROM categoria_solicitudes_monitor where id_monitor=?", new ClasificacionRowMapper(), id_monitor);
            return clasificacion;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Clasificacion> getClasificacionMonitor(int id_monitor) {
        try {
            List<Clasificacion> clasificacion = jdbcTemplate.query("SELECT * FROM categoria_monitor where id_monitor=?", new ClasificacionRowMapper(), id_monitor);
            return clasificacion;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int getIDNivel(String nom) {
        String nivel = (String) jdbcTemplate.queryForObject("SELECT id_nivel FROM tipo_nivel where descripcion=?", String.class, nom);
        return Integer.parseInt(nivel);
    }

    public String getIDTipo(String nom){
        return (String)jdbcTemplate.queryForObject("SELECT id_tipo FROM tipo_actividad where descripcion=?", String.class, nom);
    }

    public String getDescripcionNivel(int nivel){
        return (String) jdbcTemplate.queryForObject("SELECT descripcion FROM tipo_nivel where id_nivel=?", String.class, nivel);

    }
    public String getDescripcionTipo(String tipo){
        return (String) jdbcTemplate.queryForObject("SELECT descripcion FROM tipo_actividad where id_tipo=?", String.class, tipo);
    }
}