package org.fiap.infra.config.integration;

import org.fiap.infra.exceptions.GatewayResponseErrorHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.StandardIntegrationFlow;
import org.springframework.messaging.MessageChannel;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PedidoConfigurationTest {

    @InjectMocks
    private PedidoConfiguration pedidoConfiguration;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPagamento() {
        MessageChannel channel = pedidoConfiguration.pagamento();
        assertNotNull(channel);
        assertTrue(channel instanceof DirectChannel);
    }

    @Test
    void testProdutoChannel() {
        IntegrationFlow channel = pedidoConfiguration.rollBackPed();
        assertNotNull(channel);
        assertTrue(channel instanceof StandardIntegrationFlow);
    }

    @Test
    void testFindByProdutoSkuFlow() {
        IntegrationFlow flow = pedidoConfiguration.findById();
        assertNotNull(flow);
    }

    @Test
    void testErrorHandler() {
        GatewayResponseErrorHandler errorHandler = new GatewayResponseErrorHandler();
        assertNotNull(errorHandler);
    }
}
