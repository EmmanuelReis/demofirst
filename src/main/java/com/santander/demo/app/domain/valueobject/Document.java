package com.santander.demo.app.domain.valueobject;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Document {

    protected final String document;

    public abstract String formatted();

	public abstract boolean isValid();

    protected String onlyNumbers() {
        return document.replaceAll("\\D", "");
    }
}
