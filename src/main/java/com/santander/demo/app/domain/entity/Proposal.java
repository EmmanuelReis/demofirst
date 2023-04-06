package com.santander.demo.app.domain.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.santander.demo.app.domain.entity.enumeration.ProposalStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "proposals")
@EqualsAndHashCode(callSuper = true)
public class Proposal extends BaseEntity {
    
    @ManyToOne(optional = false) 
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @ManyToOne(optional = false) 
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    private Product product;

    private ProposalStatus status;

    public void toAccept() {
        if(status == ProposalStatus.IN_ANALYSIS)
            status = ProposalStatus.APPROVED;
    }

    public void reject() {
        if(status == ProposalStatus.IN_ANALYSIS)
            status = ProposalStatus.REJECTED;
    }
}
