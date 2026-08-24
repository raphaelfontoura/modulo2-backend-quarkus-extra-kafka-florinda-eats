package mx.florinda.notafiscal;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PagamentoConfirmadoConsumer {

    @RestClient
    PedidoService pedidoService;

    @Incoming("pagamentosConfirmados")
    public void consume(PagamentoConfirmadoEvent event) {
        System.out.println("Pagamento confirmado recebido: " + event);
        pedidoService.notaFiscal(event.pedidoId(), event.valor())
                .subscribe().with(notaFiscal -> {
                    System.out.println("Nota fiscal gerada: \n" + notaFiscal);
                });
    }
}
