package mx.florinda.notafiscal;

import java.math.BigDecimal;

public record PagamentoConfirmadoEvent(Long pedidoId, BigDecimal valor) {

}
