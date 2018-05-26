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
    public ResponseEntity<Funcionario> cria(@RequestBody Funcionario funcionario, HttpServletResponse response) {
        Funcionario funcionarioSalvo = funcionarioServico.salva(funcionario);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(funcionarioSalvo.getCodigo())
                .toUri();
        //response.setHeader("Location", uri.toString());
        return  ResponseEntity.created(uri).body(funcionarioSalvo);
    }
    /*
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria cria(@RequestBody Funcionario funcionario, HttpServletResponse response) {
        Funcionario funcionarioSalvo = funcionarioServico.salva(funcionario);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(funcionarioSalvo.getCodigo())
                .toUri();

        response.setHeader("Location", uri.toString() );
        return funcionarioSalvo;
    }
    */
    /*
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cria(@RequestBody Funcionario funcionario, HttpServletResponse response) {
        Funcionario funcionarioSalvo = funcionarioServico.salva(funcionario);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(categoriaSalva.getId())
                .toUri();

        response.setHeader("Location", uri.toString() );
    }
    */
    /*
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cria(@RequestBody Funcionario funcionario) {
        funcionarioServico.salva(funcionario);
    }
    */
    /*
    @PostMapping
    public void cria(@RequestBody Funcionario funcionario) {
        funcionarioServico.salva(funcionario);
    }

    */
    @GetMapping
    public ResponseEntity<?> listaFuncionarios() {
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
    public ResponseEntity<Funcionario> atualizar(@PathVariable Integer id,
                                               @Validated @RequestBody Funcionario funcionario) {

        Funcionario funcionarioManager = funcionarioServico.atualiza(id, funcionario);
        return ResponseEntity.ok(funcionarioManager);
    }
}
