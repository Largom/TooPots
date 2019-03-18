package tooPots.dao;

import tooPots.modelo.Certificado;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CertificadoRowMapper implements RowMapper<Certificado>{

    public Certificado mapRow(ResultSet rs, int rowNum) throws SQLException {

        Certificado certificado = new Certificado();
        certificado.setId_certificado(rs.getString("id_certificado"));
        certificado.setNombre(rs.getString("nombre"));
        certificado.setDescripcion(rs.getString("descripcion"));
        return certificado;
    }

}

