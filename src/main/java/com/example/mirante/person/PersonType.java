package com.example.mirante.person;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PersonType {
    FISICA, JURIDICA;

    @JsonValue
    public int toValue() {
        return ordinal();
    }
}
