package org.fiap.domain.dto;

import org.fiap.domain.enums.StatusPagamentoEnum;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PedidoDTOTest {

    @Test
    void testConstrutorPadrao() {
        PedidoDTO pedido = new PedidoDTO();
        assertThat(pedido).isNotNull();
        assertThat(pedido.getStatus()).isEqualTo(StatusPagamentoEnum.ABERTO);
        assertThat(pedido.getDtProcessamento()).isNotNull();
        assertThat(pedido.getDtAtualizacao()).isNotNull();
    }

    @Test
    void testConstrutorBuilder() {
        PedidoDTO pedido = PedidoDTO.builder().build();
        assertThat(pedido).isNotNull();
    }

    @Test
    void testEqualsQuandoObjetosSaoIguais() {
        LocalDateTime now = LocalDateTime.now();
        PedidoDTO pedido1 = new PedidoDTO(1L, 10L, "1234567890123456",
                StatusPagamentoEnum.FECHADO_COM_SUCESSO, BigDecimal.valueOf(150.75), now, now, now);

        PedidoDTO pedido2 = new PedidoDTO(1L, 10L, "1234567890123456",
                StatusPagamentoEnum.FECHADO_COM_SUCESSO, BigDecimal.valueOf(150.75), now, now, now);

        assertThat(pedido1).isEqualTo(pedido2);
        assertThat(pedido1.hashCode()).isEqualTo(pedido2.hashCode());
    }

    @Test
    void testConstrutorComParametros() {
        LocalDateTime now = LocalDateTime.now();
        PedidoDTO pedido = new PedidoDTO(1L, 10L, "1234567890123456",
                StatusPagamentoEnum.FECHADO_COM_SUCESSO, BigDecimal.valueOf(150.75), now, now, now);

        assertThat(pedido.getId()).isEqualTo(1L);
        assertThat(pedido.getClienteId()).isEqualTo(10L);
        assertThat(pedido.getNumeroCartaoCredito()).isEqualTo("1234567890123456");
        assertThat(pedido.getStatus()).isEqualTo(StatusPagamentoEnum.FECHADO_COM_SUCESSO);
        assertThat(pedido.getValorTotal()).isEqualTo(BigDecimal.valueOf(150.75));
        assertThat(pedido.getDtPedido()).isEqualTo(now);
        assertThat(pedido.getDtProcessamento()).isEqualTo(now);
        assertThat(pedido.getDtAtualizacao()).isEqualTo(now);
    }

    @Test
    void testConstrutorDeCopia() {
        LocalDateTime now = LocalDateTime.now();
        PedidoDTO pedidoOriginal = new PedidoDTO(1L, 10L, "1234567890123456",
                StatusPagamentoEnum.FECHADO_COM_SUCESSO, BigDecimal.valueOf(150.75), now, now, now);

        PedidoDTO pedidoCopia = new PedidoDTO(pedidoOriginal);

        assertThat(pedidoCopia).isEqualTo(pedidoOriginal);
        assertThat(pedidoCopia).isNotSameAs(pedidoOriginal);
    }

    @Test
    void testEqualsAndHashCode() {
        LocalDateTime now = LocalDateTime.now();
        PedidoDTO pedido1 = new PedidoDTO(1L, 10L, "1234567890123456",
                StatusPagamentoEnum.FECHADO_COM_SUCESSO, BigDecimal.valueOf(150.75), now, now, now);

        PedidoDTO pedido2 = new PedidoDTO(1L, 10L, "1234567890123456",
                StatusPagamentoEnum.FECHADO_COM_SUCESSO, BigDecimal.valueOf(150.75), now, now, now);

        assertThat(pedido1).isEqualTo(pedido2);
        assertThat(pedido1.hashCode()).isEqualTo(pedido2.hashCode());
    }

    @Test
    void testSetters() {
        PedidoDTO pedido = new PedidoDTO();
        pedido.setId(2L);
        pedido.setClienteId(20L);
        pedido.setNumeroCartaoCredito("9876543210987654");
        pedido.setStatus(StatusPagamentoEnum.FECHADO_SEM_CREDITO);
        pedido.setValorTotal(BigDecimal.valueOf(200.50));
        LocalDateTime now = LocalDateTime.now();
        pedido.setDtPedido(now);
        pedido.setDtProcessamento(now);
        pedido.setDtAtualizacao(now);

        assertThat(pedido.getId()).isEqualTo(2L);
        assertThat(pedido.getClienteId()).isEqualTo(20L);
        assertThat(pedido.getNumeroCartaoCredito()).isEqualTo("9876543210987654");
        assertThat(pedido.getStatus()).isEqualTo(StatusPagamentoEnum.FECHADO_SEM_CREDITO);
        assertThat(pedido.getValorTotal()).isEqualTo(BigDecimal.valueOf(200.50));
        assertThat(pedido.getDtPedido()).isEqualTo(now);
        assertThat(pedido.getDtProcessamento()).isEqualTo(now);
        assertThat(pedido.getDtAtualizacao()).isEqualTo(now);
    }
}
