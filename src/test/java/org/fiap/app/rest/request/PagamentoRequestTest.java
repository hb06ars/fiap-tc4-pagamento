package org.fiap.app.rest.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.fiap.domain.enums.StatusPagamentoEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PagamentoRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testPagamentoRequest_Success() {
        PagamentoRequest request = PagamentoRequest.builder()
                .id(1L)
                .pedidoId(10L)
                .idPagamento("12345ABC")
                .dtPagamento(LocalDateTime.now())
                .statusPagamento(StatusPagamentoEnum.FECHADO_COM_SUCESSO.getDescricao())
                .dtAtualizacao(LocalDateTime.now())
                .valorTotal(new BigDecimal("99.99"))
                .build();

        Set<ConstraintViolation<PagamentoRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty(), "Deveria passar sem erros de validação");
    }

    @Test
    void testPagamentoRequest_InvalidPedidoId() {
        PagamentoRequest request = PagamentoRequest.builder()
                .pedidoId(0L)
                .valorTotal(new BigDecimal("50.00"))
                .build();

        Set<ConstraintViolation<PagamentoRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Deveria falhar devido a pedidoId inválido");
        assertEquals(1, violations.size());
        assertEquals("O Pedido ID deve ser maior que 0", violations.iterator().next().getMessage());
    }

    @Test
    void testPagamentoRequest_GettersAndSetters() {
        PagamentoRequest request = new PagamentoRequest();
        request.setId(2L);
        request.setPedidoId(20L);
        request.setIdPagamento("XYZ123");
        request.setDtPagamento(LocalDateTime.of(2025, 5, 10, 14, 30));
        request.setStatusPagamento("PENDENTE");
        request.setDtAtualizacao(LocalDateTime.of(2025, 5, 11, 10, 0));
        request.setValorTotal(new BigDecimal("150.50"));

        assertEquals(2L, request.getId());
        assertEquals(20L, request.getPedidoId());
        assertEquals("XYZ123", request.getIdPagamento());
        assertEquals(LocalDateTime.of(2025, 5, 10, 14, 30), request.getDtPagamento());
        assertEquals("PENDENTE", request.getStatusPagamento());
        assertEquals(LocalDateTime.of(2025, 5, 11, 10, 0), request.getDtAtualizacao());
        assertEquals(new BigDecimal("150.50"), request.getValorTotal());
    }
}
