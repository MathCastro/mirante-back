package com.example.mirante.phone;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PhoneType {
    CELULAR, FIXO, COMERCIAL;

    @JsonValue
    public int toValue() {
        return ordinal();
    }
}
