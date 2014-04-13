package com.asmierciak.cryptography.keys;

public interface KeyGenerator {
    void generateKeys();

    PublicKey getPublicKey();
    PrivateKey getPrivateKey();
}
