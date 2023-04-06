package com.santander.demo.app.domain.repository.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.santander.demo.app.domain.valueobject.CPF;

@Converter
public class CPFConverter implements AttributeConverter<CPF, String> {

    @Override
    public String convertToDatabaseColumn(CPF cpf) {
        return cpf == null ? null : cpf.formatted();
    }

    @Override
    public CPF convertToEntityAttribute(String cpf) {
        return cpf == null ? null : new CPF(cpf);
    }
}
