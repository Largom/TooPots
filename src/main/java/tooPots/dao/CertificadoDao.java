package tooPots.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tooPots.modelo.Certificado;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CertificadoDao {

    private JdbcTemplate jdbcTemplate;


    // Obté el jdbcTemplate a partir del Data Source
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    //Lista certificados
    public List<Certificado> listaCertificados() {
        try {
            return jdbcTemplate.query("SELECT * from certificados",
                    new CertificadoRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Certificado>();
        }
    }

    //Añadir certificados a las solicitudes
    public void añadirCertificadoSolicitud(Certificado c, int id_solicitud) {
        jdbcTemplate.update("INSERT INTO certificados_solicitudes VALUES(?, ?)",
                c.getId_certificado() , id_solicitud);
    }

    //Añadir certificados a los monitores
    public void añadirCertificadoMonitor(Certificado c, int id_monitor) {
        jdbcTemplate.update("INSERT INTO certificados_monitor VALUES(?, ?)",
                c.getId_certificado() , id_monitor);
    }

    //Busqueda y seleccion de certicados de un monitor
    public Certificado buscaCertificado(String nombre) {
        try {
            return jdbcTemplate.queryForObject("SELECT * from certificados WHERE id_certificado=?",
                    new CertificadoRowMapper(), nombre);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //Busqueda y seleccion de certificados de un monitor
    public List<Certificado> consultaCertificadosMonitor(String id_monitor) {
        try {
            return jdbcTemplate.query("SELECT * from certificados_monitor WHERE id_monitor=?",
                    new CertificadoRowMapper(), id_monitor);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Certificado>();
        }
    }
    //Busqueda y seleccion de certificados de una solicitud de monitor
    public List<Certificado> consultaCertificadosSolicitud(int id_monitor) {
        try {
            List<Certificado> resultado = new ArrayList<>();
            List<Certificado> codigos = jdbcTemplate.query("SELECT * from certificados_solicitudes WHERE id_monitor=?",
                    new CertificadoRowMapper(), id_monitor);
            for(Certificado c: codigos)
                resultado.add(buscaCertificado(c.getId_certificado()));
            return resultado;


        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Certificado>();
        }
    }



}
