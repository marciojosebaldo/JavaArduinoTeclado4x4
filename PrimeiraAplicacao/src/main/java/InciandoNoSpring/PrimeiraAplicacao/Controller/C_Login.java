package InciandoNoSpring.PrimeiraAplicacao.Controller;

import InciandoNoSpring.PrimeiraAplicacao.Service.S_Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("usuario")
public class C_Login {

    @GetMapping("/")
    public String login() {
        return "Login/login";
    }

    @PostMapping("/")
    public String processLogin(@RequestParam("usuario") String usuario, @RequestParam("senha") String senha, HttpSession session, HttpServletRequest request) {

        session.setAttribute("usuario",S_Usuario.checarLogin(usuario, senha));
        if (session.getAttribute("usuario") != null) {
            request.setAttribute("usuario", session.getAttribute("usuario"));
            return "redirect:/Home"; // Redirecionar para a página home em caso de sucesso
        } else {
            return "redirect:/?error"; // Redirecionar de volta para a página de login com um parâmetro de erro em caso de falha
        }
    }
}
