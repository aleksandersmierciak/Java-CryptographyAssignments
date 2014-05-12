package com.asmierciak.cryptography.signatureschemes.elgamal.keys;

import com.asmierciak.cryptography.cryptographicsystems.keys.KeyGenerator;

import java.math.BigInteger;
import java.security.SecureRandom;

public class ElGamalKeyGenerator implements KeyGenerator {
    private static final int P_MIN = 4;

    private final SecureRandom random = new SecureRandom();

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
        BigInteger gValue;
        do {
            gValue = new BigInteger(p.bitLength(), random);
        } while (gValue.compareTo(p) >= 0);
        this.g = gValue;
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
            x = new BigInteger(p.bitLength(), random);
        } while (!isInValidRangeForASecretKey(x));
    }

    private boolean isInValidRangeForASecretKey(BigInteger value) {
        return (value.compareTo(p.subtract(BigInteger.ONE)) < 0 &&
                value.compareTo(BigInteger.ONE) > 0);
    }

    private void computeY() {
        y = g.modPow(x, p);
    }
}
