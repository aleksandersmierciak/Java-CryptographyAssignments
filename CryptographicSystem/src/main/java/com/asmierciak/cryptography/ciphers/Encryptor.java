package com.asmierciak.cryptography.ciphers;

import java.math.BigInteger;

public interface Encryptor {
    BigInteger encrypt(BigInteger plaintext);
}
