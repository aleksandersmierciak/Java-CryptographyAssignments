package com.asmierciak.cryptography.cryptosystems;

public interface Cryptosystem {
    String encrypt(String plaintext);
    String decrypt(String ciphertext);
}
