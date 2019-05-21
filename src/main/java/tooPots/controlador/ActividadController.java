package tooPots.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;


import tooPots.dao.ActividadDao;
import tooPots.modelo.Actividad;
import tooPots.modelo.Cliente;


class ActividadValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return Actividad.class.isAssignableFrom(cls);
    }
    @Override
    public void validate(Object obj, Errors errors) {


        Actividad actividad = (Actividad) obj;

        if(actividad.getNombre().trim().equals(""))
            errors.rejectValue("nombre", "obligatorio", "Campo necesario");
        if(actividad.getLugar().trim().equals(""))
            errors.rejectValue("lugar", "obligatorio", "Campo necesario");
        if(actividad.getPrecio() == 0)
            errors.rejectValue("precio", "obligatorio", "Campo necesario");
        if(actividad.getAsistentesMinimos() == 0)
            errors.rejectValue("asistentesMinimos", "obligatorio", "Campo necesario");
        if(actividad.getAsistentesMaximos() == 0)
            errors.rejectValue("asistentesMaximos", "obligatorio", "Campo necesario");
    
        
    }
}







@Controller
@RequestMapping("/actividad")
public class ActividadController {

    private ActividadDao actividadDao;

	@Autowired
	public void setActividadDao(ActividadDao actividadDao) {
		this.actividadDao = actividadDao;
	}
	

	
    @RequestMapping(value="/add") 
	public String addActividades(Model model) {
		
    	model.addAttribute("actividad", new Actividad());
		model.addAttribute("tipos_actividad", actividadDao.getTiposActividad());
		model.addAttribute("niveles", actividadDao.getNiveles());
		
		
		return "actividad/add";
	}
    
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("actividad") Actividad actividad,
	                                BindingResult bindingResult, Model model) { 
		
        ActividadValidator actividadValidator = new ActividadValidator();
        actividadValidator.validate(actividad, bindingResult);
		
		 if (bindingResult.hasErrors()) { 
				model.addAttribute("tipos_actividad", actividadDao.getTiposActividad());
				model.addAttribute("niveles", actividadDao.getNiveles());
				return "actividad/add";
		 }
		 
		 actividad.setEstado("Abierta");
		 actividadDao.addActividad(actividad);
		 return "actividad/confirmacion"; 
	 }

    
    
	
	@RequestMapping(value="/update/{nom}", method = RequestMethod.GET) 
	public String editNadador(Model model, @PathVariable  int id) { 
		model.addAttribute("actividad", actividadDao.busquedaActividad(id));
		return "actividad/update"; 
	}
	@RequestMapping(value="/delete/{id}")
	public String processDelete(@PathVariable int id) {
           actividadDao.borrarActividad(id);
           return "redirect:../listar"; 
	}
	

	
	
	@RequestMapping(value="/update/{actividad}", method = RequestMethod.POST) 
	public String processUpdateSubmit(@PathVariable String actividad, 
                            @ModelAttribute("actividades") Actividad actividad1, 
                            BindingResult bindingResult) {
		 if (bindingResult.hasErrors()) 
			 return "actividad/actualizar";
		 actividadDao.actualizarActividad(actividad1);
		 return "redirect:../listar"; 
	}
	
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String processDeleteSubmit(@ModelAttribute("actividades") Actividad actividad,
	                                BindingResult bindingResult) { 
		 if (bindingResult.hasErrors()) 
				return "actividad/delete";
		 actividadDao.addActividad(actividad);
		 return "redirect:listar"; 
	 }
	
	
	@RequestMapping("/listar")
	public String listActividades(Model model) {
	   model.addAttribute("actividades", actividadDao.listaActividad());
	   return "actividad/listar";
	}	
	
	@RequestMapping(value="/listar", method=RequestMethod.POST)
	public String processListSubmit(@ModelAttribute("actividades") Actividad actividad,
	                                BindingResult bindingResult) { 
		 if (bindingResult.hasErrors()) 
				return "actividad/listar";
		 actividadDao.addActividad(actividad);
		 return "redirect:listar"; 
	 }
	/**
	 * @return the actividadDao
	 */
	public ActividadDao getActividadDao() {
		return actividadDao;
	}




}


    


