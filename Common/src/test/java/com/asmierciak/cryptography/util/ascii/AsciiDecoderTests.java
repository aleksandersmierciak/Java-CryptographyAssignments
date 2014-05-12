package com.asmierciak.cryptography.util.ascii;

import com.asmierciak.cryptography.util.ascii.AsciiDecoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class AsciiDecoderTests {

    private final String expectedOutput;

    private final String actualOutput;

    public AsciiDecoderTests(BigInteger input, String expectedOutput) {
        this.expectedOutput = expectedOutput;

        AsciiDecoder decoder = new AsciiDecoder();
        actualOutput = decoder.decode(input);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
                {BigInteger.valueOf(65), "A"},
                {BigInteger.valueOf(16705), "AA"}
        };
        return Arrays.asList(data);
    }

    @Test
    public void testDecodedIsNotNull() throws Exception {
        assertThat(actualOutput, is(notNullValue()));
    }

    @Test
    public void testDecodedIsValid() throws Exception {
        assertThat(actualOutput, is(equalTo((expectedOutput))));
    }
}
