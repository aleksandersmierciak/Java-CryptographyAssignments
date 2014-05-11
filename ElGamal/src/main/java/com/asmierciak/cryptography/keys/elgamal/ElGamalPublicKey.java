package com.asmierciak.cryptography.keys.elgamal;

import com.asmierciak.cryptography.keys.PublicKey;

import java.math.BigInteger;

public class ElGamalPublicKey implements PublicKey {
    private final BigInteger p;

    private final BigInteger g;

    private final BigInteger y;

    public ElGamalPublicKey(BigInteger p, BigInteger g, BigInteger y) {
        if (p == null) {
            throw new IllegalArgumentException("p cannot be null");
        }
        if (g == null) {
            throw new IllegalArgumentException("g cannot be null");
        }
        if (y == null) {
            throw new IllegalArgumentException("y cannot be null");
        }

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
