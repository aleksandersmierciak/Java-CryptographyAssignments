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

    // http://stackoverflow.com/questions/140131/convert-a-string-representation-of-a-hex-dump-to-a-byte-array-using-java
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
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
