package tooPots.controlador;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;


import tooPots.dao.ActividadDao;
import tooPots.dao.MonitorDao;
import tooPots.modelo.Actividad;
import tooPots.modelo.Cliente;
import tooPots.modelo.Monitor;
import tooPots.modelo.TipoActividad;
import tooPots.modelo.Usuario;


class ActividadValidator implements Validator {
	
	@Autowired
    private ActividadDao actividadDao;
	
    @Override
    public boolean supports(Class<?> cls) {
        return Actividad.class.isAssignableFrom(cls);
    }
    @Override
    public void validate(Object obj, Errors errors) {


        Actividad actividad = (Actividad) obj;

        if(actividad.getNombre().trim().equals(""))
            errors.rejectValue("nombre", "obligatorio", "Campo necesario");
        
        /*
        List<Actividad> actividades = actividadDao.listaActividad();
        String nombrePropuesto = actividad.getNombre().trim(); 
        
        for( Actividad act : actividades) {
        	if (act.getNombre().equals(nombrePropuesto))
        		errors.rejectValue("nombre", "replicado", "¡Existe ya una actividad con el mismo nombre!");
        }
        */
        
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
    
	@Autowired
    private ActividadDao actividadDao;
    
    @Autowired
    private MonitorDao monitorDao;
    
    

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
	                                BindingResult bindingResult, Model model, HttpSession session,
	                                @RequestParam("image") MultipartFile image, RedirectAttributes atts) { 
		
		// Validamos
        ActividadValidator actividadValidator = new ActividadValidator();
        actividadValidator.validate(actividad, bindingResult);    
		
		 if (bindingResult.hasErrors()) { 
				model.addAttribute("tipos_actividad", actividadDao.getTiposActividad());
				model.addAttribute("niveles", actividadDao.getNiveles());
				return "actividad/add";
		 }
		 
		 // Preparamos datos e insertamos tanto como actividad como monitorActividad
	     Usuario user = (Usuario)session.getAttribute("usuario");
		 Monitor monitor = monitorDao.consultaMonitor( user.getUsuario().trim() );
	     int id_monitor = monitor.getId_monitor();
	     actividad.setEstado("Abierta");
		 
		 actividadDao.addActividad(actividad);
		 
		 int id_actividad=actividadDao.busquedaID(actividad);

		 actividadDao.addMonitorActividad(id_monitor, id_actividad);
		 
		 if (image.isEmpty()) {			 
			 atts.addFlashAttribute("imagen","necesita colocar una imagen");
			 
		 }
		 
		 
		 try {
			 
			 
			 
			 // Diferentes tipos de imagen: diferente formato para guardarlo también
			 
			 String[] nombreOriginal = image.getOriginalFilename().split(Pattern.quote("."));
			 String tipoImagen = nombreOriginal[nombreOriginal.length -1];
			 
			 Path path =  Paths.get("TooPots/src/main/resources/static/img/" + id_actividad + "." + tipoImagen);

			
			 Path parentPath = path.getParent();
			 if (!Files.exists(parentPath))
			     Files.createDirectories(parentPath);
			 
			 
			 Files.write(path, image.getBytes());
			 
			 System.out.println(Files.readAllBytes(path));
			 
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
		 
		 
		 
		 return "actividad/confirmacion"; 
	 }
	
	@RequestMapping(value="/addTipo")
	public String addTipo(Model model) {
		
		model.addAttribute("tipos_actividad",actividadDao.getTiposActividad());
		model.addAttribute("nuevoTipo", new TipoActividad());
		
		return "actividad/addTipo";
	}
	
	@RequestMapping(value="/TipoNuevo/{nuevoTipo}", method=RequestMethod.POST)
	public String addTipo(Model model, @PathVariable  String nuevoTipo) {
		actividadDao.addtipoActividad(nuevoTipo);
		System.out.println("aquí estoy tb");
		return "actividad/addTipo/{nuevoTipo}";
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
		 actividadDao.borrarActividad(actividad.getId_actividad());
		 return "redirect:listar"; 
	 }
	
	
	@RequestMapping("/listar")
	public String listActividades(Model model) {
		
	   model.addAttribute("actividades", actividadDao.listaActividad());
	   
	   
	   return "actividad/listar";
	}	
	/*
	@RequestMapping(value="/listar", method=RequestMethod.POST)
	public String processListSubmit(@ModelAttribute("actividades") Actividad actividad,
	                                BindingResult bindingResult) { 
		 if (bindingResult.hasErrors()) 
				return "actividad/listar";
		 actividadDao.addActividad(actividad);
		 return "redirect:listar"; 
	 }
	 */
	/**
	 * @return the actividadDao
	 */
	public ActividadDao getActividadDao() {
		return actividadDao;
	}
	




}
