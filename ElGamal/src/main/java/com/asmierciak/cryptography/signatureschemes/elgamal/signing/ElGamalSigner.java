package com.asmierciak.cryptography.signatureschemes.elgamal.signing;

import com.asmierciak.cryptography.signatureschemes.elgamal.keys.ElGamalPrivateKey;
import com.asmierciak.cryptography.signatureschemes.elgamal.keys.ElGamalPublicKey;
import com.asmierciak.cryptography.signatureschemes.signing.SignatureSigner;

import java.math.BigInteger;
import java.security.SecureRandom;

public class ElGamalSigner implements SignatureSigner {
    private final ElGamalPublicKey publicKey;

    private final ElGamalPrivateKey privateKey;

    private final BigInteger p;

    private BigInteger k;

    private BigInteger r;

    private BigInteger s;

    private ElGamalSignature signature;

    public ElGamalSigner(ElGamalPublicKey publicKey, ElGamalPrivateKey privateKey) {
        if (publicKey == null) {
            throw new IllegalArgumentException("Public key cannot be null");
        }

        if (privateKey == null) {
            throw new IllegalArgumentException("Private key cannot be null");
        }

        this.publicKey = publicKey;
        this.privateKey = privateKey;

        p = publicKey.getP();
    }

    public ElGamalSignature getSignature() {
        return signature;
    }

    @Override
    public void generateSignature(BigInteger m) {
        if (m == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        do {
            findK();
            computeR();
            computeS(m);
        } while (r.compareTo(BigInteger.ZERO) == 0 || s.compareTo(BigInteger.ZERO) == 0);

        signature = new ElGamalSignature(r, s);
    }

    private void findK() {
        SecureRandom random = new SecureRandom();
        do {
            k = new BigInteger(p.bitLength(), random);
        } while (!isInValidRangeForK(k) || !isPrimeToPMinusOne(k));
    }

    private boolean isInValidRangeForK(BigInteger value) {
        return (value.compareTo(BigInteger.ONE) > 0 &&
                value.compareTo(p.subtract(BigInteger.ONE)) < 0);
    }

    private boolean isPrimeToPMinusOne(BigInteger value) {
        return value.gcd(p.subtract(BigInteger.ONE)).compareTo(BigInteger.ONE) == 0;
    }

    private void computeR() {
        BigInteger g = publicKey.getG();
        r = g.modPow(k, p);
    }

    private void computeS(BigInteger m) {
        BigInteger x = privateKey.getX();
        BigInteger pSub1 = p.subtract(BigInteger.ONE);

        BigInteger modifiedHash = m.subtract(x.multiply(r));
        BigInteger modInverseOfK = k.modInverse(pSub1);
        s = (modifiedHash.multiply(modInverseOfK)).mod(pSub1);
    }
}
