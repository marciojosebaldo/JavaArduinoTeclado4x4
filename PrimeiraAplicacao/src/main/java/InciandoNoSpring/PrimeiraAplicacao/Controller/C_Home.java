package InciandoNoSpring.PrimeiraAplicacao.Controller;

import InciandoNoSpring.PrimeiraAplicacao.Model.M_Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("usuario")
public class C_Home {

    @ModelAttribute("usuario")
    public M_Usuario getUsuario(HttpSession session) {
        return (M_Usuario) session.getAttribute("usuario");
    }

    @GetMapping("/Home")
    public String home(@ModelAttribute("usuario") String usuario) {
        if (usuario != null) {
            // A sessão existe, redirecionar para a página home
            return "Home/home";
        } else {
            // A sessão não existe, redirecionar para a página de login
            return "redirect:/";
        }
    }
}

