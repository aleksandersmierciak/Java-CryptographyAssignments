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
        // We iterate starting at the least significant bit;
        // a reverse is necessary.
        return builder.reverse().toString();
    }
}
