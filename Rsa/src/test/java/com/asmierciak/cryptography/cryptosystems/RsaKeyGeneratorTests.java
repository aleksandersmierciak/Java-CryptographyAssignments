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
public class RsaKeyGeneratorTests {
    private final BigInteger p;

    private final BigInteger q;

    /**
     * Represents the result of multiplying p and q (symbol: n).
     */
    private final BigInteger expectedModulus;

    /**
     * Represents the public exponent (symbol: e).
     */
    private final BigInteger expectedPublicExponent;

    /**
     * Represents the private exponent (symbol: d).
     */
    private final BigInteger expectedPrivateExponent;

    private final RsaKeyGenerator generator;

    private final RsaPublicKey publicKey;

    private final RsaPrivateKey privateKey;

    public RsaKeyGeneratorTests(BigInteger p, BigInteger q,
                                BigInteger expectedModulus,
                                BigInteger expectedPublicExponent, BigInteger expectedPrivateExponent) {
        this.p = p;
        this.q = q;
        this.expectedModulus = expectedModulus;
        this.expectedPublicExponent = expectedPublicExponent;
        this.expectedPrivateExponent = expectedPrivateExponent;

        generator = new RsaKeyGenerator(p, q);
        generator.generateKeys();

        publicKey = generator.getPublicKey();
        privateKey = generator.getPrivateKey();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]
                { {
                    BigInteger.valueOf(61), BigInteger.valueOf(53),
                    BigInteger.valueOf(3233), BigInteger.valueOf(7), BigInteger.valueOf(1783)
                } };
        return Arrays.asList(data);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsIfPNegative() throws Exception {
        new RsaKeyGenerator(BigInteger.valueOf(-1), BigInteger.TEN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsIfPNotGreaterThanOne() throws Exception {
        new RsaKeyGenerator(BigInteger.ONE, BigInteger.TEN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsIfQNegative() throws Exception {
        new RsaKeyGenerator(BigInteger.TEN, BigInteger.valueOf(-1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsIfQNotGreaterThanOne() throws Exception {
        new RsaKeyGenerator(BigInteger.TEN, BigInteger.ONE);
    }

    @Test
    public void testPublicKeyIsNotNull() throws Exception {
        assertThat(publicKey, is(notNullValue()));
    }

    @Test
    public void testPublicKeyModulusIsNotNull() throws Exception {
        assertThat(publicKey.getModulus(), is(equalTo(expectedModulus)));
    }

    @Test
    public void testPublicKeyModulusIsValid() throws Exception {
        assertThat(publicKey.getModulus(), is(equalTo(expectedModulus)));
    }

    @Test
    public void testPublicKeyExponentIsNotNull() throws Exception {
        assertThat(publicKey.getPublicExponent(), is(notNullValue()));
    }

    @Test
    public void testPublicKeyExponentIsValid() throws Exception {
        assertThat(publicKey.getPublicExponent(), is(equalTo(expectedPublicExponent)));
    }

    @Test
    public void testPrivateKeyIsNotNull() throws Exception {
        assertThat(privateKey, is(notNullValue()));
    }

    @Test
    public void testPrivateKeyModulusIsNotNull() throws Exception {
        assertThat(privateKey.getModulus(), is(notNullValue()));
    }

    @Test
    public void testPrivateKeyModulusIsValid() throws Exception {
        assertThat(privateKey.getModulus(), is(equalTo(expectedModulus)));
    }

    @Test
    public void testPrivateKeyExponentIsNotNull() throws Exception {
        assertThat(privateKey.getPrivateExponent(), is(notNullValue()));
    }

    @Test
    public void testPrivateKeyExponentIsValid() throws Exception {
        assertThat(privateKey.getPrivateExponent(), is(equalTo(expectedPrivateExponent)));
    }
}
