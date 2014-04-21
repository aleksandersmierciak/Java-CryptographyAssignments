package com.asmierciak.cryptography.hashing;

import com.asmierciak.util.numbers.LongConversions;

public class MessageFiller {
    public static byte[] fillMessage(byte[] input) {
        if (input == null || input.length == 0) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        byte[] output = createOutputArray(input);
        appendOne(output, input.length);
        appendOriginalLength(output, input.length);

        return output;
    }

    private static byte[] createOutputArray(byte[] input) {
        int outputLength = calculateOutputLength(input.length);
        byte[] output = new byte[outputLength];
        System.arraycopy(input, 0, output, 0, input.length);
        return output;
    }

    private static int calculateOutputLength(int inputLength) {
        int transientLength = inputLength + 1;
        int paddedLength = transientLength -(transientLength % 64) + 56;
        return paddedLength + 8;
    }

    private static void appendOne(byte[] array, int position) {
        array[position] = (byte)0x80;
    }

    private static void appendOriginalLength(byte[] array, int originalLength) {
        byte[] longBytes = LongConversions.longToBytes(originalLength * 8L);
        System.arraycopy(longBytes, 0, array, array.length - 8, 8);
    }
}
