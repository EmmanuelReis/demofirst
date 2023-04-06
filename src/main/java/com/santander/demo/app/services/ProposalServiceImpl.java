package com.santander.demo.app.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.santander.demo.app.domain.argument.request.ProposalRequest;
import com.santander.demo.app.domain.argument.response.ProposalResponse;
import com.santander.demo.app.domain.entity.Product;
import com.santander.demo.app.domain.entity.Proposal;
import com.santander.demo.app.domain.entity.User;
import com.santander.demo.app.domain.entity.enumeration.ProposalStatus;
import com.santander.demo.app.domain.mapper.ProposalMapper;
import com.santander.demo.app.domain.repository.ProductRepository;
import com.santander.demo.app.domain.repository.ProposalRepository;
import com.santander.demo.app.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProposalServiceImpl implements IProposalService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProposalMapper proposalMapper;
    private final ProposalRepository proposalRepository;

    @Override
    public ProposalResponse createProposal(ProposalRequest request) throws Exception {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new NotFoundException());
        Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new NotFoundException());

        return proposalMapper.toResponse(proposalRepository.save(new Proposal(user, product, ProposalStatus.IN_ANALYSIS)));
    }

    @Override
    public ProposalResponse getProposal(Long id) throws Exception {
        if(Objects.isNull(id)) throw new IllegalArgumentException();

        Proposal proposal = proposalRepository.findById(id).orElseThrow(() -> new NotFoundException());

        return proposalMapper.toResponse(proposal);
    }

    @Override
    public void rejectProposal(Long id) throws Exception {
        if(Objects.isNull(id)) throw new IllegalArgumentException();

        Proposal proposal = proposalRepository.findById(id).orElseThrow(() -> new NotFoundException());
        proposal.reject();

        proposalRepository.save(proposal);
    }

    @Override
    public void toAcceptProposal(Long id) throws Exception {
        if(Objects.isNull(id)) throw new IllegalArgumentException();

        Proposal proposal = proposalRepository.findById(id).orElseThrow(() -> new NotFoundException());
        proposal.toAccept();

        proposalRepository.save(proposal);
    }
}
