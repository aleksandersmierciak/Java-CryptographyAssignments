package com.asmierciak.cryptography.cryptosystems;

import java.math.BigInteger;

public interface Cryptosystem {
    BigInteger[] encrypt(String plaintext);
    String decrypt(BigInteger[] ciphertext);
}
