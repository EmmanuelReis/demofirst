package com.santander.demo.app.domain.repository.converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.santander.demo.app.domain.entity.enumeration.ProposalStatus;

@Converter(autoApply = true)
public class ProposalStatusConverter implements AttributeConverter<ProposalStatus, String> {
 
    @Override
    public String convertToDatabaseColumn(ProposalStatus status) {
        if (status == null) {
            return null;
        }
        return status.getStatus();
    }

    @Override
    public ProposalStatus convertToEntityAttribute(String status) {
        if (status == null) {
            return null;
        }

        return Stream.of(ProposalStatus.values())
            .filter(c -> c.getStatus().equals(status))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }
}
