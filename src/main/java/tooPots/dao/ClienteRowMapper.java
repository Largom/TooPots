package tooPots.dao;

import org.springframework.jdbc.core.RowMapper;
import tooPots.modelo.Cliente;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteRowMapper implements RowMapper<Cliente> {

    public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {

        Cliente cliente = new Cliente();

        cliente.setNombre(rs.getString("nombre"));
        cliente.setnif(rs.getString("dni"));

        try {
            cliente.setSexo(rs.getString("sexo"));
            cliente.setanyo_nacimiento((rs.getInt("añonacimiento")));
            cliente.setEmail(rs.getString("email"));
        }
        catch (Exception e){}

        return cliente;
    }
}


