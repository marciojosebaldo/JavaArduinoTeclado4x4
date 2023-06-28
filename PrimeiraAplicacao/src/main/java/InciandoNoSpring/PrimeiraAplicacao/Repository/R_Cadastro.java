package InciandoNoSpring.PrimeiraAplicacao.Repository;

import InciandoNoSpring.PrimeiraAplicacao.Model.M_Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface R_Cadastro extends JpaRepository<M_Cadastro, Long> {
    @Query(value = "INSERT INTO cadastro (usuario, senha, data_nascimento, num_documento, email, telefone)" +
            "VALUES (:usuario, :senha, :num_documento, :data_nascimento, :email, :telefone)",
            nativeQuery = true)
    M_Cadastro fazerCadastro(@Param("usuario") String usuario, @Param("senha") String senha,
                             @Param("data_nascimento") String data_nascimento, @Param("num_documento")
                             String num_documento, @Param("email") String email, @Param("telefone")
                             String telefone);

    @Query(value = "SELECT * FROM cadastro WHERE usuario = :usuario and senha = :senha limit 1", nativeQuery = true)
    M_Cadastro findByUsuarioESenha(@Param("usuario") String usuario, @Param("senha") String senha);
}