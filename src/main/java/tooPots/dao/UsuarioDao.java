package tooPots.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tooPots.modelo.Usuario;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioDao {


    private JdbcTemplate jdbcTemplate;


    // Obté el jdbcTemplate a partir del Data Source
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }



    public void nuevoUsuario(Usuario usuario){

        try {
            jdbcTemplate.update("INSERT INTO users VALUES(?,?,?)",
                    usuario.getUsuario(), usuario.getPassword(), usuario.getRole());
        }
        catch(Exception e){
            System.out.println("Clave duplicada");
        }
    }

    public List<Usuario> listaUsuarios(){
        try {
            return jdbcTemplate.query("SELECT * FROM users", new UsuarioRowMapper());
        }
        catch (EmptyResultDataAccessException e) {
            return new ArrayList<Usuario>();
        }
    }

    public Usuario buscaUsuario(String nombre){
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM users WHERE usuario=?", new UsuarioRowMapper(), nombre);
        }
        catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return null;
        }
    }


}
