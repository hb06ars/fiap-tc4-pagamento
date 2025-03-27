package org.fiap.app.gateway;

import org.fiap.domain.dto.PedidoDTO;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway
public interface GatewayApi {

    @Gateway(requestChannel = "pedidoFindById", requestTimeout = 5000)
    PedidoDTO pedidoFindById(Message<Long> id);
}
