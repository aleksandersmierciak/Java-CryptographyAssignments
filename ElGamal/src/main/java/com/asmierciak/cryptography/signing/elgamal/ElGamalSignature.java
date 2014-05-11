package com.asmierciak.cryptography.signing.elgamal;

import java.math.BigInteger;

public class ElGamalSignature {
    private final BigInteger r;

    private final BigInteger s;

    public ElGamalSignature(BigInteger r, BigInteger s) {
        if (r == null) {
            throw new IllegalArgumentException("r cannot be null");
        }
        if (r.compareTo(BigInteger.ZERO) <= 0) {
            throw new IllegalArgumentException("r cannot be non-positive");
        }

        if (s == null) {
            throw new IllegalArgumentException("s cannot be null");
        }
        if (s.compareTo(BigInteger.ZERO) <= 0) {
            throw new IllegalArgumentException("s cannot be non-positive");
        }

        this.r = r;
        this.s = s;
    }

    public BigInteger getR() {
        return r;
    }

    public BigInteger getS() {
        return s;
    }
}
