package mx.florinda.pedido;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PagamentoConfirmadoConsumer {

    @Incoming("pagamentosConfirmados")
    public Uni<Void> consume(PagamentoConfirmadoEvent event) {
        System.out.println("Pagamento confirmado recebido: " + event);
        return Panache.withTransaction(() -> {
            return Pedido.<Pedido>findById(event.pedidoId)
            .onItem().ifNotNull().invoke(pedido -> {
                pedido.status = StatusPedido.PAGO;
                pedido.persist();
            });
        }).replaceWithVoid();
    }

}
