package com.asmierciak.cryptography.cryptosystems;

public interface Encryptor {
    void setKey(Key key);

    String encrypt(String plaintext);
}
