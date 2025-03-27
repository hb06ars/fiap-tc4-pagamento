package org.fiap.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fiap.app.rest.request.PagamentoRequest;
import org.fiap.domain.entity.PagamentoEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PagamentoDTO {

    private Long id;
    private Long pedidoId;
    private String idPagamento;
    private LocalDateTime dtPagamento;
    private String statusPagamento;
    private LocalDateTime dtAtualizacao;
    private BigDecimal valorTotal;

    public PagamentoDTO(PagamentoEntity pagamentoEntity) {
        this.id = pagamentoEntity.getId();
        this.pedidoId = pagamentoEntity.getPedidoId();
        this.idPagamento = pagamentoEntity.getIdPagamento();
        this.dtPagamento = pagamentoEntity.getDtPagamento();
        this.statusPagamento = pagamentoEntity.getStatusPagamento();
        this.dtAtualizacao = pagamentoEntity.getDtAtualizacao();
        this.valorTotal = pagamentoEntity.getValorTotal();
    }

    public PagamentoDTO(PagamentoRequest request) {
        this.id = request.getId();
        this.pedidoId = request.getPedidoId();
        this.idPagamento = request.getIdPagamento();
        this.dtPagamento = request.getDtPagamento();
        this.statusPagamento = request.getStatusPagamento();
        this.dtAtualizacao = request.getDtAtualizacao();
        this.valorTotal = request.getValorTotal();
    }
}
