package com.asmierciak.cryptography.signatureschemes.elgamal.signing;

import com.asmierciak.cryptography.signatureschemes.elgamal.signing.ElGamalSignature;
import org.junit.Test;

import java.math.BigInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ElGamalSignatureTests {
    private final ElGamalSignature signature;

    public ElGamalSignatureTests() {
        signature = new ElGamalSignature(BigInteger.ONE, BigInteger.ONE);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfRIsNull() {
        new ElGamalSignature(null, BigInteger.ONE);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfRIsZero() {
        new ElGamalSignature(BigInteger.ZERO, BigInteger.ONE);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfRIsNegative() {
        new ElGamalSignature(BigInteger.valueOf(-1), BigInteger.ONE);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfSIsNull() {
        new ElGamalSignature(BigInteger.ONE, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfSIsZero() {
        new ElGamalSignature(BigInteger.ONE, BigInteger.ZERO);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfSIsNegative() {
        new ElGamalSignature(BigInteger.ONE, BigInteger.valueOf(-1));
    }

    @Test
    public void testRIsNotNull() {
        assertThat(signature.getR(), is(notNullValue()));
    }

    @Test
    public void testSIsNotNull() {
        assertThat(signature.getS(), is(notNullValue()));
    }
}
