package com.asmierciak.cryptography.cryptosystems;

public interface Decryptor {
    void setKey(Key key);

    String decrypt(String ciphertext);
}
