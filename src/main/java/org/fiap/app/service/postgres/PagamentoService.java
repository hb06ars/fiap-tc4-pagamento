package org.fiap.app.service.postgres;

import org.fiap.app.gateway.GatewayApi;
import org.fiap.domain.dto.PagamentoDTO;
import org.fiap.domain.dto.PedidoDTO;
import org.fiap.domain.entity.PagamentoEntity;
import org.fiap.infra.repository.postgres.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PagamentoService {

    private final PagamentoRepository repository;
    private final GatewayApi gatewayApi;

    @Autowired
    public PagamentoService(PagamentoRepository repository, GatewayApi gatewayApi) {
        this.repository = repository;
        this.gatewayApi = gatewayApi;
    }

    @Transactional
    public PagamentoDTO save(PagamentoDTO dto) {
        PedidoDTO pedido = gatewayApi.pedidoFindById(new GenericMessage<>(dto.getPedidoId()));
        return new PagamentoDTO(repository.save(new PagamentoEntity(dto)));
    }
}

