package tjma.jus.viagem.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Comarca implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @NotNull
    @Size(min = 3, max = 100)
    private String nome;
    private String endereco;

    @ElementCollection
    @CollectionTable(
            name = "telefone",
            joinColumns = @JoinColumn(name = "comarca")
    )
    @Column(name = "numero")
    private Set<@NotEmpty String> telefone = new LinkedHashSet<>();

    private String email;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Set<String> getTelefone() {
        return telefone;
    }

    public void setTelefone(Set<String> telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comarca)) return false;
        Comarca comarca = (Comarca) o;
        return Objects.equals(codigo, comarca.codigo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(codigo);
    }
}
