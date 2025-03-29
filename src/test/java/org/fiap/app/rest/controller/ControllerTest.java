package org.fiap.app.rest.controller;

import org.fiap.app.rest.request.PagamentoRequest;
import org.fiap.domain.dto.PagamentoDTO;
import org.fiap.domain.enums.StatusPagamentoEnum;
import org.fiap.domain.useCase.EfetuarPagamentoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ControllerTest {

    @InjectMocks
    private Controller controller;

    @Mock
    private EfetuarPagamentoUseCase efetuarPagamentoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastro_Sucesso() {
        PagamentoRequest request = PagamentoRequest.builder()
                .pedidoId(1L)
                .valorTotal(BigDecimal.valueOf(100.00))
                .build();

        PagamentoDTO pagamentoDTO = PagamentoDTO.builder()
                .pedidoId(1L)
                .valorTotal(BigDecimal.valueOf(100.00))
                .statusPagamento(StatusPagamentoEnum.FECHADO_COM_SUCESSO.getDescricao())
                .build();

        when(efetuarPagamentoUseCase.execute(any(PagamentoDTO.class))).thenReturn(pagamentoDTO);

        ResponseEntity<PagamentoDTO> response = controller.cadastro(request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(pagamentoDTO, response.getBody());
        verify(efetuarPagamentoUseCase, times(1)).execute(any(PagamentoDTO.class));
    }
}
