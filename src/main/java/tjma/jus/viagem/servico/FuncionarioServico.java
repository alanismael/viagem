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

    @Autowired
    public FuncionarioServico(FuncionarioRepositorio funcionarioRepositorio) {
        this.funcionarioRepositorio = funcionarioRepositorio;
    }

    Optional<Funcionario> buscaPor(String nome) {
        return Optional.ofNullable(funcionarioRepositorio.findByNome(nome));
    }

    public Funcionario buscaPor(Integer codigo) {
        Optional<Funcionario> optionalFuncionario = funcionarioRepositorio.findById(codigo);
        return optionalFuncionario.orElse(null);
    }

    @Transactional
    public Funcionario salva(Funcionario funcionario) {
        return this.funcionarioRepositorio.save(funcionario);
    }

    @Transactional(readOnly = true)
    public List<Funcionario> obterTodosFuncionarios() {
        return funcionarioRepositorio.findAll();
    }

    @Transactional
    public void excluir(Integer id) {
        funcionarioRepositorio.deleteById(id);
    }

    @Transactional
    public Funcionario atualiza(Integer id, Funcionario funcionario) {
        Funcionario funcionarioManager = this.buscaPor(id);
        if (funcionarioManager == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(funcionario, funcionarioManager, "id" );
        this.salva(funcionarioManager);
        return funcionarioManager;
    }
}
