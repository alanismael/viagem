package tjma.jus.viagem.controle.event;

import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

    @Override
    public void onApplicationEvent(RecursoCriadoEvent eventoRecursoCriado) {
        this.adcionaHeaderLocation(eventoRecursoCriado.getResponse(), eventoRecursoCriado.getCodigo());
    }

    private void adcionaHeaderLocation(HttpServletResponse response, Integer id) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        response.setHeader("Location", uri.toString());
    }
}
