package com.asmierciak.cryptography.cryptosystems;

import java.math.BigInteger;

public interface TextDecoder {
    String decode(BigInteger number);
}
