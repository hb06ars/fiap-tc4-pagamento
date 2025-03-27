package org.fiap.app.rest.request;

import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "O ID do Produto não pode ser nulo. Por favor, forneça um valor.")
    @Positive(message = "O ID deve ser maior que 0")
    private Long produtoId;
    @Positive(message = "O Pedido ID deve ser maior que 0")
    private Long pedidoId;
    @Positive(message = "O ID do Pagamento deve ser maior que 0")
    private Long idPagamento;
    private LocalDateTime dtPagamento;
    private String statusPagamento;
    private LocalDateTime dtAtualizacao = LocalDateTime.now();

}
