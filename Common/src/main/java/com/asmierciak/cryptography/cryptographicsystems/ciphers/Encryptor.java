package com.asmierciak.cryptography.cryptographicsystems.ciphers;

import java.math.BigInteger;

public interface Encryptor {
    BigInteger encrypt(BigInteger plaintext);
}
