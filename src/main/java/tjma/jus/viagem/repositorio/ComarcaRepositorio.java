package tjma.jus.viagem.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tjma.jus.viagem.modelo.Comarca;

@Repository
public interface ComarcaRepositorio extends JpaRepository<Comarca, Integer> {
    Comarca findByNome(String nome);
}
