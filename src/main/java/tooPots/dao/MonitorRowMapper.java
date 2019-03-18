package tooPots.dao;

import tooPots.modelo.Monitor;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MonitorRowMapper implements RowMapper<Monitor>{

    public Monitor mapRow(ResultSet rs, int rowNum) throws SQLException {

        Monitor monitor = new Monitor();
        monitor.setId_monitor(rs.getInt("id_monitor"));
        monitor.setNombre(rs.getString("nombre"));
        monitor.setNif(rs.getString("nif"));
        monitor.setEmail(rs.getString("email"));
        monitor.setCuentaBancaria(rs.getString("cuentabancaria"));
        return monitor;
    }



}




