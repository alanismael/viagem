package tjma.jus.viagem.servico;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import tjma.jus.viagem.modelo.Funcionario;

import java.util.List;
import java.util.Optional;

public class GenericoServico<T> {
    private final JpaRepository<T, Integer> repository;

    public GenericoServico(JpaRepository<T, Integer> repository) {
        this.repository = repository;
    }

    T salvar(T entidade) {
        return repository.save(entidade);
    }

    List<T> buscaTodos() {
        return repository.findAll();
    }

    T atualizar(T entidade, Integer codigo) {
        T entidadeDoBanco = this.buscaPor(codigo);
        BeanUtils.copyProperties(entidade, entidadeDoBanco, "codigo" );
        this.salvar(entidadeDoBanco);
        return entidadeDoBanco;
    }

    T buscaPor(Integer codigo) {
        return repository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public void excluir(Integer codigo) {
        this.repository.deleteById(codigo);
    }
}
