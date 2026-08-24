package mx.florinda.notafiscal;

import java.math.BigDecimal;

public record PagamentoConfirmadoEvent(Long pagamentoId, Long pedidoId, BigDecimal valor) {

}
