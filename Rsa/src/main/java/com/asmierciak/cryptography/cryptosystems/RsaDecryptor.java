package com.asmierciak.cryptography.cryptosystems;

import java.math.BigInteger;

public class RsaDecryptor implements Decryptor {
    private RsaPrivateKey key;

    public RsaDecryptor(RsaPrivateKey key) {
        this.key = key;
    }

    @Override
    public BigInteger decrypt(BigInteger ciphertext) {
        return ciphertext.modPow(key.getPrivateExponent(), key.getModulus());
    }
}
