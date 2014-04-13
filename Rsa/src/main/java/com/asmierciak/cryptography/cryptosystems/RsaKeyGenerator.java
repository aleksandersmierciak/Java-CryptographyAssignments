package com.asmierciak.cryptography.cryptosystems;

import java.math.BigInteger;

public class RsaKeyGenerator implements KeyGenerator {
    private BigInteger p;
    private BigInteger q;

    private RsaPublicKey publicKey;
    private RsaPrivateKey privateKey;

    public RsaKeyGenerator(BigInteger p, BigInteger q) {
        if (p == null) throw new IllegalArgumentException("p can not be null");
        if (q == null) throw new IllegalArgumentException("q can not be null");

        if (p.compareTo(BigInteger.ONE) <= 0) throw new IllegalArgumentException("p must be greater than 1");
        if (q.compareTo(BigInteger.ONE) <= 0) throw new IllegalArgumentException("p must be greater than 1");

        this.p = p;
        this.q = q;
    }

    @Override
    public void generateKeys() {
        BigInteger n = p.multiply(q);
        BigInteger phi = eulerTotient(p, q);

        BigInteger e = firstCoprimeOf(phi);
        BigInteger d = e.modInverse(phi);

        this.publicKey = new RsaPublicKey(n, e);
        this.privateKey = new RsaPrivateKey(n, d);
    }

    private BigInteger eulerTotient(BigInteger p, BigInteger q) {
        return (p.subtract(BigInteger.ONE)).multiply((q.subtract(BigInteger.ONE)));
    }

    private static BigInteger firstCoprimeOf(BigInteger value) {
        BigInteger coprime = BigInteger.valueOf(2);
        for (BigInteger i = coprime; i.compareTo(value) < 0; i = i.add(BigInteger.ONE)) {
            BigInteger gcd = value.gcd(i);
            if (gcd.equals(BigInteger.ONE)) {
                coprime = i;
                break;
            }
        }
        return coprime;
    }

    @Override
    public RsaPublicKey getPublicKey() {
        return publicKey;
    }

    @Override
    public RsaPrivateKey getPrivateKey() {
        return privateKey;
    }
}
