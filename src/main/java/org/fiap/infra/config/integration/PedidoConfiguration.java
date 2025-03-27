package org.fiap.infra.config.integration;

import org.fiap.domain.dto.PedidoDTO;
import org.fiap.infra.exceptions.GatewayResponseErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.http.dsl.Http;
import org.springframework.messaging.MessageChannel;

import static org.fiap.domain.util.UrlConstants.PEDIDO_BASE_URL;

@Configuration
public class PedidoConfiguration {

    @Bean
    public MessageChannel pagamento() {
        DirectChannel directChannel = new DirectChannel();
        directChannel.setFailover(false);
        return directChannel;
    }

    @Bean
    public IntegrationFlow findById() {
        return IntegrationFlow.from("pedidoFindById")
                .handle(Http.outboundGateway(m -> PEDIDO_BASE_URL.concat("/" + m.getPayload()))
                        .httpMethod(HttpMethod.GET)
                        .expectedResponseType(PedidoDTO.class)
                        .errorHandler(new GatewayResponseErrorHandler())
                )
                .log().bridge().get();
    }

}
