package tooPots.dao;

import tooPots.modelo.Certificado;
import tooPots.modelo.Monitor;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MonitorRowMapper implements RowMapper<Monitor>{

    public Monitor mapRow(ResultSet rs, int rowNum) throws SQLException {

        Monitor monitor = new Monitor();
        monitor.setId_monitor(rs.getInt("id_monitor"));
        monitor.setNombre(rs.getString("nombre"));
        monitor.setNif(rs.getString("nif"));
        monitor.setEmail(rs.getString("email"));
        monitor.setObservaciones(rs.getString("observaciones"));
        List<String> certificados = new ArrayList<String>();
        if(certificados.size()>0) {
            for (String certificado : (String[]) rs.getArray("certificados").getArray()) {
                certificados.add(certificado);
            }
        }
        monitor.setCertificados(certificados);
        try {
            monitor.setCuentaBancaria(rs.getString("cuentabancaria"));
        }
        catch (Exception e){}
        return monitor;
    }



}




