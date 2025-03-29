package org.fiap.domain.useCase.impl;

import org.fiap.app.service.postgres.PagamentoService;
import org.fiap.domain.dto.PagamentoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EfetuarPagamentoUseCaseImplTest {

    @Mock
    private PagamentoService pagamentoService;

    @InjectMocks
    private EfetuarPagamentoUseCaseImpl efetuarPagamentoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveExecutarPagamentoComSucesso() {
        PagamentoDTO pagamentoDTO = new PagamentoDTO();
        when(pagamentoService.save(pagamentoDTO)).thenReturn(pagamentoDTO);
        PagamentoDTO resultado = efetuarPagamentoUseCase.execute(pagamentoDTO);
        assertThat(resultado).isNotNull();
        assertThat(resultado).isEqualTo(pagamentoDTO);
        verify(pagamentoService, times(1)).save(pagamentoDTO);
    }

    @Test
    void deveChamarPagamentoServiceSomenteUmaVez() {
        PagamentoDTO pagamentoDTO = new PagamentoDTO();
        when(pagamentoService.save(pagamentoDTO)).thenReturn(pagamentoDTO);
        efetuarPagamentoUseCase.execute(pagamentoDTO);
        verify(pagamentoService, times(1)).save(pagamentoDTO);
    }
}
