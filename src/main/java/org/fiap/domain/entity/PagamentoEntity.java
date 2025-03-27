package org.fiap.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fiap.domain.dto.PagamentoDTO;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pagamento")
public class PagamentoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pedido_id", nullable = false, unique = true)
    @Positive(message = "O Pedido ID deve ser maior que 0")
    private Long pedidoId;

    @Column(name = "idpagamento", nullable = false, unique = true)
    @Positive(message = "O ID do Pagamento deve ser maior que 0")
    private Long idPagamento;

    @Column(name = "dt_pagamento")
    private LocalDateTime dtPagamento;

    @Column(name = "status_pagamento")
    private String statusPagamento;

    @Column(name = "dt_atualizacao")
    private LocalDateTime dtAtualizacao;


    public PagamentoEntity(PagamentoDTO dto) {
        this.id = dto.getId();
        this.pedidoId = dto.getPedidoId();
        this.idPagamento = dto.getIdPagamento();
        this.dtPagamento = dto.getDtPagamento();
        this.statusPagamento = dto.getStatusPagamento();
        this.dtAtualizacao = dto.getDtAtualizacao();
    }
}
