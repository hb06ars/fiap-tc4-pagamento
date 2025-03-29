package org.fiap.app.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fiap.app.rest.request.PagamentoRequest;
import org.fiap.domain.dto.PagamentoDTO;
import org.fiap.domain.enums.StatusPagamentoEnum;
import org.fiap.domain.useCase.EfetuarPagamentoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EfetuarPagamentoUseCase efetuarPagamentoUseCase;

    @InjectMocks
    private Controller pagamentoController;

    private PagamentoDTO pagamentoDTO;
    private PagamentoRequest pagamentoRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pagamentoController).build();

        pagamentoDTO = new PagamentoDTO();
        pagamentoDTO.setId(1L);
        pagamentoDTO.setPedidoId(100L);
        pagamentoDTO.setStatusPagamento(StatusPagamentoEnum.ABERTO.getDescricao());

        pagamentoRequest = new PagamentoRequest();
        pagamentoRequest.setPedidoId(100L);
    }

    @Test
    void testCadastro() throws Exception {
        when(efetuarPagamentoUseCase.execute(any(PagamentoDTO.class))).thenReturn(pagamentoDTO);

        mockMvc.perform(post("/pagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(pagamentoRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pedidoId", is(100)));
    }
}