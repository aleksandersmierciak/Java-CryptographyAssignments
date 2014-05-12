package com.asmierciak.cryptography.signatureschemes.elgamal;

import com.asmierciak.cryptography.signatureschemes.elgamal.signing.ElGamalSignature;
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

    public ElGamalSchemeTests(String message, TextEncoder textEncoder, BigInteger seed) {
        this.message = message;

        elGamal = new ElGamalScheme(textEncoder, seed);
        signature = elGamal.sign(message);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        BigInteger p1 = BigInteger.valueOf(4663);
        // RSA-768
        BigInteger p2 = new BigInteger("1230186684530117755130494958384962720772853569595334792197322452151726400507263657518745202199786469389956474942774063845925192557326303453731548268507917026122142913461670429214311602221240479274737794080665351419597459856902143413");
        // RSA-2048
        BigInteger p3 = new BigInteger("25195908475657893494027183240048398571429282126204032027777137836043662020707595556264018525880784406918290641249515082189298559149176184502808489120072844992687392807287776735971418347270261896375014971824691165077613379859095700097330459748808428401797429100642458691817195118746121515172654632282216869987549182422433637259085141865462043576798423387184774447920739934236584823824281198163815010674810451660377306056201619676256133844143603833904414952634432190114657544454178424020924616515723350778707749817125772467962926386356373289912154831438167899885040445364023527381951378636564391212010397122822120720357");

        // "Ala ma kota";
        String message1 = "43fd70009a97a7d311c5644047ccc700f8d08a9d";
        // "The quick brown fox jumps over the lazy dog";
        String message2 = "2fd4e1c67a2d28fced849ee1bb76e7391b93eb12";
        // "Zażółć gęślą jaźń";
        String message3 = "839b3ecd39c5ac4118a24e3ebee3401b0f62d1fc";

        // Test case data found below is an arbitrarily chosen seed (p)
        // and a message decoded using AsciiDecoder.
        Object[][] data = new Object[][]
                {
                        { message1, new AsciiEncoder(), p1 },
                        { message1, new AsciiEncoder(), p2 },
                        { message1, new AsciiEncoder(), p3 },

                        { message2, new AsciiEncoder(), p1 },
                        { message2, new AsciiEncoder(), p2 },
                        { message2, new AsciiEncoder(), p3 },

                        { message3, new AsciiEncoder(), p1 },
                        { message3, new AsciiEncoder(), p2 },
                        { message3, new AsciiEncoder(), p3 },
                };
        return Arrays.asList(data);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfInputIsNull() {
        elGamal.sign(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfEncoderIsNull() {
        new ElGamalScheme(null, BigInteger.valueOf(7));
    }

    @Test
    public void testSignatureIsValid() {
        assertThat(elGamal.verify(signature, message), is(true));
    }
}
