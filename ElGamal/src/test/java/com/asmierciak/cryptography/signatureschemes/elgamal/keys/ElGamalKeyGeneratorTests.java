package com.asmierciak.cryptography.signatureschemes.elgamal.keys;

import com.asmierciak.cryptography.signatureschemes.elgamal.keys.ElGamalKeyGenerator;
import com.asmierciak.cryptography.signatureschemes.elgamal.keys.ElGamalPrivateKey;
import com.asmierciak.cryptography.signatureschemes.elgamal.keys.ElGamalPublicKey;
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
                        { BigInteger.valueOf(5) },
                        { BigInteger.valueOf(7) },
                        { BigInteger.valueOf(11) },
                        { BigInteger.valueOf(13) },

                        { BigInteger.valueOf(4663) },
                        { BigInteger.valueOf(6947) },

                        // RSA-768
                        { new BigInteger("1230186684530117755130494958384962720772853569595334792197322452151726400507263657518745202199786469389956474942774063845925192557326303453731548268507917026122142913461670429214311602221240479274737794080665351419597459856902143413") }
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
    public void testPublicKeyPIsSameAsInput() {
        assertThat(publicKey.getP(), is(equalTo(p)));
    }

    @Test
    public void testPublicKeyGIsNotNull() {
        assertThat(publicKey.getG(), is(notNullValue()));
    }

    @Test
    public void testPublicKeyGIsLessThanPublicKeyP() {
        assertThat(publicKey.getG(), lessThan(p));
    }

    @Test
    public void testPublicKeyGIsNonNegativeInteger() {
        assertThat(publicKey.getG(), greaterThanOrEqualTo(BigInteger.ZERO));
    }

    @Test
    public void testPublicKeyYIsNotNull() {
        assertThat(publicKey.getY(), is(notNullValue()));
    }

    @Test
    public void testPublicKeyYIsNonNegativeInteger() {
        assertThat(publicKey.getY(), greaterThanOrEqualTo(BigInteger.ZERO));
    }

    @Test
    public void testPublicKeyYIsLessThanPublicKeyP() {
        assertThat(publicKey.getY(), lessThan(p));
    }

    @Test
    public void testPrivateKeyIsNotNull() {
        assertThat(privateKey, is(notNullValue()));
    }

    @Test
    public void testPrivateKeyXIsNotNull() {
        assertThat(privateKey.getX(), is(notNullValue()));
    }

    @Test
    public void testPrivateKeyXIsGreaterThanOne() {
        assertThat(privateKey.getX(), greaterThan(BigInteger.ONE));
    }

    @Test
    public void testPrivateKeyXIsLessThanPMinusOne() {
        assertThat(privateKey.getX(), lessThan(p.subtract(BigInteger.ONE)));
    }
}
