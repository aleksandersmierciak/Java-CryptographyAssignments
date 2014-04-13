package com.asmierciak.cryptography.ciphers.rsa;

import com.asmierciak.cryptography.ciphers.rsa.RsaDecryptor;
import com.asmierciak.cryptography.keys.rsa.RsaPrivateKey;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class RsaDecryptorTests {
    private final BigInteger expectedOutput;

    private final BigInteger actualOutput;

    public RsaDecryptorTests(BigInteger input, BigInteger expectedOutput) {
        this.expectedOutput = expectedOutput;

        RsaPrivateKey key = new RsaPrivateKey(BigInteger.valueOf(3233), BigInteger.valueOf(1783));
        RsaDecryptor decryptor = new RsaDecryptor(key);
        actualOutput = decryptor.decrypt(input);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
                {BigInteger.valueOf('\n'), BigInteger.valueOf(1372)},
                {BigInteger.valueOf('A'), BigInteger.valueOf(2093)},
                {BigInteger.valueOf('a'), BigInteger.valueOf(187)}
        };
        return Arrays.asList(data);
    }

    @Test
    public void testOutputIsNotNull() throws Exception {
        Assert.assertThat(actualOutput, CoreMatchers.is(CoreMatchers.notNullValue()));
    }

    @Test
    public void testOutputIsValid() throws Exception {
        Assert.assertThat(actualOutput, CoreMatchers.is(CoreMatchers.equalTo(expectedOutput)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsIfInputIsGreaterThanModulus() {
        RsaPrivateKey key = new RsaPrivateKey(BigInteger.ONE, BigInteger.ONE);
        RsaDecryptor decryptor = new RsaDecryptor(key);
        decryptor.decrypt(BigInteger.TEN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsIfInputIsEqualToModulus() {
        RsaPrivateKey key = new RsaPrivateKey(BigInteger.ONE, BigInteger.ONE);
        RsaDecryptor decryptor = new RsaDecryptor(key);
        decryptor.decrypt(BigInteger.ONE);
    }
}
