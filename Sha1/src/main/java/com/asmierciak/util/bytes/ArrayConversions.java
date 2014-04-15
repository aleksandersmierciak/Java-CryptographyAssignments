package com.asmierciak.util.bytes;

import java.math.BigInteger;

public class ArrayConversions {
    public static byte[][] binaryStringsToByteArrays(String[] binaryStrings) {
        byte[][] byteArrays = new byte[binaryStrings.length][];
        for (int i = 0; i < byteArrays.length; ++i) {
            byteArrays[i] = binaryStringToByteArray(binaryStrings[i]);
        }
        return byteArrays;
    }

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
}
