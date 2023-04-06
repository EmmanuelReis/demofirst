package com.santander.demo.app.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.santander.demo.app.domain.entity.Proposal;

public interface ProposalRepository extends CrudRepository<Proposal, Long> {
    
}
