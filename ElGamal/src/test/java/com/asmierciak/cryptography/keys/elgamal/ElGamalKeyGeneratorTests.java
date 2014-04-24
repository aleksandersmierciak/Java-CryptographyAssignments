package com.asmierciak.cryptography.keys.elgamal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(Parameterized.class)
public class ElGamalKeyGeneratorTests {
    private static final int P_MIN = 4;

    private final BigInteger p;

    private final ElGamalPublicKey publicKey;

    private final ElGamalPrivateKey privateKey;

    public ElGamalKeyGeneratorTests(BigInteger p) {
        this.p = p;

        ElGamalKeyGenerator keyGenerator = new ElGamalKeyGenerator(p);
        keyGenerator.generateKeys();
        publicKey = keyGenerator.getPublicKey();
        privateKey = keyGenerator.getPrivateKey();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]
                {
                        { BigInteger.valueOf(61) }
                };
        return Arrays.asList(data);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfPIsNull() {
        new ElGamalKeyGenerator(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfPIsOutOfBounds() {
        new ElGamalKeyGenerator(BigInteger.valueOf(P_MIN - 1));
    }

    @Test
    public void testPublicKeyIsNotNull() {
        assertThat(publicKey, is(notNullValue()));
    }

    @Test
    public void testPublicKeyPIsNotNull() {
        assertThat(publicKey.getP(), is(notNullValue()));
    }

    @Test
    public void testPublicKeyPIsValid() {
        assertThat(publicKey.getP(), is(equalTo(p)));
    }

    @Test
    public void testPublicKeyGIsNotNull() {
        assertThat(publicKey.getG(), is(notNullValue()));
    }

    @Test
    public void testPublicKeyYIsNotNull() {
        assertThat(publicKey.getY(), is(notNullValue()));
    }

    @Test
    public void testPrivateKeyIsNotNull() {
        assertThat(privateKey, is(notNullValue()));
    }

    @Test
    public void testPrivateKeyXIsNotNull() {
        assertThat(privateKey.getX(), is(notNullValue()));
    }
}
