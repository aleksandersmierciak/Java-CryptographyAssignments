package com.asmierciak.util.bytes;

import java.util.Arrays;

public class ArraySplitter {
    public static byte[][] split(byte[] input, int sizeLimit) {
        int splitCount = (input.length + sizeLimit - 1) / sizeLimit;
        byte[][] output = new byte[splitCount][];
        for (int i = 0; i < splitCount; ++i) {
            int end = Math.min((i + 1) * sizeLimit, input.length);
            output[i] = Arrays.copyOfRange(input, i * sizeLimit, end);
        }
        return output;
    }
}
