package com.asmierciak.cryptography.hashfunctions.sha.sha2;

import com.asmierciak.cryptography.hashfunctions.HashFunction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class Sha2Tests {
    private final String actualHash;

    private final String expectedHash;

    public Sha2Tests(String input, String expectedHash) {
        HashFunction sha2 = new Sha2();
        actualHash = sha2.hash(input);
        this.expectedHash = expectedHash;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]
                {
                        {
                                "Ala ma kota",
                                "124bfb6284d82f3b1105f88e3e7a0ee02d0e525193413c05b75041917022cd6e"
                        },
                        {
                                "The quick brown fox jumps over the lazy dog",
                                "d7a8fbb307d7809469ca9abcb0082e4f8d5651e46d3cdb762d02d0bf37c9e592"
                        },
                        {
                                "Zażółć gęślą jaźń",
                                "bc5348fd7c2dd8bbf411f0b9268265f7c2e0d31ebf314695882b8170c7e1e9d7"
                        }
                };
        return Arrays.asList(data);
    }

    @Test
    public void testHashIsNotNull() {
        assertThat(actualHash, is(notNullValue()));
    }

    @Test
    public void testHashIsValid() {
        assertThat(actualHash, is(equalTo(expectedHash)));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfInputIsNull() {
        new Sha2().hash(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfInputIsEmpty() {
        new Sha2().hash("");
    }
}
