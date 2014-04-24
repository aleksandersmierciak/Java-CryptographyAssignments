package com.asmierciak.cryptography.keys.elgamal;

import com.asmierciak.cryptography.keys.PrivateKey;

import java.math.BigInteger;

public class ElGamalPrivateKey implements PrivateKey {
    private final BigInteger x;

    public ElGamalPrivateKey(BigInteger x) {
        this.x = x;
    }

    public BigInteger getX() {
        return x;
    }
}
