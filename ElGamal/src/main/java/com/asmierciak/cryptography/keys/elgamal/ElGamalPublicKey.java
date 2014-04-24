package com.asmierciak.cryptography.keys.elgamal;

import com.asmierciak.cryptography.keys.PublicKey;

import java.math.BigInteger;

public class ElGamalPublicKey implements PublicKey {
    private final BigInteger p;

    private final BigInteger g;

    private final BigInteger y;

    public ElGamalPublicKey(BigInteger p, BigInteger g, BigInteger y) {
        this.p = p;
        this.g = g;
        this.y = y;
    }

    public BigInteger getP() {
        return p;
    }
    public BigInteger getG() {
        return g;
    }
    public BigInteger getY() {
        return y;
    }
}
