package tooPots.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tooPots.dao.CertificadoDao;
import tooPots.dao.MonitorDao;
import tooPots.modelo.Certificado;
import tooPots.modelo.Monitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class MonitorServicios implements MonitorSv{

    @Autowired
    private CertificadoDao certificadodao;
    @Autowired
    private MonitorDao monitordao;


    public List<Certificado> certificadosMonitor(){
       List<Certificado> certificados = certificadodao.listaCertificados();
       return certificados;
    }

    public void añadircertificadosDeSolicitudes(int id_solicitud, List<String> certificados){

        for(String c: certificados){
            certificadodao.añadirCertificadoSolicitud(certificadodao.buscaCertificado(c), id_solicitud);}
    }

    public void añadircertificadosDeMonitor(int id_monitor, List<Certificado> certificados){

        for(Certificado c: certificados){
            certificadodao.añadirCertificadoMonitor(c, id_monitor);}
    }

    public Map<Monitor, List<Certificado>> getcertificadosSolicitud(){

        List<Monitor> solicitudesMonitores = monitordao.listaSolicitudesMonitor();
        Map<Monitor, List<Certificado>> certs = new HashMap<Monitor, List<Certificado>>();
        for(Monitor m : solicitudesMonitores) {
            certs.put(m, certificadodao.consultaCertificadosSolicitud(m.getId_monitor()));
        }
        return certs;
    }

    public List<Certificado> getcertificadosSolicitud(int id_solicitud){
        return certificadodao.consultaCertificadosSolicitud(id_solicitud);
    }


}
