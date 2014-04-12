package com.asmierciak.cryptography.cryptosystems;

import java.math.BigInteger;

public interface Encryptor {
    BigInteger encrypt(BigInteger plaintext);
}
