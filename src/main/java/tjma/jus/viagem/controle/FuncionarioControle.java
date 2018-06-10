package tjma.jus.viagem.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tjma.jus.viagem.controle.event.RecursoCriadoEvent;
import tjma.jus.viagem.modelo.Funcionario;
import tjma.jus.viagem.servico.FuncionarioServico;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioControle {
    @Autowired
    private ApplicationEventPublisher publicar;
    private final FuncionarioServico funcionarioServico;

    @Autowired
    public FuncionarioControle(FuncionarioServico funcionarioServico) {
        this.funcionarioServico = funcionarioServico;
    }
    /*
    @PostMapping
    public ResponseEntity<Funcionario> salvar(@Validated @RequestBody Funcionario funcionario, HttpServletResponse response) {
        Funcionario funcionarioSalvo = funcionarioServico.salvar(funcionario);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(funcionarioSalvo.getCodigo())
                .toUri();
        //response.setHeader("Location", uri.toString());
        return  ResponseEntity.created(uri).body(funcionarioSalvo);
    }
    */
    @PostMapping
    public ResponseEntity<?> salvar(@Validated @RequestBody Funcionario funcionario, HttpServletResponse response) {
        Funcionario funcionarioSalvo = funcionarioServico.salvar(funcionario);
        //Utilizando um evento da aplicação (RecursoCriadoEvent) para adicionar o header_location no cabeçalho da resposta
        publicar.publishEvent(new RecursoCriadoEvent(this, response, funcionarioSalvo.getCodigo()));

        return  ResponseEntity.status(HttpStatus.CREATED).body(funcionarioSalvo);
    }

    @GetMapping
    public ResponseEntity<?> buscaTodos() {
        List<Funcionario> funcionarios = funcionarioServico.buscaTodos();

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
        funcionarioServico.excluir(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizar(@PathVariable Integer id, @Validated @RequestBody Funcionario funcionario) {
        Funcionario funcionarioManager = funcionarioServico.atualizar(id, funcionario);
        return ResponseEntity.ok(funcionarioManager);
    }
}
