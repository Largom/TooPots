package tooPots.controlador;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tooPots.dao.ReservaDao;
import tooPots.modelo.Cliente;
import tooPots.modelo.Reserva;



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


    private ReservaDao reservaDao;
    
  //Inscribirse en una actividad (realizar reserva)   
    

    @Autowired
    public void setMonitor(ReservaDao reservaDao) {
        this.reservaDao= reservaDao;
    }
    
    
    @RequestMapping("/reaReserva")
    public String realizarReserva(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente/inscripcion";
    }
    
    
    
    
}
















