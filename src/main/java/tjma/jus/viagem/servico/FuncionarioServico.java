package tjma.jus.viagem.servico;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tjma.jus.viagem.modelo.Funcionario;
import tjma.jus.viagem.repositorio.FuncionarioRepositorio;
import tjma.jus.viagem.repositorio.filtro.FuncionarioFiltro;

import java.util.ArrayList;
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
/*
    Optional<Funcionario> buscaPor(String nome) {
        return Optional.ofNullable(funcionarioRepositorio.findByNome(nome));
    }
*/
    public List<Funcionario> buscaPor(String nome) {
        return funcionarioRepositorio.findByNomeContaining(nome).orElse(new ArrayList<>());
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

    public Page<Funcionario> buscaPaginada(Pageable pageable) {
        return funcionarioRepositorio.findAll(pageable);
    }

    public List<Funcionario> pesquisa(FuncionarioFiltro filtro) {
        return funcionarioRepositorio.filtrar(filtro);
    }

    public Page<Funcionario> pesquisa(FuncionarioFiltro filtro, Pageable pageable) {
        return funcionarioRepositorio.filtrar(filtro, pageable );
    }
}
