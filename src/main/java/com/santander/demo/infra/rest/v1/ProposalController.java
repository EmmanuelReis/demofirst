package com.santander.demo.infra.rest.v1;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.santander.demo.app.domain.argument.request.ProposalRequest;
import com.santander.demo.app.domain.argument.response.ProposalResponse;
import com.santander.demo.app.services.IProposalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/proposal")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProposalController {
    
    private final IProposalService proposalService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProposalResponse saveProposal(@Valid @RequestBody ProposalRequest request) throws Exception {
        return proposalService.createProposal(request);
    }

    @GetMapping("/{id}")
    public ProposalResponse getProposal(@PathVariable("id") Long id) throws Exception {
        return proposalService.getProposal(id);
    }

    @PostMapping("/{id}/reject")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void rejectProposal(@PathVariable("id") Long id) throws Exception {
        proposalService.rejectProposal(id);
    }

    @PostMapping("/{id}/accept")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void acceptProposal(@PathVariable("id") Long id) throws Exception {
        proposalService.toAcceptProposal(id);
    }
}
