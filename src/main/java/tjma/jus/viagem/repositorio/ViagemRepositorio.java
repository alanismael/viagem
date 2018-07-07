package tjma.jus.viagem.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tjma.jus.viagem.modelo.Viagem;

@Repository
public interface ViagemRepositorio extends JpaRepository<Viagem, Integer> {
    Viagem findByDescricao(String nome);
}
