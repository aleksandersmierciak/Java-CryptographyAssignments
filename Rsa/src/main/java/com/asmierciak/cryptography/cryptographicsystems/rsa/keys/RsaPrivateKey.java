package com.asmierciak.cryptography.cryptographicsystems.rsa.keys;

import java.math.BigInteger;

public class RsaPrivateKey implements RsaKey {
    private BigInteger modulus;

    private BigInteger privateExponent;

    public RsaPrivateKey(BigInteger modulus, BigInteger privateExponent) {
        this.modulus = modulus;
        this.privateExponent = privateExponent;
    }

    @Override
    public BigInteger getModulus() {
        return modulus;
    }

    public BigInteger getPrivateExponent() {
        return privateExponent;
    }
}
