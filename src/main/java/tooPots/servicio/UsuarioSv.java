package tooPots.servicio;

import tooPots.modelo.Usuario;

public interface UsuarioSv {

    public void altaUsuario(Usuario user);
    public int cantidadSolicitudes();
    public int cantidadMonitores();
    public int cantidadActividades();
    public int cantidadActividadesAltaMonitor(int id_monitor);
    public int cantidadReservasOK();
    public int cantidadReservasKO();
    public int cantidadReservasPorRevisar(int id_monitor);
    public int cantidadReservasMonitorOK(int id_monitor);
    public int cantidadReservasMonitorKO(int id_monitor);
    public int cantidadReservasClienteOK(int id_cliente);
    public int cantidadReservasClienteKO(int id_cliente);
}
