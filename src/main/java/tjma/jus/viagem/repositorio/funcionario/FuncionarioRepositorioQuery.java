package tjma.jus.viagem.repositorio.funcionario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tjma.jus.viagem.modelo.Funcionario;
import tjma.jus.viagem.repositorio.filtro.FuncionarioFiltro;

import java.util.List;

public interface FuncionarioRepositorioQuery {
    List<Funcionario> filtrar(FuncionarioFiltro filtro);

    Page<Funcionario> filtrar(FuncionarioFiltro filtro, Pageable pageable);
}
