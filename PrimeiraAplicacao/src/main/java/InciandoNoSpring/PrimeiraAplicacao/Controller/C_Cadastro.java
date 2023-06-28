package InciandoNoSpring.PrimeiraAplicacao.Controller;

import jakarta.servlet.http.HttpServletRequest;
import InciandoNoSpring.PrimeiraAplicacao.Model.M_Cadastro;
import InciandoNoSpring.PrimeiraAplicacao.Repository.R_Cadastro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class C_Cadastro {

    private final R_Cadastro cadastroRepository;

    @Autowired
    public C_Cadastro(R_Cadastro cadastroRepository) {
        this.cadastroRepository = cadastroRepository;
    }

    @GetMapping("/")
    public String cadastroForm() {
        return "Cadastro/cadastro";
    }

    @PostMapping("/cadastro")
    public String processCadastro(@RequestParam("usuario") String usuario, @RequestParam("senha") String senha,
                                  @RequestParam("num_documento") String num_documento,
                                  @RequestParam("data_nascimento") String data_nascimento,
                                  @RequestParam("email") String email, @RequestParam("telefone") String telefone,
                                  HttpServletRequest request) {
        M_Cadastro cadastro = new M_Cadastro();
        cadastro.setUsuario(usuario);
        cadastro.setSenha(senha);
        cadastro.setNum_documento(num_documento);
        cadastro.setData_nascimento(data_nascimento);
        cadastro.setEmail(email);
        cadastro.setTelefone(telefone);

        cadastroRepository.save(cadastro);

        // Redirecionar para uma página de confirmação ou outra página desejada após o cadastro
        return "redirect:/Login";
    }
}



/*
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
@SessionAttributes("cadastro")

public class C_Cadastro {

    @GetMapping("/")
    public String cadastro() {
        return "Cadastro/cadastro";
    }

    @PostMapping("/cadastro")
    public String processCadastro(@RequestParam("usuario") String usuario, @RequestParam("senha") String senha,
                                  @RequestParam("num_documento") String num_documento,
                                  @RequestParam("data_nascimento") String data_nascimento,
                                  @RequestParam("email") String email, @RequestParam("telefone") String telefone,
                                  HttpSession session, HttpServletRequest request) {
        session.setAttribute("cadastro", S_Cadastro.criarCadastro(usuario, senha, num_documento,
                data_nascimento, email, telefone));

        if(session.getAttribute("cadastro") != null) {
            request.setAttribute("cadastro", session.getAttribute("cadastro"));
            return "redirect:/cadastro";
        } else {
            return "redirect:/?error";
        }
    }
} */