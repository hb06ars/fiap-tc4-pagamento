package org.fiap.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fiap.domain.enums.StatusPagamentoEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PedidoDTO {

    private Long id;
    private Long clienteId;
    private String numeroCartaoCredito;
    private StatusPagamentoEnum status = StatusPagamentoEnum.ABERTO;
    private BigDecimal valorTotal;
    private LocalDateTime dtPedido;
    private LocalDateTime dtProcessamento = LocalDateTime.now();
    private LocalDateTime dtAtualizacao = LocalDateTime.now();


    public PedidoDTO(PedidoDTO pedidoDTO) {
        this.id = pedidoDTO.getId();
        this.clienteId = pedidoDTO.getClienteId();
        this.numeroCartaoCredito = pedidoDTO.getNumeroCartaoCredito();
        this.status = pedidoDTO.getStatus();
        this.dtPedido = pedidoDTO.getDtPedido();
        this.dtProcessamento = pedidoDTO.getDtProcessamento();
        this.valorTotal = pedidoDTO.getValorTotal();
        this.dtAtualizacao = pedidoDTO.getDtAtualizacao();
    }
}