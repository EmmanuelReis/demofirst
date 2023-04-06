package com.santander.demo.app.services;

import com.santander.demo.app.domain.argument.request.ProposalRequest;
import com.santander.demo.app.domain.argument.response.ProposalResponse;

public interface IProposalService {
    
    ProposalResponse createProposal(ProposalRequest request) throws Exception;

    ProposalResponse getProposal(Long id) throws Exception;

    void rejectProposal(Long id) throws Exception;

    void toAcceptProposal(Long id) throws Exception;
}
