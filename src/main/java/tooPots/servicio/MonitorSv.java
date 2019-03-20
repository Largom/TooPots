package tooPots.servicio;

import tooPots.modelo.Certificado;
import tooPots.modelo.Monitor;

import java.util.List;
import java.util.Map;

public interface MonitorSv {

    public List<Certificado> certificadosMonitor();
    public void añadircertificadosDeSolicitudes(int id_solicitud, List<String> certificados);
    public void añadircertificadosDeMonitor(int id_monitor, List<Certificado> certificados);
    public Map<Monitor, List<Certificado>> getcertificadosSolicitud();
    public List<Certificado> getcertificadosSolicitud(int id_solicitud);
}
