package tjma.jus.viagem.servico;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tjma.jus.viagem.modelo.Funcionario;
import tjma.jus.viagem.repositorio.FuncionarioRepositorio;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioServico {
    private final FuncionarioRepositorio funcionarioRepositorio;
    private final GenericoServico<Funcionario> genericoServico;

    @Autowired
    public FuncionarioServico(FuncionarioRepositorio funcionarioRepositorio) {
        this.funcionarioRepositorio = funcionarioRepositorio;
        this.genericoServico = new GenericoServico<Funcionario>(funcionarioRepositorio);
    }

    Optional<Funcionario> buscaPor(String nome) {
        return Optional.ofNullable(funcionarioRepositorio.findByNome(nome));
    }

    public Funcionario buscaPor(Integer codigo) {
        return this.genericoServico.buscaPor(codigo);
    }

    @Transactional
    public Funcionario salvar(Funcionario funcionario) {
        return this.genericoServico.salvar(funcionario);
    }

    @Transactional(readOnly = true)
    public List<Funcionario> buscaTodos() {
        return this.genericoServico.buscaTodos();
    }

    @Transactional
    public void excluir(Integer codigo) {
        this.genericoServico.excluir(codigo);
    }
/*
    @Transactional
    public Funcionario atualizar(Integer codigo, Funcionario funcionario) {
        Funcionario funcionarioDoBanco = this.buscaPor(codigo);
        BeanUtils.copyProperties(funcionario, funcionarioDoBanco, "codigo" );
        this.salvar(funcionarioDoBanco);
        return funcionarioDoBanco;
    }
*/
    @Transactional
    public Funcionario atualizar(Integer codigo, Funcionario funcionario) {
        return this.genericoServico.atualizar(funcionario, codigo);
    }
}
