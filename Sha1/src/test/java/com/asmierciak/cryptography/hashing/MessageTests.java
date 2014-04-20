package com.asmierciak.cryptography.hashing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class MessageTests {
    private final Message message;

    private final int expectedLength;

    private final int expectedChunkCount;

    private final byte[] expectedOutput;

    public MessageTests(byte[] input, byte[] expectedOutput, int expectedLength, int expectedChunkCount) {
        message = new Message(input);

        this.expectedLength = expectedLength;
        this.expectedChunkCount = expectedChunkCount;
        this.expectedOutput = expectedOutput;

        message.calculateHash();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        byte[] inputBytes = {65,108,97,32,109,97,32,107,111,116,97}; // byte representation of UTF-8 "Ala ma kota"
        byte[] outputBytes = {-85,-15,-31,92,88,-69,107,72,80,-75,50,84,51,56,100,-58,16,50,10,50};

        Object[][] data = new Object[][]
                {
                        { inputBytes, outputBytes, 64, 1 }
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
        new Message(new byte[0]);
    }
}
