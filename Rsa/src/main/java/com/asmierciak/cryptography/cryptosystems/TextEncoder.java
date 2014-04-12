package com.asmierciak.cryptography.cryptosystems;

import java.math.BigInteger;

public interface TextEncoder {
    BigInteger encode(String text);
}
