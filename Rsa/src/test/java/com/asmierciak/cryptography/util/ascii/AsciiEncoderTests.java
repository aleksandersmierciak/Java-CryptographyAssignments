package com.asmierciak.cryptography.util.ascii;

import com.asmierciak.cryptography.util.ascii.AsciiEncoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class AsciiEncoderTests {
    private final String input;

    private final BigInteger expectedOutput;

    private final AsciiEncoder encoder;

    private final BigInteger actualOutput;

    public AsciiEncoderTests(String input, BigInteger expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;

        encoder = new AsciiEncoder();
        actualOutput = encoder.encode(input);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
                {"A", BigInteger.valueOf(65)},
                {"AA", BigInteger.valueOf(16705)}
        };
        return Arrays.asList(data);
    }

    @Test
    public void testEncodedIsNotNull() throws Exception {
        assertThat(actualOutput, is(notNullValue()));
    }

    @Test
    public void testEncodedIsValid() throws Exception {
        assertThat(actualOutput, is(equalTo((expectedOutput))));
    }
}
