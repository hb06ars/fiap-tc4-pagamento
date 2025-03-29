package org.fiap.domain.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PagamentoEntityTest {

    @Test
    void deveCriarInstanciaComConstrutorPadrao() {
        PagamentoEntity pagamento = new PagamentoEntity();

        assertThat(pagamento).isNotNull();
        assertThat(pagamento.getId()).isNull();
        assertThat(pagamento.getPedidoId()).isNull();
        assertThat(pagamento.getIdPagamento()).isNull();
        assertThat(pagamento.getDtPagamento()).isNull();
        assertThat(pagamento.getStatusPagamento()).isNull();
        assertThat(pagamento.getDtAtualizacao()).isNull();
        assertThat(pagamento.getValorTotal()).isNull();
    }
}
