package org.fiap.domain.useCase.impl;

import lombok.extern.slf4j.Slf4j;
import org.fiap.app.service.postgres.PagamentoService;
import org.fiap.domain.dto.PagamentoDTO;
import org.fiap.domain.useCase.EfetuarPagamentoUseCase;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EfetuarPagamentoUseCaseImpl implements EfetuarPagamentoUseCase {

    private final PagamentoService service;

    public EfetuarPagamentoUseCaseImpl(PagamentoService service) {
        this.service = service;
    }

    @Override
    public PagamentoDTO execute(PagamentoDTO dto) {
        return service.save(dto);
    }
}
