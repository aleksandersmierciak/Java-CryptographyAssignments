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
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply((q.subtract(BigInteger.ONE)));

        BigInteger e = BigInteger.valueOf(2);
        for (BigInteger i = e; i.compareTo(phi) < 0; i = i.add(BigInteger.ONE)) {
            BigInteger gcd = phi.gcd(i);
            if (gcd.equals(BigInteger.ONE)) {
                e = i;
                break;
            }
        }

        BigInteger d = e.modInverse(phi);

        this.publicKey = new RsaPublicKey(n, e);
        this.privateKey = new RsaPrivateKey(n, d);
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
