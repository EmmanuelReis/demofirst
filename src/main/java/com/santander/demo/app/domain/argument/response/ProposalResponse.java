package com.santander.demo.app.domain.argument.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProposalResponse {
    
    private Long id;
    private Long user;
    private Long product;
    private String status;
}
