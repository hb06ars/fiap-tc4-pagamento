package org.fiap.app.service.postgres;

import org.fiap.domain.dto.PagamentoDTO;
import org.fiap.domain.entity.PagamentoEntity;
import org.fiap.infra.repository.postgres.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PagamentoService {

    private final PagamentoRepository repository;

    @Autowired
    public PagamentoService(PagamentoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public PagamentoDTO save(PagamentoDTO dto) {
        return new PagamentoDTO(repository.save(new PagamentoEntity(dto)));
    }
}

