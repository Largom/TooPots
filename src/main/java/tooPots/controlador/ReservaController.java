package tooPots.controlador;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tooPots.dao.ActividadDao;
import tooPots.dao.ClienteDao;
import tooPots.dao.MonitorDao;
import tooPots.dao.ReservaDao;
import tooPots.modelo.Actividad;
import tooPots.modelo.Clasificacion;
import tooPots.modelo.Cliente;
import tooPots.modelo.Monitor;
import tooPots.modelo.Reserva;
import tooPots.modelo.Usuario;
import tooPots.servicio.UsuariosServicio;




class ReservaValidator implements Validator {
	
	
    @Override
    public boolean supports(Class<?> cls) {
        return Reserva.class.isAssignableFrom(cls);
    }
    @Override
    public void validate(Object obj, Errors errors) {


        Reserva reserva = (Reserva) obj;

        
//        if(cliente.getNombre().trim().equals(""))
//            errors.rejectValue("nombre", "obligatorio", "Campo necesario");

         
        
        
        
    }
}


@Controller
@RequestMapping("/reserva")
public class ReservaController{

	private static AtomicInteger NUMS_RESERVA = new AtomicInteger(0);
    
	@Autowired
	private ReservaDao reservaDao;
    
	@Autowired
	private ActividadDao actividadDao;
	
	@Autowired
	private ClienteDao clienteDao;
	
    @Autowired
    private UsuariosServicio usuarioSV;
    
    
    //Inscribirse en una actividad (realizar reserva) 
    @RequestMapping(value="/reaReserva/{id_actividad}")
    public String realizarReserva( @PathVariable int id_actividad, Model model) {
        
        model.addAttribute("reserva", new Reserva());     
        model.addAttribute("actividad", actividadDao.busquedaActividad(id_actividad));  
        return "reserva/reaReserva";
    }

    
  //  
    @RequestMapping(value="/reaReserva/{id_actividad}", method = RequestMethod.POST)
    public String actualizaReserva(Model model, @ModelAttribute("reserva") Reserva reserva, @PathVariable int id_actividad,
    		HttpSession session) {
    	
    	Usuario user = (Usuario) (session.getAttribute("usuario"));
    	    	
    	reserva.setId_cliente( clienteDao.busquedaClientePorCorreo(user.getUsuario()).getId_cliente());
    	
        Actividad actividad = actividadDao.busquedaActividad(reserva.getId_actividad());
        reserva.setPrecioFinal(actividad.getPrecio() * reserva.getAsistentes());
        reserva.setPrecioTotal(actividad.getPrecio() * reserva.getAsistentes());

        reserva.setEstado("Pendiente");
        //Si no hay error
        reservaDao.addReserva(reserva);
        
        return "reserva/confirmacion";
        
    }
    
    
    
    

    @Autowired
    public void setMonitor(ReservaDao reservaDao) {
        this.reservaDao= reservaDao;
    }
    
    

    
    
    
    
}
















