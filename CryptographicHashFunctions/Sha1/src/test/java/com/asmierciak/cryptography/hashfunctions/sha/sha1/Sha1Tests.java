package com.asmierciak.cryptography.hashfunctions.sha.sha1;

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
public class Sha1Tests {
    private final String actualHash;

    private final String expectedHash;

    public Sha1Tests(String input, String expectedHash) {
        HashFunction sha1 = new Sha1();
        actualHash = sha1.hash(input);
        this.expectedHash = expectedHash;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]
                {
                        {
                                "Ala ma kota",
                                "43fd70009a97a7d311c5644047ccc700f8d08a9d"
                        },
                        {
                                "The quick brown fox jumps over the lazy dog",
                                "2fd4e1c67a2d28fced849ee1bb76e7391b93eb12"
                        },
                        {
                                "Zażółć gęślą jaźń",
                                "839b3ecd39c5ac4118a24e3ebee3401b0f62d1fc"
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
        new Sha1().hash(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfInputIsEmpty() {
        new Sha1().hash("");
    }
}
