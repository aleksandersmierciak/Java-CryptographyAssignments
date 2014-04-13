package com.asmierciak.cryptography.util.ascii;

import com.asmierciak.cryptography.util.TextDecoder;

import java.math.BigInteger;

public class AsciiDecoder implements TextDecoder {
    @Override
    public String decode(BigInteger number) {
        StringBuilder builder = new StringBuilder();
        while (number.compareTo(BigInteger.ZERO) > 0) {
            builder.append((char)number.byteValue());
            number = number.shiftRight(8);
        }
        return builder.toString();
    }
}