package com.asmierciak.cryptography.ciphers.rsa;

import com.asmierciak.cryptography.ciphers.rsa.RsaEncryptor;
import com.asmierciak.cryptography.keys.rsa.RsaPublicKey;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class RsaEncryptorTests {
    private final BigInteger expectedOutput;

    private final BigInteger actualOutput;

    public RsaEncryptorTests(BigInteger input, BigInteger expectedOutput) {
        this.expectedOutput = expectedOutput;

        RsaPublicKey key = new RsaPublicKey(BigInteger.valueOf(3233), BigInteger.valueOf(7));
        RsaEncryptor encryptor = new RsaEncryptor(key);
        actualOutput = encryptor.encrypt(input);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
                {BigInteger.valueOf(1372), BigInteger.valueOf('\n')},
                {BigInteger.valueOf(2093), BigInteger.valueOf('A')},
                {BigInteger.valueOf(187), BigInteger.valueOf('a')}
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
        RsaPublicKey key = new RsaPublicKey(BigInteger.ONE, BigInteger.ONE);
        RsaEncryptor encryptor = new RsaEncryptor(key);
        encryptor.encrypt(BigInteger.TEN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsIfInputIsEqualToModulus() {
        RsaPublicKey key = new RsaPublicKey(BigInteger.ONE, BigInteger.ONE);
        RsaEncryptor encryptor = new RsaEncryptor(key);
        encryptor.encrypt(BigInteger.ONE);
    }
}