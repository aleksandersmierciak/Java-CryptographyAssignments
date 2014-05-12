package com.asmierciak.cryptography.cryptographicsystems.rsa.keys;

import java.math.BigInteger;

public class RsaPublicKey implements RsaKey {
    private BigInteger modulus;

    private BigInteger publicExponent;

    public RsaPublicKey(BigInteger modulus, BigInteger publicExponent) {
        this.modulus = modulus;
        this.publicExponent = publicExponent;
    }

    @Override
    public BigInteger getModulus() {
        return modulus;
    }

    public BigInteger getPublicExponent() {
        return publicExponent;
    }
}
