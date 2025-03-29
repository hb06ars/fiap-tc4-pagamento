package org.fiap.infra.exceptions;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GlobalExceptionTest {

    @Test
    void testGlobalExceptionWithMessage() {
        String message = "Erro personalizado";
        GlobalException exception = new GlobalException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testGlobalExceptionWithMessageAndCause() {
        String message = "Erro personalizado com causa";
        Throwable cause = new RuntimeException("Causa do erro");

        GlobalException exception = new GlobalException(message, cause);

        assertEquals(message, exception.getMessage());

        assertEquals(cause, exception.getCause());
    }

    @Test
    void testGlobalExceptionWithNullMessageAndCause() {
        GlobalException exception = new GlobalException(null, null);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }
}
