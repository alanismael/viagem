package tjma.jus.viagem.repositorio.funcionario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import tjma.jus.viagem.modelo.Funcionario;
import tjma.jus.viagem.repositorio.filtro.FuncionarioFiltro;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepositorioImpl implements FuncionarioRepositorioQuery {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Funcionario> filtrar(FuncionarioFiltro filtro) {

        // Select p From Funcionario p
        // 1 - Usamos o CriteriaBuilder(cb) para criar a CriteriaQuery (cq)
        // com a tipagem do tipo a ser selecionado (Funcionario)
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Funcionario> cq = cb.createQuery(Funcionario.class);

        // 2 - clausula from e joins
        Root<Funcionario> funcionarioRoot = cq.from(Funcionario.class);

        // 3 - adiciona as restrições (os predicados) que serão passadas na clausula where
        Predicate[] restricoes = this.criaRestricoes(filtro, cb, funcionarioRoot);

        // 4 - monta a consulta com as restrições
        cq.select(funcionarioRoot).where(restricoes).orderBy(cb.asc(funcionarioRoot.get("nome")));

        // 5 - cria e executa a consula
        return manager.createQuery(cq).getResultList();
    }

    private Predicate[] criaRestricoes(FuncionarioFiltro filtro, CriteriaBuilder cb, Root<Funcionario> funcionarioRoot) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(filtro.getNome())) {
            predicates.add(cb.like(funcionarioRoot.get("nome"),"%" + filtro.getNome() + "%" ));
        }

        if (!StringUtils.isEmpty(filtro.getEndereco())) {
            predicates.add(cb.like(funcionarioRoot.get("endereco"),"%" + filtro.getEndereco() + "%" ));
        }

        if (!StringUtils.isEmpty(filtro.getCelular())) {
            predicates.add(cb.like(funcionarioRoot.get("celular"),"%" + filtro.getCelular() + "%" ));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    @Override
    public Page<Funcionario> filtrar(FuncionarioFiltro filtro, Pageable pageable) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Funcionario> cq = cb.createQuery(Funcionario.class);
        Root<Funcionario> funcionarioRoot = cq.from(Funcionario.class);
        Predicate[] restricoes = this.criaRestricoes(filtro, cb, funcionarioRoot);

        // Monta a consulta com as restrições de paginação
        TypedQuery<Funcionario> query = manager.createQuery(cq);
        adicionaRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(filtro));
    }

    private void adicionaRestricoesDePaginacao(TypedQuery<Funcionario> query, Pageable pageable) {
        Integer paginaAtual = pageable.getPageNumber();
        Integer totalObjetosPorPagina = pageable.getPageSize();
        Integer primeiroObjetoDaPagina = paginaAtual * totalObjetosPorPagina;

        // 0 a n-1, n - (2n -1), ...
        query.setFirstResult(primeiroObjetoDaPagina);
        query.setMaxResults(totalObjetosPorPagina);
    }

    private Long total(FuncionarioFiltro filtro) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Funcionario> funcionarioRoot = cq.from(Funcionario.class);
        Predicate[] restricoes = criaRestricoes(filtro, cb, funcionarioRoot);
        cq.where(restricoes);
        cq.select(cb.count(funcionarioRoot));

        return manager.createQuery(cq).getSingleResult();
    }
}
