package tooPots.servicio;

import tooPots.modelo.Certificado;

import java.util.List;

public interface MonitorSv {

    public List<Certificado> certificadosMonitor();
    public void añadircertificadosDeSolicitudes(int id_solicitud, List<Certificado> certificados);
}
