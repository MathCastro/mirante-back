package com.example.mirante.security.model;

import com.example.mirante.operator.Operator;

import java.util.Date;

public class AuthenticationResponse {

    private final String token;

    private final Operator operator;

    private final Date expireAt;

    public AuthenticationResponse(String token, Operator operator, Date expireAt) {
        this.token = token;
        this.operator = operator;
        this.expireAt = expireAt;
    }

    public String getToken() {
        return token;
    }

    public Operator getOperator() {
        return operator;
    }

    public Date getExpireAt() {
        return expireAt;
    }
}
