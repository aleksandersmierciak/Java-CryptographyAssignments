package com.asmierciak.cryptography.keys.rsa;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class RsaKeyGeneratorTests {
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

    private final RsaPublicKey publicKey;

    private final RsaPrivateKey privateKey;

    public RsaKeyGeneratorTests(BigInteger p, BigInteger q,
                                BigInteger expectedModulus,
                                BigInteger expectedPublicExponent, BigInteger expectedPrivateExponent) {
        this.expectedModulus = expectedModulus;
        this.expectedPublicExponent = expectedPublicExponent;
        this.expectedPrivateExponent = expectedPrivateExponent;

        RsaKeyGenerator generator = new RsaKeyGenerator(p, q);
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
    public void testThrowsIfPIsNegative() throws Exception {
        new RsaKeyGenerator(BigInteger.valueOf(-1), BigInteger.TEN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsIfPIsNotGreaterThanOne() throws Exception {
        new RsaKeyGenerator(BigInteger.ONE, BigInteger.TEN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsIfQIsNegative() throws Exception {
        new RsaKeyGenerator(BigInteger.TEN, BigInteger.valueOf(-1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsIfQIsNotGreaterThanOne() throws Exception {
        new RsaKeyGenerator(BigInteger.TEN, BigInteger.ONE);
    }

    @Test
    public void testPublicKeyIsNotNull() throws Exception {
        Assert.assertThat(publicKey, CoreMatchers.is(CoreMatchers.notNullValue()));
    }

    @Test
    public void testPublicKeyModulusIsNotNull() throws Exception {
        Assert.assertThat(publicKey.getModulus(), CoreMatchers.is(CoreMatchers.equalTo(expectedModulus)));
    }

    @Test
    public void testPublicKeyModulusIsValid() throws Exception {
        Assert.assertThat(publicKey.getModulus(), CoreMatchers.is(CoreMatchers.equalTo(expectedModulus)));
    }

    @Test
    public void testPublicKeyExponentIsNotNull() throws Exception {
        Assert.assertThat(publicKey.getPublicExponent(), CoreMatchers.is(CoreMatchers.notNullValue()));
    }

    @Test
    public void testPublicKeyExponentIsValid() throws Exception {
        Assert.assertThat(publicKey.getPublicExponent(), CoreMatchers.is(CoreMatchers.equalTo(expectedPublicExponent)));
    }

    @Test
    public void testPrivateKeyIsNotNull() throws Exception {
        Assert.assertThat(privateKey, CoreMatchers.is(CoreMatchers.notNullValue()));
    }

    @Test
    public void testPrivateKeyModulusIsNotNull() throws Exception {
        Assert.assertThat(privateKey.getModulus(), CoreMatchers.is(CoreMatchers.notNullValue()));
    }

    @Test
    public void testPrivateKeyModulusIsValid() throws Exception {
        Assert.assertThat(privateKey.getModulus(), CoreMatchers.is(CoreMatchers.equalTo(expectedModulus)));
    }

    @Test
    public void testPrivateKeyExponentIsNotNull() throws Exception {
        Assert.assertThat(privateKey.getPrivateExponent(), CoreMatchers.is(CoreMatchers.notNullValue()));
    }

    @Test
    public void testPrivateKeyExponentIsValid() throws Exception {
        Assert.assertThat(privateKey.getPrivateExponent(), CoreMatchers.is(CoreMatchers.equalTo(expectedPrivateExponent)));
    }
}
