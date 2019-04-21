package tooPots.dao;

import org.springframework.jdbc.core.RowMapper;
import tooPots.modelo.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioRowMapper implements RowMapper<Usuario> {


    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {

        Usuario usuario= new Usuario();

        usuario.setUsuario(rs.getString("usuario"));
        usuario.setPassword(rs.getString("password"));
        Usuario.Role tipo = usuario.tipoRole(rs.getString("role"));
        usuario.setRole(tipo);

        return usuario;
    }

}
