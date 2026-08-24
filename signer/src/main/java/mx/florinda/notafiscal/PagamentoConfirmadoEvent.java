package mx.florinda.notafiscal;

import java.math.BigDecimal;

public class PagamentoConfirmadoEvent {
  public Long pagamentoId;
  public Long pedidoId;
  public BigDecimal valor;

  public PagamentoConfirmadoEvent(Long pagamentoId, Long pedidoId, BigDecimal valor) {
    this.pagamentoId = pagamentoId;
    this.pedidoId = pedidoId;
    this.valor = valor;
  }

  @Override
  public String toString() {
    return "PagamentoConfirmadoEvent{" +
            "pagamentoId=" + pagamentoId +
            ", pedidoId=" + pedidoId +
            ", valor=" + valor +
            '}';
  }
}
