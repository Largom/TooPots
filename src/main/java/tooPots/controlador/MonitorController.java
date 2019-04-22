package tooPots.controlador;


import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tooPots.dao.CertificadoDao;
import tooPots.dao.MonitorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tooPots.dao.UsuarioDao;
import tooPots.modelo.Certificado;
import tooPots.modelo.Clasificacion;
import tooPots.modelo.Monitor;
import tooPots.modelo.Usuario;
import tooPots.servicio.CorreoServicios;
import tooPots.servicio.GestionFicheros;
import tooPots.servicio.MonitorSv;
import tooPots.servicio.UsuariosServicio;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/monitor")
public class MonitorController {


    private MonitorDao monitorDao;

    @Autowired
    private MonitorSv monitorsv;

    @Autowired
    private CorreoServicios correoSv;

    @Autowired
    private GestionFicheros gestionFicheros;

    @Autowired
    private UsuariosServicio usuarioSV;

    @Autowired
    public void setMonitor(MonitorDao monitorDao) {
        this.monitorDao = monitorDao;
    }

    //Consulta monitor
    @RequestMapping(value="/consulta/{identificador}")
    public String consultaMonitor(Model model, @PathVariable int identificador, HttpSession session) {


        Usuario user = (Usuario)session.getAttribute("usuario"); //Control usuarios
        if(user.getRole()=="ADMINISTRADOR") {
            model.addAttribute("monitor", monitorDao.consultaMonitor(identificador));
            return "monitor/listar";
        }
        else{ return "redirect:/";}
    }


    //Listado de monitores
    @RequestMapping("/listar")
    public String listadoMonitores(Model model , HttpSession session) {

        Usuario user = (Usuario)session.getAttribute("usuario"); //Control usuarios
        if(user.getRole()=="ADMINISTRADOR") {
            model.addAttribute("monitores", monitorDao.listaMonitores());
            model.addAttribute("certificados", monitorsv.getcertificadosMonitores());
            model.addAttribute("clasificacionesMonitores", monitorsv.getClasificacionesMonitores());
            return "monitor/listar";
        }
        else{ return "redirect:/";}
    }

    //Listado de solicitudes pendientes
    @RequestMapping("/pendientes")
    public String consultaSolicitudesMonitor(Model model,  HttpSession session) {

        Usuario user = (Usuario)session.getAttribute("usuario"); //Control usuarios
        if(user.getRole()=="ADMINISTRADOR") {
            //Obtenemos listado de solicitudes
            model.addAttribute("monitores", monitorDao.listaSolicitudesMonitor());

            //Obtenemos tipos tipo y niveles para poder clasificar la solicitud
            Clasificacion clasificacion = new Clasificacion(monitorsv.getTiposActividad(), monitorsv.getNiveles());
            model.addAttribute("clasificaciones", clasificacion);

            Integer id_mon = (Integer) session.getAttribute("monitorClas");
            if (id_mon != null) { //Informacion a cargar de las clasificaciones hechas sobre la solicitud.
                session.removeAttribute("monitorClas");
                model.addAttribute("monitorClas", id_mon);
                List<Clasificacion> clasificaciones = monitorsv.getClasificacion(id_mon);
                model.addAttribute("clasif", clasificaciones);
            }
            return "monitor/listadoSolicitudes";
        }
        else{ return "redirect:/";}
    }

    //Añadir solicitud de monitor
    @RequestMapping("/solicitud")
    public String SolicitudMonitor(Model model) {

        model.addAttribute("monitor", new Monitor());
        return "monitor/solicitudes";
    }


    @RequestMapping(value="/solicitud", method = RequestMethod.POST)
    public String processAddSubmit(@RequestParam(value ="fichero", required = false) MultipartFile fichero,
                                   @ModelAttribute("monitor") Monitor monitor, @ModelAttribute("certificados") ArrayList<String> certificados,
                                   BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "redirect: monitor/solicitud";
        }
        monitorDao.nuevasolicitudMonitor(monitor);
        monitorsv.añadircertificadosDeSolicitudes(monitorDao.busquedaID(monitor), certificados);

        try {
            gestionFicheros.guardaFichero(fichero);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "monitor/confirmacion";
    }

    //Perfil desde monitor
    @RequestMapping(value="/perfil/{correoUsuario}", method = RequestMethod.GET)
    public String perfilMonitor(Model model, @PathVariable String correoUsuario, HttpSession session) {

        Usuario user = (Usuario)session.getAttribute("usuario"); //Control usuarios
        if(user.getRole()=="MONITOR") {
            Monitor monitor = monitorDao.consultaMonitor(correoUsuario);
            model.addAttribute("monitor", monitor);
            return "monitor/actualizar";
        }
        else{ return "redirect:/";}
    }


    @RequestMapping(value="/perfil/{correoUsuario}", method = RequestMethod.POST)
    public String processUpdateSubmit(@PathVariable String correoUsuario, @ModelAttribute("monitor") Monitor monitor,Model model,
                                      BindingResult bindingResult, HttpSession session ) {

        Usuario user = (Usuario)session.getAttribute("usuario"); //Control usuarios
        if(user.getRole()=="MONITOR") {
            if (bindingResult.hasErrors()) {
                return "monitor/perfil/" + correoUsuario;
            }
            monitorDao.actualizaMonitor(monitor);
            return "redirect:../listar";
        }
        else{ return "redirect:/";}
    }

    //Perfil desde admin

