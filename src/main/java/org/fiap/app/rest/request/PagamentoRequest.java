package org.fiap.app.rest.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagamentoRequest {
    private Long id;

    @Positive(message = "O Pedido ID deve ser maior que 0")
    private Long pedidoId;

    private String idPagamento;
    private LocalDateTime dtPagamento;
    private String statusPagamento;
    private LocalDateTime dtAtualizacao;
}
