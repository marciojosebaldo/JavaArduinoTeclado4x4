package InciandoNoSpring.PrimeiraAplicacao.Controller;

import InciandoNoSpring.PrimeiraAplicacao.Service.S_Cadastro;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
//@SessionAttributes("login") // Anotação que define o atributo cadastro da sessão deve ser armazenado temporariamente no escopo compartilhado e disponibilizado para outros controladores
public class C_Login {

    @GetMapping("/Login") // Anotação mapeia a requição HTTP GET para a raiz do contexto da aplicação ("/") ao método "login". Significa que quando acessar a raiz da aplicação será acessado o método login()
    public String login() {
        return "Login/login";
    } //Responsável em lidar com requisição GET para a raiz da aplicação. Retorna String "Login/login" que representa o nome da página (ou template) a ser exibida ao usuário

    @PostMapping("/Login") // Mapeia anotação da requisição HTTP POST para a raiz do contexto da aplicação ("/") ao método processLogin(). Isso significa que quando um método enviar um formulário para a raiz da aplicação usando o método POST, o método processLogin() será executado
    public String processLogin(@RequestParam("usuario") String usuario, @RequestParam("senha") String senha, HttpSession session, HttpServletRequest request) {
        // Método responsável em lidar com a requisição POST para a raiz da aplicação. Ele recebe os parâmetros "usuario" e "senha" da requisição como Strings, além dos
        // objetos 'HttpSession' e 'HttpServletReques'. O método 'processLogin()' verifica o login e a senha fornecidos, armazena o usuário na sessão e redireciona para a página
        // "Home" se o login e a senha for bem-sucedido, ou para a página de login com um parâmetro de erro em caso de falha.

        session.setAttribute("cadastro", S_Cadastro.checarLogin(usuario, senha));
        // Armazena o objeto retornado pelo método S_Cadastro.chegarLogin(usuario, senha) na sessão com o nome de atributo "usuario". Esse objeto representa as informações do usuário logado
        if (session.getAttribute("cadastro") != null) {
            // Verifica se o "usuario" está presente na sessão. Se estiver presente, o método define o atributo "usuario" no objeto 'request' e redireciona para a página "Home". Caso
            // contrário, redireciona de volta para a página login com um parâmetro de erro
            request.setAttribute("cadastro", session.getAttribute("cadastro"));
            return "redirect:/Reserva"; // Redirecionar para a página home em caso de sucesso
        } else {
            return "redirect:/?error"; // Redirecionar de volta para a página de login com um parâmetro de erro em caso de falha
        }
    }
}