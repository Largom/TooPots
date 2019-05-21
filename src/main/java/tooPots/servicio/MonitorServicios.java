package tooPots.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tooPots.dao.ActividadDao;
import tooPots.dao.CertificadoDao;
import tooPots.dao.MonitorDao;
import tooPots.modelo.Certificado;
import tooPots.modelo.Clasificacion;
import tooPots.modelo.Monitor;

import javax.rmi.CORBA.ClassDesc;
import java.util.*;


@Service
public class MonitorServicios implements MonitorSv{

    @Autowired
    private CertificadoDao certificadodao;
    @Autowired
    private MonitorDao monitordao;
    @Autowired
    private ActividadDao actividaddao;


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


    public Map<Integer, List<Certificado>> getcertificadosSolicitud(){

        List<Monitor> solicitudesMonitores = monitordao.listaSolicitudesMonitor();
        Map<Integer, List<Certificado>> certs = new HashMap<>();
        for(Monitor m : solicitudesMonitores) {
            if(!certificadodao.consultaCertificadosSolicitud(m.getId_monitor()).isEmpty())
                certs.put(m.getId_monitor(), certificadodao.consultaCertificadosSolicitud(m.getId_monitor()));
        }
        return certs;
    }

    public Map<Integer, List<Certificado>> getcertificadosMonitores(){

        List<Monitor> monitores = monitordao.listaMonitores();
        Map<Integer, List<Certificado>> certs = new HashMap<>();
        for(Monitor m : monitores) {
            if(!certificadodao.consultaCertificadosMonitor(m.getId_monitor()).isEmpty())
                certs.put(m.getId_monitor(), certificadodao.consultaCertificadosMonitor(m.getId_monitor()));
        }
        return certs;
    }

    public List<Certificado> getcertificadosSolicitud(int id_solicitud){
        return certificadodao.consultaCertificadosSolicitud(id_solicitud);
    }


    public List<String> getTiposActividad(){
        return actividaddao.getTiposActividad();
    }

    public List<String> getNiveles(){
        return actividaddao.getNiveles();
    }

    public void añadirClasificacion(int id_monitor, String tipo, int nivel){
         monitordao.añadirClasificacion(id_monitor, tipo, nivel);
    }

    public void añadirClasificacionAMonitor(int monitor, List<Clasificacion> clasificaciones){

        monitordao.añadirClasificacionesMonitor(monitor, clasificaciones);
    }

    public void añadirClasificacionMonitor(int id_monitor, String tipo, int nivel){

        monitordao.añadirClasificacionM(id_monitor, tipo, nivel);

    }

    public List<Clasificacion> getClasificacion(int id_monitor){

        List<Clasificacion> clasificacionDescripcion = new LinkedList();
        List<Clasificacion> clasificacionesID = monitordao.getClasificacion(id_monitor);
        for (Clasificacion clas: clasificacionesID) {
            Clasificacion cDescripcion = new Clasificacion();
            String tipoDescripcion = monitordao.getDescripcionTipo(clas.getId_tipo());
            String nivelDescripcion = monitordao.getDescripcionNivel(clas.getId_nivel());
            cDescripcion.setId_tipo(clas.getId_tipo());
            cDescripcion.setDescripcion_tipo(tipoDescripcion);
            cDescripcion.setId_nivel(clas.getId_nivel());
            cDescripcion.setDescripcion_nivel(nivelDescripcion);
            clasificacionDescripcion.add(cDescripcion);
        }

        return clasificacionDescripcion;
    }

    public List<Clasificacion> getClasificacionM(int id_monitor){

        List<Clasificacion> clasificacionDescripcion = new LinkedList();
        List<Clasificacion> clasificacionesID = monitordao.getClasificacionMonitor(id_monitor);
        for (Clasificacion clas: clasificacionesID) {
            Clasificacion cDescripcion = new Clasificacion();
            String tipoDescripcion = monitordao.getDescripcionTipo(clas.getId_tipo());
            String nivelDescripcion = monitordao.getDescripcionNivel(clas.getId_nivel());
            cDescripcion.setId_tipo(clas.getId_tipo());
            cDescripcion.setDescripcion_tipo(tipoDescripcion);
            cDescripcion.setId_nivel(clas.getId_nivel());
            cDescripcion.setDescripcion_nivel(nivelDescripcion);
            clasificacionDescripcion.add(cDescripcion);
        }

        return clasificacionDescripcion;
    }

    public Map<Integer,List<Clasificacion>> getClasificacionesMonitores() {

        List<Monitor> monitores = monitordao.listaMonitores();
        Map<Integer, List<Clasificacion>> certificadosMonitores = new HashMap<>();

        try {
            for (Monitor m : monitores)
                certificadosMonitores.put(m.getId_monitor(), this.getClasificacionM(m.getId_monitor()));
            return certificadosMonitores;
        } catch (Exception e) {
            return null;
        }
    }

    public void borrarClasificaciones(int monitor){
        monitordao.borrarClasificaciones(monitor);
    }
}
