package tooPots.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tooPots.dao.UsuarioDao;
import tooPots.modelo.Usuario;

import javax.servlet.http.HttpSession;


class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return Usuario.class.isAssignableFrom(cls);
    }
    @Override
    public void validate(Object obj, Errors errors) {

        Usuario user = (Usuario) obj;

        if(user.getUsuario().trim().equals(""))
            errors.rejectValue("usuario", "obligatorio", "Campo necesario");
        if(user.getPassword().trim().equals(""))
            errors.rejectValue("password", "obligatorio", "Campo necesario");

    }
}


@Controller
public class LoginController {

    @Autowired
    private UsuarioDao usuarioDao;

    @RequestMapping("/")
    public String login(Model model) {
        model.addAttribute("user", new Usuario());
        return "home";
    }

    @RequestMapping(value="/home", method= RequestMethod.POST)
    public String checkLogin(@ModelAttribute("user") Usuario user, BindingResult bindingResult, HttpSession session) {

        UserValidator userValidator = new UserValidator();
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "home";
        }

        user = usuarioDao.buscaUsuario(user.getUsuario());

        if (user == null) {
            bindingResult.rejectValue("password", "badpw", "Contraseña incorrecta");
            return "/home";
        }


        session.setAttribute("usuario", user);
        session.setAttribute("role", user.getRole());

        String nextURL = (String) session.getAttribute("nextURL");
        session.removeAttribute("nextURL");
        if(nextURL!=null)
            return "redirect:"+nextURL;

        return "logueado";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
