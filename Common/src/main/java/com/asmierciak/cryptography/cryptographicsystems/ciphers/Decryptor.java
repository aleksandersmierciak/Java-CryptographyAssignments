package com.asmierciak.cryptography.cryptographicsystems.ciphers;

import java.math.BigInteger;

public interface Decryptor {
    BigInteger decrypt(BigInteger ciphertext);
}
