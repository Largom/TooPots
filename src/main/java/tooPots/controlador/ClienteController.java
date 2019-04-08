package tooPots.controlador;


import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import tooPots.dao.ClienteDao;
import tooPots.dao.MonitorDao;
import tooPots.modelo.Cliente;
import tooPots.modelo.Monitor;
import tooPots.servicio.ClienteSv;
import tooPots.servicio.CorreoServicios;
import tooPots.servicio.GestionFicheros;
import tooPots.servicio.MonitorSv;


@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private ClienteDao clienteDao;

    @Autowired
    private ClienteSv clientesv;

    @Autowired
    private CorreoServicios correoSv;

    @Autowired
    private GestionFicheros gestionFicheros;

    @Autowired
    public void setCliente(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }
    
    /*
     * Inscribirse en una actividad (realizar reserva)
		Realizar la reserva te llevara al formulario de reservas.
		Cancelar incripcion(reserva)
		Darte de alta como cliente
     * 
     */
    
    //Añadir cliente
    
    @RequestMapping("/inscripcion")
    public String InscripcionCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente/inscripcion";
    }

    
    @RequestMapping(value="/inscripcion", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("cliente") Cliente cliente,
                                              BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "redirect: cliente/inscripcion";
        }

        clienteDao.anyadeCliente(cliente);
        return "cliente/confirmacion";

    }
    
    @RequestMapping("/listar")
    public String ListarClientes(Model model) {
        model.addAttribute("clientes", clienteDao.listaClientes());

        return "cliente/listar";
    }
    

    


}

