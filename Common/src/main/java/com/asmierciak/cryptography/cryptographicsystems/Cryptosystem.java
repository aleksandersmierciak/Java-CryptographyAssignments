package com.asmierciak.cryptography.cryptographicsystems;

import java.math.BigInteger;

public interface Cryptosystem {
    BigInteger[] encrypt(String plaintext);
    String decrypt(BigInteger[] ciphertext);
}
