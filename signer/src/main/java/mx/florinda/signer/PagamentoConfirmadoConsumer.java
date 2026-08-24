package mx.florinda.signer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class PagamentoConfirmadoConsumer {

    @Inject
    private Hash hash;

    @Incoming("pagamentosConfirmados")
    public void process(PagamentoConfirmadoEvent event) {
        System.out.println("Pagamento confirmado recebido: " + event);
        String hashValue = hash.geraHash(event.toString());
        System.out.println("Hash gerado: " + hashValue);
    }

}
