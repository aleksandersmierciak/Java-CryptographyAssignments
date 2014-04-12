package com.asmierciak.cryptography.cryptosystems;

import java.math.BigInteger;

public interface Decryptor {
    BigInteger decrypt(BigInteger ciphertext);
}
