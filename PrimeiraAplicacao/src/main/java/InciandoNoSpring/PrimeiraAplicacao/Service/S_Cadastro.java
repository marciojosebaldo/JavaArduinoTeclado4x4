package InciandoNoSpring.PrimeiraAplicacao.Service;

import InciandoNoSpring.PrimeiraAplicacao.Model.M_Cadastro;
import InciandoNoSpring.PrimeiraAplicacao.Repository.R_Cadastro;
import org.springframework.stereotype.Service;

@Service
public class S_Cadastro {

    private static R_Cadastro cadastro;

    public S_Cadastro(R_Cadastro cadastro) {
        this.cadastro = cadastro;
    }

    public static M_Cadastro criarCadastro(String usuario, String senha, String num_documento, String
                                           data_nascimento, String email, String telefone) {
        return cadastro.fazerCadastro(usuario, senha, num_documento, data_nascimento, email, telefone);
    }

    public static M_Cadastro checarLogin(String usuario, String senha) {
        return cadastro.findByUsuarioESenha(usuario, senha);
    }
}