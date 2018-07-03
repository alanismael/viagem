package tjma.jus.viagem.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tjma.jus.viagem.controle.event.RecursoCriadoEvent;
import tjma.jus.viagem.modelo.Comarca;
import tjma.jus.viagem.servico.ComarcaServico;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/comarca")
public class ComarcaControle {
    @Autowired
    private ApplicationEventPublisher publicar;
    private final ComarcaServico comarcaServico;

    @Autowired
    public ComarcaControle(ComarcaServico comarcaServico) {
        this.comarcaServico = comarcaServico;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@Validated @RequestBody Comarca comarca, HttpServletResponse response) {
        Comarca comarcaSalvo = comarcaServico.salvar(comarca);
        publicar.publishEvent(new RecursoCriadoEvent(this, response, comarcaSalvo.getCodigo()));

        return  ResponseEntity.status(HttpStatus.CREATED).body(comarcaSalvo);
    }

    @GetMapping
    public ResponseEntity<?> buscaTodos() {
        List<Comarca> comarcas = comarcaServico.buscaTodos();

        if (comarcas.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(comarcas);
        }
    }

    @GetMapping("/{id}")
    public Comarca buscaPor(@PathVariable Integer id) {
        return comarcaServico.buscaPor(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void excluir(@PathVariable Integer id) {
        comarcaServico.excluir(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comarca> atualizar(@PathVariable Integer id, @Validated @RequestBody Comarca comarca) {
        Comarca comarcaManager = comarcaServico.atualizar(id, comarca);
        return ResponseEntity.ok(comarcaManager);
    }
}
