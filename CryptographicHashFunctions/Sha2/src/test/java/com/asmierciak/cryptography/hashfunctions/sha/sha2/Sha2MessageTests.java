package com.asmierciak.cryptography.hashfunctions.sha.sha2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class Sha2MessageTests {
    private final Sha2Message message;

    private final int expectedLength;

    private final int expectedChunkCount;

    private final byte[] expectedOutput;

    public Sha2MessageTests(byte[] input, byte[] expectedOutput, int expectedLength, int expectedChunkCount) {
        this.expectedLength = expectedLength;
        this.expectedChunkCount = expectedChunkCount;
        this.expectedOutput = expectedOutput;

        message = new Sha2Message(input);
        message.calculateHash();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        // Test case data found below is UTF-8 data for different phrases,
        // their SHA-1 hex hash converted to bytes,
        // expected length of message as a whole,
        // expected number of chunks in the message.
        Object[][] data = new Object[][]
                {
                        {
                                // TODO: add test data
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
    public void testHashBytesAreNotNull() {
        assertThat(message.getHashBytes(), is(notNullValue()));
    }

    @Test
    public void testHashBytesIsValid() {
        assertThat(message.getHashBytes(), is(expectedOutput));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfInputIsEmptyArray() {
        new Sha2Message(new byte[0]);
    }
}
