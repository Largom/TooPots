package tooPots.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tooPots.dao.ActividadDao;

@Controller
@RequestMapping("/actividad")
public class ActividadController {

    private ActividadDao actividadDao;

    @Autowired
    public void setMonitor(ActividadDao actividadDao) { this.actividadDao= actividadDao; }
}

