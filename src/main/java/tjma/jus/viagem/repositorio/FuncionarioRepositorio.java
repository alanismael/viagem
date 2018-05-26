package tjma.jus.viagem.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tjma.jus.viagem.modelo.Funcionario;

@Repository
public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Integer> {
    Funcionario findByNome(String nome);
}
