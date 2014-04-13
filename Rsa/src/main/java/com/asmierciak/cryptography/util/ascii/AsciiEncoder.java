package com.asmierciak.cryptography.util.ascii;

import com.asmierciak.cryptography.util.TextEncoder;

import java.math.BigInteger;

public class AsciiEncoder implements TextEncoder {
    @Override
    public BigInteger encode(String text) {
        BigInteger number = BigInteger.ZERO;
        for (char currentChar : text.toCharArray()) {
            number = number.shiftLeft(8);
            number = number.add(BigInteger.valueOf(currentChar));
        }
        return number;
    }
}
