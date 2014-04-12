package com.asmierciak.cryptography.cryptosystems;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class RsaDecryptorTests {
    private final BigInteger input;

    private final BigInteger expectedOutput;

    private final RsaDecryptor decryptor;

    private final BigInteger actualOutput;

    public RsaDecryptorTests(BigInteger input, BigInteger expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;

        RsaPrivateKey key = new RsaPrivateKey(BigInteger.valueOf(3233), BigInteger.valueOf(1783));
        decryptor = new RsaDecryptor(key);
        actualOutput = decryptor.decrypt(input);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
                {BigInteger.valueOf(65), BigInteger.valueOf(2093)},
                {BigInteger.valueOf(16705), BigInteger.valueOf(1955)}
        };
        return Arrays.asList(data);
    }

    @Test
    public void testOutputIsNotNull() throws Exception {
        assertThat(actualOutput, is(notNullValue()));
    }

    @Test
    public void testOutputIsValid() throws Exception {
        assertThat(actualOutput, is(equalTo(expectedOutput)));
    }
}
