package com.asmierciak.cryptography.hashfunctions.sha.sha2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class Sha2MessageChunkTests {
    private final int[] expectedWords;

    private final int[] expectedHash;

    private final Sha2MessageChunk chunk;

    public Sha2MessageChunkTests(byte[] inputData, int[] expectedWords, int[] expectedHash) {
        this.expectedWords = expectedWords;
        this.expectedHash = expectedHash;

        chunk = new Sha2MessageChunk(inputData);
        chunk.calculateHash(Arrays.copyOf(Sha2MessageChunk.INITIAL_HASH, Sha2MessageChunk.INITIAL_HASH.length));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]
                {
                        { // "Ala ma kota"
                                new byte[]{65,108,97,32,109,97,32,107,111,116,97,-128,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,88},
                                new int[]{0},
                                new int[]{0}
                        },
                };
        return Arrays.asList(data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsIfChunkSizeInvalid() {
        new Sha2MessageChunk(new byte[1]);
    }

    @Test
    public void testWordsAreNotNull() {
        assertThat(chunk.getWords(), is(notNullValue()));
    }

    @Test
    public void testThereAre64Words() {
        assertThat(chunk.getWords().length, is(equalTo(64)));
    }

    @Test
    public void testInitialWordsAreValid() {
        int[] actual = Arrays.copyOfRange(chunk.getWords(), 0, 16);
        int[] expected = Arrays.copyOfRange(expectedWords, 0, 16);
        assertThat(actual, is(expected));
    }

    @Test
    public void testAllWordsAreValid() {
        assertThat(chunk.getWords(), is(expectedWords));
    }

    @Test
    public void testHashIsNotNull() {
        assertThat(chunk.getHash(), is(notNullValue()));
    }

    @Test
    public void testHashIsValid() {
        assertThat(chunk.getHash(), is(equalTo(expectedHash)));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfHashGivenIsNot32Bytes() {
        chunk.calculateHash(new int[6]);
    }
}
