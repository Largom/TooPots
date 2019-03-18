package tooPots.controlador;


import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import tooPots.dao.CertificadoDao;
import tooPots.dao.MonitorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tooPots.modelo.Certificado;
import tooPots.modelo.Monitor;
import tooPots.servicio.MonitorSv;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/monitor")
public class MonitorController {


    private MonitorDao monitorDao;

    @Autowired
    private MonitorSv monitorsv;

    @Autowired
    public void setMonitor(MonitorDao monitorDao) {
        this.monitorDao = monitorDao;
    }


    @RequestMapping(value="/consulta/{identificador}")
    public String consultaMonitor(Model model, @PathVariable int identificador) {
        model.addAttribute("monitor", monitorDao.consultaMonitor(identificador));
        return "monitor/listar";
    }

    @RequestMapping("/listar")
    public String listadoMonitores(Model model) {
        model.addAttribute("monitores", monitorDao.listaMonitores());
        model.addAttribute("solicitudesPendientes", "false");
        return "monitor/listar";
    }


    @RequestMapping("/pendientes")
    public String consultaSolicitudesMonitor(Model model) {
        model.addAttribute("monitores", monitorDao.listaSolicitudesMonitor());
        model.addAttribute("solicitudesPendientes", "true");
        return "monitor/listar";
    }

    //Añadir solicitud de monitor
    @RequestMapping("/solicitud")
    public String SolicitudMonitor(Model model) {
        model.addAttribute("monitor", new Monitor());
        model.addAttribute("certificados", monitorsv.certificadosMonitor());
        model.addAttribute("certificar", new ArrayList<Certificado>());
        return "monitor/solicitudes";
    }

    @RequestMapping(value="/solicitud", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("monitor") Monitor monitor,
                                   @ModelAttribute("certificar") ArrayList<Certificado> certificados,
                                   BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "monitor/solicitud";
        }
        monitorDao.solicitudMonitor(monitor);
        monitorsv.añadircertificadosDeSolicitudes(monitor.getId_monitor(), certificados);

        return "../index";

    }
    //Actualiza monitor
    @RequestMapping(value="/actualiza/{id_monitor}", method = RequestMethod.GET)
    public String actualizarMonitor(Model model, @PathVariable int id_monitor) {
        model.addAttribute("monitor", monitorDao.consultaMonitor(id_monitor));
        return "monitor/actualizar";
    }

    @RequestMapping(value = "/actualiza/{id_monitor}", method = RequestMethod.POST)
    public String processUpdateSubmit(@PathVariable int id_monitor, @ModelAttribute("monitor") Monitor monitor, BindingResult bindingResult ) {
        if (bindingResult.hasErrors()){
            return "monitor/actualizar";
        }
       monitorDao.actualizaMonitor(monitor);
        return "redirect:../listar";
    }
    //Elimina monitor
    @RequestMapping(value="/elimina/{id_monitor}")
    public String deleteNadador(@PathVariable int id_monitor){
        monitorDao.borraMonitor(id_monitor);
        return "redirect:../list";

    }


}
