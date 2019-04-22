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

    public int cantidadSolicitudes(){return 3;}
    public int cantidadMonitores(){return 3;}
    public int cantidadActividades(){return 3;}
    public int cantidadActividadesAltaMonitor(int id_monitor){return 3;}
    public int cantidadReservasOK(){return 3;}
    public int cantidadReservasKO(){return 3;}
    public int cantidadReservasPorRevisar(int id_monitor){return 3;}
    public int cantidadReservasMonitorOK(int id_monitor){return 3;}
    public int cantidadReservasMonitorKO(int id_monitor){return 3;}
    public int cantidadReservasClienteOK(int id_cliente){return 3;}
    public int cantidadReservasClienteKO(int id_cliente){return 3;}


}
