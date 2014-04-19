package com.asmierciak.util.bytes;

import java.math.BigInteger;

public class ArrayConversions {
    public static byte[] binaryStringToByteArray(String binaryString) {
        BigInteger number = new BigInteger(binaryString, 2);
        byte[] bytes = number.toByteArray();
        if (bytes[0] == 0) {
            byte[] temp = new byte[bytes.length - 1];
            System.arraycopy(bytes, 1, temp, 0, temp.length);
            bytes = temp;
        }
        return bytes;
    }

    public static int[] binaryStringsToIntegers(String[] binaryStrings) {
        int[] integers = new int[binaryStrings.length];
        for (int i = 0; i < integers.length; ++i) {
            integers[i] = binaryStringToInteger(binaryStrings[i]);
        }
        return integers;
    }

    public static int binaryStringToInteger(String binaryString) {
        return (int)Long.parseLong(binaryString, 2);
    }
}
