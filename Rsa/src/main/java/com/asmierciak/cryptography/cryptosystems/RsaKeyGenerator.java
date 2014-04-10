package com.asmierciak.cryptography.cryptosystems;

public class RsaKeyGenerator implements KeyGenerator {
    private Key publicKey;
    private Key privateKey;

    @Override
    public void generateKeys() {

    }

    @Override
    public Key getPublicKey() {
        return publicKey;
    }

    @Override
    public Key getPrivateKey() {
        return privateKey;
    }
}
