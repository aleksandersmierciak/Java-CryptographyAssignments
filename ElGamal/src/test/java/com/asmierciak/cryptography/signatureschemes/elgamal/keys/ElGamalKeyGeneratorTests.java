package com.asmierciak.cryptography.signatureschemes.elgamal.keys;

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
                        // Infeasible for signer and verifier tests, but left as is
                        { BigInteger.valueOf(5) },
                        { BigInteger.valueOf(7) },
                        { BigInteger.valueOf(11) },
                        { BigInteger.valueOf(13) },

                        { BigInteger.valueOf(4663) },
                        { BigInteger.valueOf(6947) },

                        // RSA-768
                        { new BigInteger("1230186684530117755130494958384962720772853569595334792197322452151726400507263657518745202199786469389956474942774063845925192557326303453731548268507917026122142913461670429214311602221240479274737794080665351419597459856902143413") },

                        // RSA-2048
                        { new BigInteger("25195908475657893494027183240048398571429282126204032027777137836043662020707595556264018525880784406918290641249515082189298559149176184502808489120072844992687392807287776735971418347270261896375014971824691165077613379859095700097330459748808428401797429100642458691817195118746121515172654632282216869987549182422433637259085141865462043576798423387184774447920739934236584823824281198163815010674810451660377306056201619676256133844143603833904414952634432190114657544454178424020924616515723350778707749817125772467962926386356373289912154831438167899885040445364023527381951378636564391212010397122822120720357") }
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
