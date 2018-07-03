package tjma.jus.viagem.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tjma.jus.viagem.modelo.Comarca;
import tjma.jus.viagem.repositorio.ComarcaRepositorio;

import java.util.List;
import java.util.Optional;

@Service
public class ComarcaServico {
    private final ComarcaRepositorio comarcaRepositorio;
    private final GenericoServico<Comarca> genericoServico;

    @Autowired
    public ComarcaServico(ComarcaRepositorio comarcaRepositorio) {
        this.comarcaRepositorio = comarcaRepositorio;
        this.genericoServico = new GenericoServico<Comarca>(comarcaRepositorio);
    }

    Optional<Comarca> buscaPor(String nome) {
        return Optional.ofNullable(comarcaRepositorio.findByNome(nome));
    }

    public Comarca buscaPor(Integer codigo) {
        return this.genericoServico.buscaPor(codigo);
    }

    @Transactional
    public Comarca salvar(Comarca comarca) {
        return this.genericoServico.salvar(comarca);
    }

    @Transactional(readOnly = true)
    public List<Comarca> buscaTodos() {
        return this.genericoServico.buscaTodos();
    }

    @Transactional
    public void excluir(Integer codigo) {
        this.genericoServico.excluir(codigo);
    }

    @Transactional
    public Comarca atualizar(Integer codigo, Comarca comarca) {
        return this.genericoServico.atualizar(comarca, codigo);
    }
}
