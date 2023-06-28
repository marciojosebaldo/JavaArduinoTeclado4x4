package InciandoNoSpring.PrimeiraAplicacao.Repository;

import InciandoNoSpring.PrimeiraAplicacao.Model.M_Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
// Não foi incluso
public interface R_Reserva extends JpaRepository<M_Reserva, Long>{
    /*
    Primeiro parâmetro define a classe da entidade que a interface trabalhará e o segundo parâmetro o tipo
    de dado do identificador dessa entidade
     */
    @Query(value = "INSERT INTO reserva (usuario, senha, data_entrada, data_saida, quarto) VALUES " +
            "(:usuario, :senha, :data_entrada, :data_saida, :quarto)", nativeQuery = true)
    M_Reserva fazerReserva(@Param("usuario") String usuario, @Param("senha") String senha,
                           @Param("data_entrada") String data_entrada, @Param("data_saida") String data_saida,
                           @Param("quarto") Long quarto);
}