package com.asmierciak.cryptography.cryptosystems;

import java.math.BigInteger;

public class RsaEncryptor implements Encryptor {
    private RsaPublicKey key;

    public RsaEncryptor(RsaPublicKey key) {
        this.key = key;
    }

    @Override
    public BigInteger encrypt(BigInteger plaintext) {
        return plaintext.modPow(key.getPublicExponent(), key.getModulus());
    }
}
