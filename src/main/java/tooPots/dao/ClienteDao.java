package tooPots.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tooPots.modelo.Cliente;

import javax.sql.DataSource;

@Repository
public class ClienteDao {


    private JdbcTemplate jdbcTemplate;


    // Obté el jdbcTemplate a partir del Data Source
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    //Buscamos id solicitud monitor
    public Cliente busquedaCliente(int id_cliente){
        return jdbcTemplate.queryForObject("SELECT * from cliente WHERE id_cliente=?",
                new ClienteRowMapper() , id_cliente);


    }
    //Actualizar cliente
    public void actualizarCliente(Cliente cliente){
        jdbcTemplate.update("UPDATE cliente SET nombre = ?, dni=?, sexo=?, añonacimiento=?, email=?",
                        cliente.getNombre(), cliente.getDni(), cliente.getSexo(), cliente.getAñoNacimiento(),
                        cliente.getEmail());

    }

    // Borrado de cliente
    public void borrarCliente(int id_cliente) {
        jdbcTemplate.update("DELETE from cliente where id_cliente=?", id_cliente);
    }


}
