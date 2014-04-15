package com.asmierciak.cryptography.hashing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class MessageChunkTests {
    private final byte[][] expectedWords;

    private final MessageChunk chunk;

    public MessageChunkTests(byte[] inputData, byte[][] expectedWords) {
        this.expectedWords = expectedWords;
        chunk = new MessageChunk(inputData);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        String[] words = {
                "11000110001110100101001010010111", "01000111011110111111111110110110",
                "01101001001010010110100101011011", "01100110110111111010100111000010",
                "10100101001011100000110110111101", "11011011100010011111111010110101",
                "10000001110011100111001011010010", "00111110001000010000110111101111",
                "10101100111101000111100100001011", "10010101100111000110111101010100",
                "11101000110111001110110001000101", "11001010011100101101011101001101",
                "00110001010010110111100111001111", "11010111101111111111110000101101",
                "01001000101001110111110010011111", "10001000011001001101010001110011"
        };
        StringBuilder builder = new StringBuilder();
        for (String word : words) {
            builder.append(word);
        }
        String bitString = builder.toString();

        Object[][] data = new Object[][]
                {
                        {
                                convertBinaryStringToBytes(bitString),
                                convertBinaryStringsToBytes(words)
                        }
                };
        return Arrays.asList(data);
    }

    private static byte[][] convertBinaryStringsToBytes(String[] binaryStrings) {
        byte[][] byteArrays = new byte[binaryStrings.length][];
        for (int i = 0; i < byteArrays.length; ++i) {
            byteArrays[i] = convertBinaryStringToBytes(binaryStrings[i]);
        }
        return byteArrays;
    }

    private static byte[] convertBinaryStringToBytes(String binaryString) {
        BigInteger number = new BigInteger(binaryString, 2);
        byte[] bytes = number.toByteArray();
        if (bytes[0] == 0) {
            byte[] temp = new byte[bytes.length - 1];
            System.arraycopy(bytes, 1, temp, 0, temp.length);
            bytes = temp;
        }
        return bytes;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsIfChunkSizeInvalid() {
        new MessageChunk(new byte[1]);
    }

    @Test
    public void testWordsAreNotNull() {
        assertThat(chunk.getWords(), is(notNullValue()));
    }

    @Test
    public void testWordsAreValid() {
        assertThat(chunk.getWords(), is(expectedWords));
    }
}