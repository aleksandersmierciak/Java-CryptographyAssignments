package com.asmierciak.cryptography.cryptosystems;

import java.math.BigInteger;

public class RsaDecryptor implements Decryptor {
    private RsaPrivateKey key;

    public RsaDecryptor(RsaPrivateKey key) {
        this.key = key;
    }

    @Override
    public BigInteger decrypt(BigInteger ciphertext) {
        if (ciphertext.compareTo(key.getModulus()) >= 0) {
            throw new IllegalArgumentException("Input must be less than the key modulus");
        }

        return ciphertext.modPow(key.getPrivateExponent(), key.getModulus());
    }
}
