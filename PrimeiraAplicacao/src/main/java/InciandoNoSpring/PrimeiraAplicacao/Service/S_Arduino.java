package InciandoNoSpring.PrimeiraAplicacao.Service;

import InciandoNoSpring.PrimeiraAplicacao.Model.M_Usuario;
import InciandoNoSpring.PrimeiraAplicacao.Repository.R_Arduino;
import org.springframework.stereotype.Service;

@Service
public class S_Arduino {

    private static R_Arduino arduino;

    public S_Arduino (R_Arduino arduino) {
        this.arduino = arduino;
    }

    public static M_Usuario loginArduino(String senha) {
        return arduino.findBySenha(senha);
    }

}