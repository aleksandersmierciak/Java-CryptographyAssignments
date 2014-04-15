package com.asmierciak.cryptography.hashing;

import com.asmierciak.util.bytes.ArrayConversions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class MessageTests {
    private final Message message;

    private final int expectedLength;

    private final int expectedChunkCount;

    private final byte[] expectedHash;

    public MessageTests(byte[] input, int expectedLength, int expectedChunkCount) {
        message = new Message(input);

        this.expectedLength = expectedLength;
        this.expectedChunkCount = expectedChunkCount;

        message.calculateHash();

        expectedHash = message.getHash();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]
                {
                        {
                                ArrayConversions.binaryStringToByteArray(
                                        "1100011000111010010100101001011101000111011110111111111110110110" +
                                        "0110100100101001011010010101101101100110110111111010100111000010" +
                                        "1010010100101110000011011011110111011011100010011111111010110101" +
                                        "1000000111001110011100101101001000111110001000010000110111101111" +
                                        "1010110011110100011110010000101110010101100111000110111101010100" +
                                        "1110100011011100111011000100010111001010011100101101011101001101" +
                                        "0011000101001011011110011100111111010111101111111111110000101101" +
                                        "0100100010100111011111001001111110001000011001001101010001110011"),
                                64, 1
                        }
                };
        return Arrays.asList(data);
    }

    @Test
    public void testMessageLengthIsValid() {
        assertThat(message.getLength(), is(expectedLength));
    }

    @Test
    public void testChunkCountIsValid() {
        assertThat(message.getChunks().size(), is(expectedChunkCount));
    }

    @Test
    public void testChunksAreNotNull() {
        assertThat(message.getChunks(), is(notNullValue()));
    }

    @Test
    public void testHashIsNotNull() {
        assertThat(message.getHash(), is(notNullValue()));
    }

    @Test
    public void testHashIsValid() {
        assertThat(message.getHash(), is(expectedHash));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfInputIsEmptyArray() {
        new Message(new byte[0]);
    }
}
