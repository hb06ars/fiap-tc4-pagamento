package org.fiap.domain.useCase;

import org.fiap.domain.dto.PagamentoDTO;

public interface EfetuarPagamentoUseCase {
    PagamentoDTO execute(PagamentoDTO dto);
}
