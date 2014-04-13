package com.asmierciak.cryptography.ciphers.rsa;

import com.asmierciak.cryptography.ciphers.Decryptor;
import com.asmierciak.cryptography.keys.rsa.RsaPrivateKey;

import java.math.BigInteger;

public class RsaDecryptor implements Decryptor {
    private RsaPrivateKey key;

    public RsaDecryptor(RsaPrivateKey key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
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
