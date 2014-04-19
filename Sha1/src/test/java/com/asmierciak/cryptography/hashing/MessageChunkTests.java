package com.asmierciak.cryptography.hashing;

import com.asmierciak.util.bytes.ArrayConversions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class MessageChunkTests {
    private final int[] expectedWords;

    private final MessageChunk chunk;

    public MessageChunkTests(byte[] inputData, int[] expectedWords) {
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
                                ArrayConversions.binaryStringToByteArray(bitString),
                                ArrayConversions.binaryStringsToIntegers(words)
                        }
                };
        return Arrays.asList(data);
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
    public void testThereAre80Words() {
        assertThat(chunk.getWords().length, is(equalTo(80)));
    }

    @Test
    public void testInitialWordsAreValid() {
        int[] actual = Arrays.copyOfRange(chunk.getWords(), 0, 16);
        int[] expected = Arrays.copyOfRange(expectedWords, 0, 16);
        assertThat(actual, is(expected));
    }

    @Test
    public void testHashIsNotNull() {
        chunk.calculateHash(new int[5]);
        assertThat(chunk.getHash(), is(notNullValue()));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfHashGivenIsNot20Bytes() {
        chunk.calculateHash(new int[6]);
    }
}
