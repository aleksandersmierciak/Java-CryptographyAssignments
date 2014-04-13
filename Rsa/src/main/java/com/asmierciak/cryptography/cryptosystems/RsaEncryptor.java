package com.asmierciak.cryptography.cryptosystems;

import java.math.BigInteger;

public class RsaEncryptor implements Encryptor {
    private RsaPublicKey key;

    public RsaEncryptor(RsaPublicKey key) {
        this.key = key;
    }

    @Override
    public BigInteger encrypt(BigInteger plaintext) {
        if (plaintext.compareTo(key.getModulus()) >= 0) {
            throw new IllegalArgumentException("Input must be less than the key modulus");
        }

        return plaintext.modPow(key.getPublicExponent(), key.getModulus());
    }
}
