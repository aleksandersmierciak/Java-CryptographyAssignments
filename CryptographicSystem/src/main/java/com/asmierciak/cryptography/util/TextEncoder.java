package com.asmierciak.cryptography.util;

import java.math.BigInteger;

public interface TextEncoder {
    BigInteger encode(String text);
}
