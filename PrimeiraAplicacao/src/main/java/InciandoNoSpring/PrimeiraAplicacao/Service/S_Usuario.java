package InciandoNoSpring.PrimeiraAplicacao.Service;

import InciandoNoSpring.PrimeiraAplicacao.Model.M_Usuario;
import InciandoNoSpring.PrimeiraAplicacao.Repository.R_Usuario;
import org.springframework.stereotype.Service;

@Service
public class S_Usuario {

    private static R_Usuario usuario;

    public S_Usuario(R_Usuario usuario) {
        this.usuario = usuario;
    }

    public static M_Usuario checarLogin(String nome, String senha) {
        return usuario.findByUsuarioESenha(nome,senha);
    }

}
