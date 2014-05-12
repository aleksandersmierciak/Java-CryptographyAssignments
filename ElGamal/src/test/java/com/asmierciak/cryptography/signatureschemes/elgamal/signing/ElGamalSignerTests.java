package com.asmierciak.cryptography.signatureschemes.elgamal.signing;

import com.asmierciak.cryptography.signatureschemes.elgamal.keys.ElGamalPrivateKey;
import com.asmierciak.cryptography.signatureschemes.elgamal.keys.ElGamalPublicKey;
import com.asmierciak.cryptography.signatureschemes.elgamal.signing.ElGamalSignature;
import com.asmierciak.cryptography.signatureschemes.elgamal.signing.ElGamalSigner;
import com.asmierciak.cryptography.signatureschemes.signing.Signer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(Parameterized.class)
public class ElGamalSignerTests {
    private final ElGamalSignature signature;

    public ElGamalSignerTests(ElGamalPublicKey publicKey, ElGamalPrivateKey privateKey, BigInteger input) {
        ElGamalSigner signer = new ElGamalSigner(publicKey, privateKey);
        signer.generateSignature(input);
        signature = signer.getSignature();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        ElGamalPublicKey tinyPublicKey = new ElGamalPublicKey(BigInteger.valueOf(7), BigInteger.valueOf(4), BigInteger.valueOf(2));
        ElGamalPublicKey smallPublicKey = new ElGamalPublicKey(BigInteger.valueOf(4663), BigInteger.valueOf(3605), BigInteger.valueOf(3831));
//        ElGamalPublicKey largePublicKey = new ElGamalPublicKey(
//                new BigInteger("1230186684530117755130494958384962720772853569595334792197322452151726400507263657518745202199786469389956474942774063845925192557326303453731548268507917026122142913461670429214311602221240479274737794080665351419597459856902143413"),
//                new BigInteger("178530505861634674756617917210668419492586289623607229616955011560049646148685373630841737744868677832680706866777032422627596011085224463063903982759011429881144684069684685659195817399528287283642149768373178525608379048644428023"),
//                new BigInteger("297862634128839561820402180654517022749165787047341397566874391927082725619027197086037627933750271813515457359699153060665365820233656809052566400659870364763803093273626657949480173367041904711393216626086558798205621377354236130"));

        ElGamalPrivateKey tinyPrivateKey = new ElGamalPrivateKey(BigInteger.valueOf(5));
        ElGamalPrivateKey smallPrivateKey = new ElGamalPrivateKey(BigInteger.valueOf(3483));
//        ElGamalPrivateKey largePrivateKey = new ElGamalPrivateKey(
//                new BigInteger("661944845495209522864995554116801403715510468598103022692870372736622573217856168486701594757698400214036478256431126205338733056845251025013547291385426218188456399324643579064789503397068595143994947371823059148043848605922987780"));

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
                        { tinyPublicKey, tinyPrivateKey, message1 },
                        { tinyPublicKey, tinyPrivateKey, message2 },
                        { tinyPublicKey, tinyPrivateKey, message3 },

                        { smallPublicKey, smallPrivateKey, message1 },
                        { smallPublicKey, smallPrivateKey, message2 },
                        { smallPublicKey, smallPrivateKey, message3 },

//                        { largePublicKey, largePrivateKey, message1 },
//                        { largePublicKey, largePrivateKey, message2 },
//                        { largePublicKey, largePrivateKey, message3 },
                };
        return Arrays.asList(data);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfPublicKeyIsNull() {
        new ElGamalSigner(
                null,
                new ElGamalPrivateKey(BigInteger.ZERO));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfPrivateKeyIsNull() {
        new ElGamalSigner(
                new ElGamalPublicKey(BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO),
                null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfInputIsNull() {
        Signer signer = new ElGamalSigner(
                new ElGamalPublicKey(BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO),
                new ElGamalPrivateKey(BigInteger.ZERO));
        signer.generateSignature(null);
    }

    @Test
    public void testSignatureIsNotNull() {
        assertThat(signature, is(notNullValue()));
    }
}
