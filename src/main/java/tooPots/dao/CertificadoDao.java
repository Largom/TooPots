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
}
