package com.santander.demo.app.domain.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProposalStatus {
    IN_ANALYSIS("Em análise"),
    APPROVED("Aprovada"),
    REJECTED("Rejeitada");

    private String status;
}
