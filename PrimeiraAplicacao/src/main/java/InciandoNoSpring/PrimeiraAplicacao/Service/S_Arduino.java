package InciandoNoSpring.PrimeiraAplicacao.Service;

import InciandoNoSpring.PrimeiraAplicacao.Model.M_Usuario;
import InciandoNoSpring.PrimeiraAplicacao.Repository.R_VerificarSenhaArduino;
import org.springframework.stereotype.Service;

@Service
public class S_Arduino {

    private static R_VerificarSenhaArduino arduino;

    public S_Arduino (R_VerificarSenhaArduino arduino) {
        this.arduino = arduino;
    }

    public static M_Usuario loginArduino(String nome, String senha) {
        return arduino.findBySenha(senha);
    }

}
