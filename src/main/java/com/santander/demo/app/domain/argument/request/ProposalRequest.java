package com.santander.demo.app.domain.argument.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProposalRequest {
    
    @NotNull
    private Long userId;

    @NotNull
    private Long productId;
}
