package com.asmierciak.cryptography.signatureschemes.elgamal;

import com.asmierciak.cryptography.hashing.HashFunction;
import com.asmierciak.cryptography.hashing.sha.Sha1;
import com.asmierciak.cryptography.signing.elgamal.ElGamalSignature;
import com.asmierciak.cryptography.util.TextEncoder;
import com.asmierciak.cryptography.util.ascii.AsciiEncoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(Parameterized.class)
public class ElGamalSchemeTests {
    private final String message;

    private final ElGamalScheme elGamal;

    private final ElGamalSignature signature;

    public ElGamalSchemeTests(String message, HashFunction hashFunction, TextEncoder textEncoder, BigInteger seed) {
        this.message = message;

        elGamal = new ElGamalScheme(hashFunction, textEncoder, seed);
        signature = elGamal.sign(message);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        BigInteger p1 = BigInteger.valueOf(7);
        BigInteger p2 = BigInteger.valueOf(4663);
        // RSA-768
        BigInteger p3 = new BigInteger("1230186684530117755130494958384962720772853569595334792197322452151726400507263657518745202199786469389956474942774063845925192557326303453731548268507917026122142913461670429214311602221240479274737794080665351419597459856902143413");

        String message1 = "Ala ma kota";
        String message2 = "The quick brown fox jumps over the lazy dog";
        String message3 = "Zażółć gęślą jaźń";

        // Test case data found below is an arbitrarily chosen seed (p)
        // and a message decoded using AsciiDecoder.
        Object[][] data = new Object[][]
                {
                        { message1, new Sha1(), new AsciiEncoder(), p1 },
                        { message1, new Sha1(), new AsciiEncoder(), p2 },
                        { message1, new Sha1(), new AsciiEncoder(), p3 },

                        { message2, new Sha1(), new AsciiEncoder(), p1 },
                        { message2, new Sha1(), new AsciiEncoder(), p2 },
                        { message2, new Sha1(), new AsciiEncoder(), p3 },

                        { message3, new Sha1(), new AsciiEncoder(), p1 },
                        { message3, new Sha1(), new AsciiEncoder(), p2 },
                        { message3, new Sha1(), new AsciiEncoder(), p3 },
                };
        return Arrays.asList(data);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfInputIsNull() {
        elGamal.sign(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfHashFunctionIsNull() {
        new ElGamalScheme(null, new AsciiEncoder(), BigInteger.valueOf(7));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfEncoderIsNull() {
        new ElGamalScheme(new Sha1(), null, BigInteger.valueOf(7));
    }

    @Test
    public void testSignerIsNotNull() {

    }

    @Test
    public void testVerifierIsNotNull() {

    }

    @Test
    public void testSignatureIsValid() {
        assertThat(elGamal.verify(signature, message), is(true));
    }
}
