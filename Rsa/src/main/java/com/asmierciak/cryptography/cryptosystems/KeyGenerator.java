package com.asmierciak.cryptography.cryptosystems;

public interface KeyGenerator {
    void generateKeys();

    Key getPublicKey();
    Key getPrivateKey();
}
