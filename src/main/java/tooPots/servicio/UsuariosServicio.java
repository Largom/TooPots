package tooPots.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tooPots.dao.UsuarioDao;
import tooPots.modelo.Usuario;

@Service
public class UsuariosServicio implements UsuarioSv{

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public void altaUsuario(Usuario user) {
        usuarioDao.nuevoUsuario(user);
    }
}
