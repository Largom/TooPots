package tooPots.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tooPots.modelo.Cliente;
import tooPots.modelo.Monitor;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

@Repository
public class ClienteDao {


    private JdbcTemplate jdbcTemplate;


    // Obté el jdbcTemplate a partir del Data Source
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    //Anyadimos Cliente
    public void anyadeCliente(Cliente cliente) {
        jdbcTemplate.update("INSERT INTO cliente(nombre, dni, sexo, anyo_nacimiento, email) VALUES(?, ?, ?, ?, ?)",
        		cliente.getNombre(), cliente.getnif(),
        		cliente.getSexo(), cliente.getanyo_nacimiento(), cliente.getEmail());
    }


    //Buscamos id cliente
    public Cliente busquedaCliente(int id_cliente){
        return jdbcTemplate.queryForObject("SELECT * from cliente WHERE id_cliente=?",
                new ClienteRowMapper() , id_cliente);


    }
     
    
    //Actualizar cliente
    public void actualizarCliente(Cliente cliente){
        jdbcTemplate.update("UPDATE cliente SET nombre = ?, nif=?, sexo=?, anyo_nacimiento=?, email=?",
                        cliente.getNombre(), cliente.getnif(), cliente.getSexo(), cliente.getanyo_nacimiento(),
                        cliente.getEmail());

    }

    // Borrado de cliente
    public void borrarCliente(int id_cliente) {
        jdbcTemplate.update("DELETE from cliente where id_cliente=?", id_cliente);
    }

    
    //Lista clientes
    public List<Cliente> listaClientes() {
        try {
            return jdbcTemplate.query("SELECT * from cliente", new ClienteRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Cliente>();
        }
    }

}
