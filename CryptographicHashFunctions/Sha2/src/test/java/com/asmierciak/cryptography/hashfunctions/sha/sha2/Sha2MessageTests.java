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
                                new byte[]{18,75,-5,98,-124,-40,47,59,17,5,-8,-114,62,122,14,-32,45,14,82,81,-109,65,60,5,-73,80,65,-111,112,34,-51,110},
                                64,
                                1
                        },
                        {
                                new byte[]{84,104,101,32,113,117,105,99,107,32,98,114,111,119,110,32,102,111,120,32,106,117,109,112,115,32,111,118,101,114,32,116,104,101,32,108,97,122,121,32,100,111,103},
                                new byte[]{-41,-88,-5,-77,7,-41,-128,-108,105,-54,-102,-68,-80,8,46,79,-115,86,81,-28,109,60,-37,118,45,2,-48,-65,55,-55,-27,-110},
                                64,
                                1
                        },
                        {
                                new byte[]{90,97,-59,-68,-61,-77,-59,-126,-60,-121,32,103,-60,-103,-59,-101,108,-60,-123,32,106,97,-59,-70,-59,-124},
                                new byte[]{-68,83,72,-3,124,45,-40,-69,-12,17,-16,-71,38,-126,101,-9,-62,-32,-45,30,-65,49,70,-107,-120,43,-127,112,-57,-31,-23,-41},
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
