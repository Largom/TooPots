package tooPots.dao;

import tooPots.modelo.Certificado;
import tooPots.modelo.Monitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
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
    public void solicitudMonitor(Monitor monitor) {
        jdbcTemplate.update("INSERT INTO solicitud_monitor VALUES(?, ?, ?, ?, ?,'')",
                monitor.getId_monitor(), monitor.getNombre(), monitor.getNif(), monitor.getEmail(), monitor.getObservaciones());
    }

    //Dar de alta nuevo monitor
    public void añadeMonitor(Monitor monitor) {
        jdbcTemplate.update("INSERT INTO monitor VALUES(?, ?, ?, ?, ?)",
                monitor.getId_monitor(), monitor.getNombre(), monitor.getNif(), monitor.getEmail(), monitor.getCuentaBancaria());
    }


    // Borrado de un monitor
    public void borraMonitor(int monitor) {
        jdbcTemplate.update("DELETE from monitor where id_monitor=?", monitor);
    }

    //Actualización datos monitor
    public void actualizaMonitor(Monitor monitor) {
        jdbcTemplate.update("UPDATE monitor SET nombre=?, nif=?, email=?, cuentabancaria=? where id_monitor=?",
                monitor.getNombre(), monitor.getNif(), monitor.getEmail(), monitor.getCuentaBancaria(), monitor.getId_monitor());
    }

    //Busqueda y seleccion monitor
    public Monitor consultaMonitor(int id_monitor) {
        try {
            return jdbcTemplate.queryForObject("SELECT * from monitor WHERE id_monitor=?",
                    new MonitorRowMapper(), id_monitor);
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

}