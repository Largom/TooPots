package tooPots.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tooPots.dao.ReservaDao;

@Controller
@RequestMapping("/reserva")
public class ReservaController {


    private ReservaDao reservaDao;

    @Autowired
    public void setMonitor(ReservaDao reservaDao) {
        this.reservaDao= reservaDao;
    }
}
