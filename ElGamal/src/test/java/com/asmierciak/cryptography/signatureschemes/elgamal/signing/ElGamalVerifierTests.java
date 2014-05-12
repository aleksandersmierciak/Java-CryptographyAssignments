package com.asmierciak.cryptography.signatureschemes.elgamal.signing;

import com.asmierciak.cryptography.signatureschemes.elgamal.keys.ElGamalPublicKey;
import com.asmierciak.cryptography.signatureschemes.elgamal.signing.ElGamalSignature;
import com.asmierciak.cryptography.signatureschemes.elgamal.signing.ElGamalVerifier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(Parameterized.class)
public class ElGamalVerifierTests {
    private BigInteger input;

    private final ElGamalSignature signature;

    private final ElGamalVerifier verifier;

    public ElGamalVerifierTests(ElGamalPublicKey publicKey, ElGamalSignature signature, BigInteger input) {
        this.signature = signature;
        this.input = input;

        verifier = new ElGamalVerifier(publicKey);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        ElGamalPublicKey tinyPublicKey = new ElGamalPublicKey(BigInteger.valueOf(7), BigInteger.valueOf(4), BigInteger.valueOf(2));
        ElGamalPublicKey smallPublicKey = new ElGamalPublicKey(BigInteger.valueOf(4663), BigInteger.valueOf(3605), BigInteger.valueOf(3831));
//        ElGamalPublicKey largePublicKey = new ElGamalPublicKey(
//                new BigInteger("1230186684530117755130494958384962720772853569595334792197322452151726400507263657518745202199786469389956474942774063845925192557326303453731548268507917026122142913461670429214311602221240479274737794080665351419597459856902143413"),
//                new BigInteger("178530505861634674756617917210668419492586289623607229616955011560049646148685373630841737744868677832680706866777032422627596011085224463063903982759011429881144684069684685659195817399528287283642149768373178525608379048644428023"),
//                new BigInteger("297862634128839561820402180654517022749165787047341397566874391927082725619027197086037627933750271813515457359699153060665365820233656809052566400659870364763803093273626657949480173367041904711393216626086558798205621377354236130"));

        // "Ala ma kota"
        BigInteger message1 = new BigInteger("79091985525906541409956961");
        // "The quick brown fox jumps over the lazy dog"
        BigInteger message2 = new BigInteger("11815744420664747200359014215611078249874077418792906203758916158211866334739307190174417697959789752167");
        // "Zażółć gęślą jaźń"
        BigInteger message3 = new BigInteger("30756326165500277537644814179649104018244");

        // Test case data found below are
        // valid ElGamal public and private keys
        // and a message decoded using AsciiDecoder.
        Object[][] data = new Object[][]
                {
                        { tinyPublicKey, new ElGamalSignature(BigInteger.valueOf(2), new BigInteger("395459927629532707049784755")), message1 },
                        { tinyPublicKey, new ElGamalSignature(BigInteger.valueOf(2), new BigInteger("59078722103323736001795071078055391249370387093964531018794580791059331673696535950872088489798948760785")), message2 },
                        { tinyPublicKey, new ElGamalSignature(BigInteger.valueOf(2), new BigInteger("153781630827501387688224070898245520091170")), message3 },

                        { smallPublicKey, new ElGamalSignature(BigInteger.valueOf(69), new BigInteger("326570808236468109480719981786")), message1 },
                        { smallPublicKey, new ElGamalSignature(BigInteger.valueOf(919), new BigInteger("18869743839801601278973345702330891965048901637812271207402989104664350536578673582708545063641779122410130")), message2 },
                        { smallPublicKey, new ElGamalSignature(BigInteger.valueOf(1738), new BigInteger("33247588584905800018194044128200674899937990")), message3 },

//                        { largePublicKey, new ElGamalSignature(BigInteger.ONE, BigInteger.ONE), message1 },
//                        { largePublicKey, new ElGamalSignature(BigInteger.ONE, BigInteger.ONE), message2 },
//                        { largePublicKey, new ElGamalSignature(BigInteger.ONE, BigInteger.ONE), message3 },
                };
        return Arrays.asList(data);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfSignatureIsNull() {
        verifier.verify(null, input);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfInputIsNull() {
        verifier.verify(new ElGamalSignature(BigInteger.ONE, BigInteger.ONE), null);
    }

    @Test
    public void testInputMessageIsVerified() {
        assertThat(verifier.verify(signature, input), is(true));
    }
}
