package com.santander.demo.app.domain.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.santander.demo.app.domain.repository.converter.CPFConverter;
import com.santander.demo.app.domain.valueobject.CPF;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseEntity {

    @Column(unique = true)
    @Convert(converter = CPFConverter.class)
    private CPF cpf;
    private String name;

    public static User create(String document, String name) throws Exception {
        CPF cpf = new CPF(document);

        if(!cpf.isValid())
            throw new IllegalArgumentException("CPF inválido");

        return new User(cpf, name);
    }
}
