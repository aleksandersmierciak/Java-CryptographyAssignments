package com.asmierciak.cryptography.util.ascii;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class AsciiEndecTests {
    private final String input;

    private final String actualOutput;

    public AsciiEndecTests(String input) {
        this.input = input;

        AsciiEncoder encoder = new AsciiEncoder();
        AsciiDecoder decoder = new AsciiDecoder();

        BigInteger encoded = encoder.encode(input);
        actualOutput = decoder.decode(encoded);
    }

    @Parameterized.Parameters
    public static Collection<String[]> data() {
        String[][] data = new String[][]
                {
                        {"A quick brown fox jumped over the lazy dog."},
                        {"What is love? Baby don't hurt me, don't hurt me, no more"},
                        {"Come with me if you want to live!"},
                        {"Madness...? This... is... SPARTA!!!"}
                };
        return Arrays.asList(data);
    }

    @Test
    public void testActualOutputIsNotNull() {
        assertThat(actualOutput, is(notNullValue()));
    }

    @Test
    public void testActualOutputEqualsInput() {
        assertThat(actualOutput, is(input));
    }
}
