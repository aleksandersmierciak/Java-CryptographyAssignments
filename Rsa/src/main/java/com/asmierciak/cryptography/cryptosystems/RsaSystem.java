package com.asmierciak.cryptography.cryptosystems;

public class RsaSystem implements Cryptosystem {
    private Encryptor encryptor;
    private Decryptor decryptor;
    private KeyGenerator keyGenerator;

    public RsaSystem(RsaEncryptor encryptor, RsaDecryptor decryptor, RsaKeyGenerator keyGenerator) {
        this.encryptor = encryptor;
        this.decryptor = decryptor;
        this.keyGenerator = keyGenerator;
    }

    @Override
    public String encrypt(String plaintext) {
        return null;
    }

    @Override
    public String decrypt(String ciphertext) {
        return null;
    }
}
