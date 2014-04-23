package com.asmierciak.cryptography.ciphers;

import java.math.BigInteger;

public interface Decryptor {
    BigInteger decrypt(BigInteger ciphertext);
}
