package com.asmierciak.cryptography.cryptosystems;

public interface KeyGenerator {
    void generateKeys();

    PublicKey getPublicKey();
    PrivateKey getPrivateKey();
}
