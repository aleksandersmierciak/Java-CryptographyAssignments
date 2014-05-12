package com.asmierciak.cryptography.signatureschemes.elgamal.keys;

import com.asmierciak.cryptography.cryptographicsystems.keys.PrivateKey;

import java.math.BigInteger;

public class ElGamalPrivateKey implements PrivateKey {
    private final BigInteger x;

    public ElGamalPrivateKey(BigInteger x) {
        if (x == null) {
            throw new IllegalArgumentException("x cannot be null");
        }

        this.x = x;
    }

    public BigInteger getX() {
        return x;
    }
}
