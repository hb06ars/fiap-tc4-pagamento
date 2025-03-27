package org.fiap.app.service.postgres;

import lombok.extern.slf4j.Slf4j;
import org.fiap.app.gateway.GatewayApi;
import org.fiap.domain.dto.PagamentoDTO;
import org.fiap.domain.dto.PedidoDTO;
import org.fiap.domain.entity.PagamentoEntity;
import org.fiap.domain.enums.StatusPagamentoEnum;
import org.fiap.infra.repository.postgres.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
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

        PagamentoEntity pagamento = new PagamentoEntity(dto);
        pagamento.setValorTotal(pedido.getValorTotal());

        String idResponseMockApi = gerarRespostaApiPagamento(pedido);
        if (idResponseMockApi != null) {
            pagamento.setIdPagamento(idResponseMockApi);
            pagamento.setStatusPagamento(StatusPagamentoEnum.FECHADO_COM_SUCESSO.getDescricao());
            log.info("Pedido validado com sucesso");
        } else {
            log.error("Não há credito no cartão, pedido fechado sem crédito.");
            pagamento.setStatusPagamento(StatusPagamentoEnum.FECHADO_SEM_CREDITO.getDescricao());
            log.error("Efetuando rollback do Estoque");
            gatewayApi.pedidoRollBack(new GenericMessage<>(new PagamentoDTO(pagamento)));
        }
        return new PagamentoDTO(repository.save(pagamento));
    }

    private String gerarRespostaApiPagamento(PedidoDTO pedido) {
        log.info("Chamando API de Pagamento Mockada");
        if (!pedido.getNumeroCartaoCredito().equalsIgnoreCase("00000000000000")) {
            log.info("Pagamento confirmado.");
            return UUID.randomUUID().toString();
        } else {
            log.error("Pagamento não confirmado.");
            return null;
        }
    }
}

