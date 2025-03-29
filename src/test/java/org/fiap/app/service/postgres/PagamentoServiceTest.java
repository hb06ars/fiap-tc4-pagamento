package org.fiap.app.service.postgres;

import org.fiap.app.gateway.GatewayApi;
import org.fiap.domain.dto.PagamentoDTO;
import org.fiap.domain.dto.PedidoDTO;
import org.fiap.domain.entity.PagamentoEntity;
import org.fiap.domain.enums.StatusPagamentoEnum;
import org.fiap.infra.repository.postgres.PagamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.support.GenericMessage;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PagamentoServiceTest {

    @Mock
    private PagamentoRepository repository;

    @Mock
    private GatewayApi gatewayApi;

    @InjectMocks
    private PagamentoService pagamentoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave_PagamentoComSucesso() {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setValorTotal(new BigDecimal("100.00"));
        pedidoDTO.setNumeroCartaoCredito("12345678901234");

        PagamentoDTO pagamentoDTO = new PagamentoDTO();
        pagamentoDTO.setPedidoId(1L);

        when(gatewayApi.pedidoFindById(any())).thenReturn(pedidoDTO);
        when(repository.save(any(PagamentoEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        PagamentoDTO resultado = pagamentoService.save(pagamentoDTO);

        assertNotNull(resultado);
        assertNotNull(resultado.getIdPagamento());
        assertEquals(StatusPagamentoEnum.FECHADO_COM_SUCESSO.getDescricao(), resultado.getStatusPagamento());
        assertEquals(new BigDecimal("100.00"), resultado.getValorTotal());

        verify(repository, times(1)).save(any(PagamentoEntity.class));
    }

    @Test
    void testSave_PagamentoSemCredito() {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setValorTotal(new BigDecimal("100.00"));
        pedidoDTO.setNumeroCartaoCredito("00000000000000");

        PagamentoDTO pagamentoDTO = new PagamentoDTO();
        pagamentoDTO.setPedidoId(1L);

        when(gatewayApi.pedidoFindById(any())).thenReturn(pedidoDTO);
        when(repository.save(any(PagamentoEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        PagamentoDTO resultado = pagamentoService.save(pagamentoDTO);

        assertNotNull(resultado);
        assertNull(resultado.getIdPagamento());
        assertEquals(StatusPagamentoEnum.FECHADO_SEM_CREDITO.getDescricao(), resultado.getStatusPagamento());

        verify(gatewayApi, times(1)).pedidoRollBack(any(GenericMessage.class));
        verify(repository, times(1)).save(any(PagamentoEntity.class));
    }

    @Test
    void testGerarRespostaApiPagamento_CartaoValido() {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setNumeroCartaoCredito("12345678901234");
        PagamentoDTO pagamentoDTO = new PagamentoDTO();
        pagamentoDTO.setPedidoId(1L);
        pagamentoDTO.setValorTotal(BigDecimal.TEN);

        when(gatewayApi.pedidoFindById(any())).thenReturn(pedidoDTO);
        when(repository.save(any(PagamentoEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        String idPagamento = pagamentoService.save(pagamentoDTO).getIdPagamento();

        assertNotNull(idPagamento, "O pagamento deveria ter sido aprovado.");
    }

    @Test
    void testGerarRespostaApiPagamento_CartaoInvalido() {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setNumeroCartaoCredito("00000000000000");
        pedidoDTO.setValorTotal(BigDecimal.TEN);

        PagamentoDTO pagamentoDTO = new PagamentoDTO();
        pagamentoDTO.setPedidoId(1L);
        pagamentoDTO.setValorTotal(BigDecimal.TEN);

        when(gatewayApi.pedidoFindById(any())).thenReturn(pedidoDTO);
        when(repository.save(any(PagamentoEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        String idPagamento = pagamentoService.save(pagamentoDTO).getIdPagamento();

        assertNull(idPagamento, "O pagamento deveria ter sido negado.");
    }
}
