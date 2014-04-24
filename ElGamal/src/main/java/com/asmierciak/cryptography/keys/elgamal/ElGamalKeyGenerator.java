package com.asmierciak.cryptography.keys.elgamal;

import com.asmierciak.cryptography.keys.KeyGenerator;

import java.math.BigInteger;
import java.security.SecureRandom;

public class ElGamalKeyGenerator implements KeyGenerator {
    private static final int P_MIN = 4;

    private final BigInteger p;

    private final BigInteger g;

    private BigInteger y;

    private BigInteger x;

    private ElGamalPublicKey publicKey;

    private ElGamalPrivateKey privateKey;

    public ElGamalKeyGenerator(BigInteger p) {
        if (p == null) {
            throw new IllegalArgumentException("p cannot be null");
        }
        if (p.compareTo(BigInteger.valueOf(P_MIN)) < 0) {
            throw new IllegalArgumentException("p cannot be less than " + P_MIN);
        }

        this.p = p;
        this.g = BigInteger.ZERO;
    }

    public ElGamalPublicKey getPublicKey() {
        return publicKey;
    }

    public ElGamalPrivateKey getPrivateKey() {
        return privateKey;
    }

    @Override
    public void generateKeys() {
        chooseSecretKey();
        computeY();

        this.publicKey = new ElGamalPublicKey(p, g, y);
        this.privateKey = new ElGamalPrivateKey(x);
    }

    private void chooseSecretKey() {
        do {
            x = BigInteger.probablePrime(p.bitLength(), new SecureRandom());
        } while (x.compareTo(p.subtract(BigInteger.ONE)) >= 0);
    }

    private void computeY() {
        y = g.modPow(x, p);
    }
}
