package tooPots.dao;

import org.springframework.jdbc.core.RowMapper;
import tooPots.modelo.Cliente;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteRowMapper implements RowMapper<Cliente> {

    public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {

        Cliente cliente = new Cliente();
        cliente.setId_cliente(rs.getString("id_cliente"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setDni(rs.getString("dni"));
        cliente.setEmail(rs.getString("email"));
        try {
            cliente.setSexo(rs.getString("sexo"));
            cliente.setAñoNacimiento(rs.getInt("añonacimiento"));
        }
        catch (Exception e){}

        return cliente;
    }
}
