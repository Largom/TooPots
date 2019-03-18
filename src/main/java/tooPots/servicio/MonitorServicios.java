package tooPots.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tooPots.dao.CertificadoDao;
import tooPots.dao.MonitorDao;
import tooPots.modelo.Certificado;
import java.util.List;


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

    public void añadircertificadosDeSolicitudes(int id_solicitud, List<Certificado> certificados){

        for(Certificado c: certificados){
            certificadodao.añadirCertificadoSolicitud(c, id_solicitud);}
    }

}
