package tjma.jus.viagem.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tjma.jus.viagem.modelo.Viagem;
import tjma.jus.viagem.repositorio.ViagemRepositorio;

import java.util.List;
import java.util.Optional;

@Service
public class ViagemServico {
    private final ViagemRepositorio viagemRepositorio;
    private final GenericoServico<Viagem> genericoServico;

    @Autowired
    public ViagemServico(ViagemRepositorio viagemRepositorio) {
        this.viagemRepositorio = viagemRepositorio;
        this.genericoServico = new GenericoServico<Viagem>(viagemRepositorio);
    }

    Optional<Viagem> buscaPor(String nome) {
        return Optional.ofNullable(viagemRepositorio.findByDescricao(nome));
    }

    public Viagem buscaPor(Integer codigo) {
        return this.genericoServico.buscaPor(codigo);
    }

    @Transactional
    public Viagem salvar(Viagem viagem) {
        return this.genericoServico.salvar(viagem);
    }

    @Transactional(readOnly = true)
    public List<Viagem> buscaTodos() {
        return this.genericoServico.buscaTodos();
    }

    @Transactional
    public void excluir(Integer codigo) {
        this.genericoServico.excluir(codigo);
    }

    @Transactional
    public Viagem atualizar(Integer codigo, Viagem viagem) {
        return this.genericoServico.atualizar(viagem, codigo);
    }
}
