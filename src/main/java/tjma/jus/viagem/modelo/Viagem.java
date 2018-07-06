package tjma.jus.viagem.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "viagem")
public class Viagem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(name = "datainicio")
    private LocalDate datainicio;

    @Column(name = "datafinal")
    private LocalDate datafinal;

    @NotNull
    @Size(min = 3, max = 200)
    private String descricao;

    @NotNull
    @Size(min = 3, max = 20)
    private String situacao;

    @ManyToMany
    @JoinTable(name = "funcionario_viagem",
            joinColumns = @JoinColumn(name = "funcionario"),
            inverseJoinColumns = @JoinColumn(name = "viagem"))
    private List<Funcionario> funcionarios = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "viagem_comarca",
            joinColumns = @JoinColumn(name = "viagem"),
            inverseJoinColumns = @JoinColumn(name = "comarca"))
    private List<Comarca> comarcas = new ArrayList<>();

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public LocalDate getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(LocalDate datainicio) {
        this.datainicio = datainicio;
    }

    public LocalDate getDatafinal() {
        return datafinal;
    }

    public void setDatafinal(LocalDate datafinal) {
        this.datafinal = datafinal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Comarca> getComarcas() {
        return comarcas;
    }

    public void setComarcas(List<Comarca> comarcas) {
        this.comarcas = comarcas;
    }

    public void adicionaFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public void adicionaComarca(Comarca comarca) {
        comarcas.add(comarca);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Viagem)) return false;
        Viagem viagem = (Viagem) o;
        return Objects.equals(codigo, viagem.codigo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(codigo);
    }
}