    @RequestMapping(value="/actualiza/{id_monitor}", method = RequestMethod.GET)
    public String perfilMonitor(Model model, @PathVariable int id_monitor, HttpSession session) {

        Usuario user = (Usuario)session.getAttribute("usuario"); //Control usuarios
        if(user.getRole()=="ADMINISTRADOR") {
            Monitor monitor = monitorDao.consultaMonitor(id_monitor);
            model.addAttribute("monitor", monitor);
            return "monitor/actualizar";
        }
        else{ return "redirect:/";}
    }


    @RequestMapping(value="/actualiza/{id_monitor}", method = RequestMethod.POST)
    public String processUpdateSubmit(@PathVariable int id_monitor, @ModelAttribute("monitor") Monitor monitor,Model model,
                                      BindingResult bindingResult, HttpSession session ) {

        Usuario user = (Usuario)session.getAttribute("usuario"); //Control usuarios
        if(user.getRole()=="ADMINISTRADOR") {
            if (bindingResult.hasErrors()) {
                return "monitor/actualiza/" + id_monitor;
            }
            monitorDao.actualizaMonitor(monitor);
            return "redirect:../listar";
        }
        else{ return "redirect:/";}
    }


    //Elimina monitor
    @RequestMapping(value="/elimina/{id_monitor}")
    public String deleteMonitor(@PathVariable int id_monitor, HttpSession session){

        Usuario user = (Usuario)session.getAttribute("usuario"); //Control usuarios
        if(user.getRole()=="ADMINISTRADOR") {
            monitorDao.borrarClasificaciones(id_monitor);
            monitorDao.borraMonitor(id_monitor);
            return "redirect:../listar";
        }
        else{ return "redirect:/";}

    }

    //Elimina solicitud
    @RequestMapping(value="/solicitud/denegada/{id_monitor}")
    public String deleteSolicitud(@PathVariable int id_monitor , HttpSession session){

        Usuario user = (Usuario)session.getAttribute("usuario"); //Control usuarios
        if(user.getRole()=="ADMINISTRADOR") {
            monitorsv.borrarClasificaciones(id_monitor);
            monitorDao.borrarSolicitud(id_monitor);
            return "redirect:../../pendientes";
        }
        else{ return "redirect:/";}
    }

    //Aprueba solicitud
    @RequestMapping(value="/solicitud/aprobada/{id_monitor}")
    public String addmonitor(@PathVariable int id_monitor, HttpSession session){

        Usuario user = (Usuario)session.getAttribute("usuario"); //Control usuarios
        if(user.getRole()=="ADMINISTRADOR") {

            //Recupero datos monitor
            Monitor m = monitorDao.busquedaSolicitud(id_monitor);
            //Copio datos personales a monitores, añado sus certificados y sus categorias.
            monitorDao.añadeMonitor(m);
            List<Certificado> c = monitorsv.getcertificadosSolicitud(id_monitor);
            monitorsv.añadircertificadosDeMonitor(id_monitor, c);
            List<Clasificacion> clasificacionesSolicitud = monitorsv.getClasificacion(id_monitor);
            monitorsv.añadirClasificacionAMonitor(id_monitor, clasificacionesSolicitud);

            //Una vez copiados todos los datos e la solicitud, procedo a eliminar los datos de la solicitud.
            monitorsv.borrarClasificaciones(id_monitor);
            monitorDao.borrarSolicitud(id_monitor);

            return "redirect:../../enviar/" + id_monitor;
        }
        else{ return "redirect:/";}

    }


    @RequestMapping(value="/clasificar/{id_monitor}", method = RequestMethod.POST)
    public String processUpdateSubmit(@PathVariable int id_monitor, @ModelAttribute("clasificaciones") Clasificacion clasificacion,
                                      Model model, BindingResult bindingResult , HttpSession session) {

       /* if (bindingResult.hasErrors()){
            return "monitor/actualiza/"+correoUsuario;
        }*/
        //monitorDao.actualizaMonitor(monitor);
        Usuario user = (Usuario)session.getAttribute("usuario"); //Control usuarios
        if(user.getRole()=="ADMINISTRADOR") {
            session.setAttribute("monitorClas", id_monitor);

            //Se extraen id de tipo y nivel a partir de si descripcion seleccionada en el formulario
            String id_tipo = monitorDao.getIDTipo(clasificacion.getDescripcion_tipo());
            int id_nivel = monitorDao.getIDNivel(clasificacion.getDescripcion_nivel());

            monitorsv.añadirClasificacion(id_monitor, id_tipo, id_nivel);

            return "redirect:/monitor/pendientes";
        }
        else{ return "redirect:/";}
    }






    //Envia correo
    @RequestMapping(value="/enviar/{id_monitor}")
    public String sendMail(@PathVariable("id_monitor") int id_monitor, HttpSession session){

        Usuario user = (Usuario)session.getAttribute("usuario"); //Control usuarios
        if(user.getRole()=="ADMINISTRADOR") {
            Monitor monitor = correoSv.getDatosEnvio(id_monitor);
            SecureRandom random = new SecureRandom();
            String emisor = "gmstoopots@gmail.com";
            String contraseña = new BigInteger(50, random).toString(32);
            String receptor = monitor.getEmail();


            String asunto = "Credenciales LOGIN para " + monitor.getNombre();
            String cuerpo = "DATOS DE ACCESO\n\nUsuario --> " + monitor.getEmail() + "\nContraseña --> " + contraseña;

            Usuario nuevoUsuario = new Usuario(receptor, contraseña, "MONITOR");
            usuarioSV.altaUsuario(nuevoUsuario);
            correoSv.sendMail(emisor, receptor, asunto, cuerpo);

            return "redirect:/monitor/listar";
        }
        else{ return "redirect:/";}
    }




}