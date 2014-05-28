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
        // their SHA-2 hex hash converted to bytes,
        // expected length of message as a whole,
        // expected number of chunks in the message.
        Object[][] data = new Object[][]
                {
                        {
                                new byte[]{65,108,97,32,109,97,32,107,111,116,97},
                                new byte[]{49,50,52,98,102,98,54,50,56,52,100,56,50,102,51,98,49,49,48,53,102,56,56,101,51,101,55,97,48,101,101,48,50,100,48,101,53,50,53,49,57,51,52,49,51,99,48,53,98,55,53,48,52,49,57,49,55,48,50,50,99,100,54,101},
                                64,
                                1
                        },
                        {
                                new byte[]{84,104,101,32,113,117,105,99,107,32,98,114,111,119,110,32,102,111,120,32,106,117,109,112,115,32,111,118,101,114,32,116,104,101,32,108,97,122,121,32,100,111,103},
                                new byte[]{100,55,97,56,102,98,98,51,48,55,100,55,56,48,57,52,54,57,99,97,57,97,98,99,98,48,48,56,50,101,52,102,56,100,53,54,53,49,101,52,54,100,51,99,100,98,55,54,50,100,48,50,100,48,98,102,51,55,99,57,101,53,57,50},
                                64,
                                1
                        },
                        {
                                new byte[]{90,97,-59,-68,-61,-77,-59,-126,-60,-121,32,103,-60,-103,-59,-101,108,-60,-123,32,106,97,-59,-70,-59,-124},
                                new byte[]{98,99,53,51,52,56,102,100,55,99,50,100,100,56,98,98,102,52,49,49,102,48,98,57,50,54,56,50,54,53,102,55,99,50,101,48,100,51,49,101,98,102,51,49,52,54,57,53,56,56,50,98,56,49,55,48,99,55,101,49,101,57,100,55},
                                64,
                                1
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
