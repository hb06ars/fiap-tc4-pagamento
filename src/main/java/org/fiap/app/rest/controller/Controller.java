package org.fiap.app.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.fiap.app.rest.request.PagamentoRequest;
import org.fiap.domain.dto.PagamentoDTO;
import org.fiap.domain.useCase.EfetuarPagamentoUseCase;
import org.fiap.domain.util.HttpStatusCodes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/pagamento")
@Slf4j
public class Controller {

    private final EfetuarPagamentoUseCase efetuarPagamentoUseCase;

    public Controller(EfetuarPagamentoUseCase efetuarPagamentoUseCase) {
        this.efetuarPagamentoUseCase = efetuarPagamentoUseCase;
    }

    @Operation(summary = "Efetuar Pagamento",
            description = "Efetuar Pagamento.")
    @ApiResponse(responseCode = HttpStatusCodes.OK, description = "Pagamento processado.")
    @PostMapping
    public ResponseEntity<PagamentoDTO> cadastro(@Valid @RequestBody PagamentoRequest request) {
        log.info("requisição para efetuar pagamento foi efetuada");
        return ResponseEntity.ok(efetuarPagamentoUseCase.execute(new PagamentoDTO(request)));
    }

}