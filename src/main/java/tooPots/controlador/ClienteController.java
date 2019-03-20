package tooPots.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tooPots.dao.ClienteDao;


@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private ClienteDao clienteDao;

    @Autowired
    public void setMonitor(ClienteDao clienteDao) {
        this.clienteDao= clienteDao;
    }

}
