package tjma.jus.viagem.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tjma.jus.viagem.modelo.Funcionario;
import tjma.jus.viagem.servico.FuncionarioServico;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioControle {

    private final FuncionarioServico funcionarioServico;

    @Autowired
    public FuncionarioControle(FuncionarioServico funcionarioServico) {
        this.funcionarioServico = funcionarioServico;
    }

    @PostMapping
    public ResponseEntity<Funcionario> salvar(@Validated @RequestBody Funcionario funcionario, HttpServletResponse response) {
        Funcionario funcionarioSalvo = funcionarioServico.salva(funcionario);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(funcionarioSalvo.getCodigo())
                .toUri();
        //response.setHeader("Location", uri.toString());
        return  ResponseEntity.created(uri).body(funcionarioSalvo);
    }

    @GetMapping
    public ResponseEntity<?> buscaTodos() {
        List<Funcionario> funcionarios = funcionarioServico.obterTodosFuncionarios();

        if (funcionarios.isEmpty()) {
            //return ResponseEntity.notFound().build();
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(funcionarios);
        }
    }

    @GetMapping("/{id}")
    public Funcionario buscaPor(@PathVariable Integer id) {
        return funcionarioServico.buscaPor(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void excluir(@PathVariable Integer id) {
        funcionarioServico.excluir(id );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> alterar(@PathVariable Integer id,
                                               @Validated @RequestBody Funcionario funcionario) {

        Funcionario funcionarioManager = funcionarioServico.atualiza(id, funcionario);
        return ResponseEntity.ok(funcionarioManager);
    }
}
