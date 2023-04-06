package com.santander.demo.app.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.santander.demo.app.domain.argument.response.ProposalResponse;
import com.santander.demo.app.domain.entity.Proposal;

@Mapper(componentModel = "spring")
public interface ProposalMapper extends BaseMapper<Proposal, ProposalResponse> {
 
    @Mapping(source = "entity.user.id", target = "user")
    @Mapping(source = "entity.product.id", target = "product")
    ProposalResponse toResponse(Proposal entity);
}
