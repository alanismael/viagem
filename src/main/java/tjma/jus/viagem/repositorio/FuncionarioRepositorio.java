package tjma.jus.viagem.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tjma.jus.viagem.modelo.Funcionario;
import tjma.jus.viagem.repositorio.funcionario.FuncionarioRepositorioQuery;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Integer>, FuncionarioRepositorioQuery {
    Funcionario findByNome(String nome);
    Optional<List<Funcionario>> findByNomeContaining(String nome);
}
