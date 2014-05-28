package com.asmierciak.cryptography.hashfunctions.sha.sha1;

import com.asmierciak.cryptography.hashfunctions.sha.sha1.Sha1Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class Sha1MessageTests {
    private final Sha1Message message;

    private final int expectedLength;

    private final int expectedChunkCount;

    private final byte[] expectedOutput;

    public Sha1MessageTests(byte[] input, byte[] expectedOutput, int expectedLength, int expectedChunkCount) {
        this.expectedLength = expectedLength;
        this.expectedChunkCount = expectedChunkCount;
        this.expectedOutput = expectedOutput;

        message = new Sha1Message(input);
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
                        { // "Ala ma kota"
                                new byte[]{65,108,97,32,109,97,32,107,111,116,97},
                                new byte[]{67,-3,112,0,-102,-105,-89,-45,17,-59,100,64,71,-52,-57,0,-8,-48,-118,-99},
                                64,
                                1
                        },
                        { // "The quick brown fox jumps over the lazy dog"
                                new byte[]{84,104,101,32,113,117,105,99,107,32,98,114,111,119,110,32,102,111,120,32,106,117,109,112,115,32,111,118,101,114,32,116,104,101,32,108,97,122,121,32,100,111,103},
                                new byte[]{47,-44,-31,-58,122,45,40,-4,-19,-124,-98,-31,-69,118,-25,57,27,-109,-21,18},
                                64,
                                1
                        },
                        { // "Zażółć gęślą jaźń"
                                new byte[]{90,97,-59,-68,-61,-77,-59,-126,-60,-121,32,103,-60,-103,-59,-101,108,-60,-123,32,106,97,-59,-70,-59,-124},
                                new byte[]{-125,-101,62,-51,57,-59,-84,65,24,-94,78,62,-66,-29,64,27,15,98,-47,-4},
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
        new Sha1Message(new byte[0]);
    }
}
