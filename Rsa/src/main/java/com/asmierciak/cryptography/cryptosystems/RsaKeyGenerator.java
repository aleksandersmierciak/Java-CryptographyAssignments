package com.asmierciak.cryptography.cryptosystems;

public class RsaKeyGenerator implements KeyGenerator {
    private RsaPublicKey publicKey;
    private RsaPrivateKey privateKey;

    @Override
    public void generateKeys() {

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
