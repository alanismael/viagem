package tjma.jus.viagem.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tjma.jus.viagem.controle.event.RecursoCriadoEvent;
import tjma.jus.viagem.modelo.Viagem;
import tjma.jus.viagem.servico.ViagemServico;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ViagemControle {
    @Autowired
    private ApplicationEventPublisher publicar;
    private final ViagemServico viagemServico;

    @Autowired
    public ViagemControle(ViagemServico viagemServico) {
        this.viagemServico = viagemServico;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@Validated @RequestBody Viagem viagem, HttpServletResponse response) {
        Viagem viagemSalvo = viagemServico.salvar(viagem);
        publicar.publishEvent(new RecursoCriadoEvent(this, response, viagemSalvo.getCodigo()));

        return  ResponseEntity.status(HttpStatus.CREATED).body(viagemSalvo);
    }

    @GetMapping
    public ResponseEntity<?> buscaTodos() {
        List<Viagem> viagems = viagemServico.buscaTodos();

        if (viagems.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(viagems);
        }
    }

    @GetMapping("/{id}")
    public Viagem buscaPor(@PathVariable Integer id) {
        return viagemServico.buscaPor(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void excluir(@PathVariable Integer id) {
        viagemServico.excluir(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viagem> atualizar(@PathVariable Integer id, @Validated @RequestBody Viagem viagem) {
        Viagem viagemManager = viagemServico.atualizar(id, viagem);
        return ResponseEntity.ok(viagemManager);
    }
}
