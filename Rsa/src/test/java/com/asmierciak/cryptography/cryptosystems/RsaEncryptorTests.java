package com.asmierciak.cryptography.cryptosystems;

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
public class RsaEncryptorTests {
    private final BigInteger input;

    private final BigInteger expectedOutput;

    private final RsaEncryptor encryptor;

    private final BigInteger actualOutput;

    public RsaEncryptorTests(BigInteger input, BigInteger expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;

        RsaPublicKey key = new RsaPublicKey(BigInteger.valueOf(3233), BigInteger.valueOf(7));
        encryptor = new RsaEncryptor(key);
        actualOutput = encryptor.encrypt(input);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
                {BigInteger.valueOf(2093), BigInteger.valueOf(65)},
                {BigInteger.valueOf(1955), BigInteger.valueOf(540)}
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
