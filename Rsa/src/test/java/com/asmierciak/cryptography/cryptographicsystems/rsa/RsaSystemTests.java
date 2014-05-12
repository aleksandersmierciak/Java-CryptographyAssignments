package com.asmierciak.cryptography.cryptographicsystems.rsa;

import com.asmierciak.cryptography.cryptographicsystems.rsa.ciphers.RsaDecryptor;
import com.asmierciak.cryptography.cryptographicsystems.rsa.ciphers.RsaEncryptor;
import com.asmierciak.cryptography.cryptographicsystems.rsa.keys.RsaKeyGenerator;
import com.asmierciak.cryptography.cryptographicsystems.rsa.keys.RsaPrivateKey;
import com.asmierciak.cryptography.cryptographicsystems.rsa.keys.RsaPublicKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class RsaSystemTests {
    private final String input;

    private final RsaSystem cryptosystem;

    private final BigInteger[] ciphertext;

    private final String actualOutput;

    public RsaSystemTests(String input) {
        this.input = input;

        RsaKeyGenerator keyGenerator = new RsaKeyGenerator(
                BigInteger.probablePrime(128, new Random()),
                BigInteger.probablePrime(128, new Random()));
        keyGenerator.generateKeys();

        RsaPublicKey publicKey = keyGenerator.getPublicKey();
        RsaPrivateKey privateKey = keyGenerator.getPrivateKey();
        RsaEncryptor encryptor = new RsaEncryptor(publicKey);
        RsaDecryptor decryptor = new RsaDecryptor(privateKey);

        cryptosystem = new RsaSystem(encryptor, decryptor);

        ciphertext = cryptosystem.encrypt(input);
        actualOutput = cryptosystem.decrypt(ciphertext);
    }

    @Parameterized.Parameters
    public static Collection<String[]> data() {
        String[][] data = new String[][]
                {
                        {"A quick brown fox jumped over the lazy dog."},
                        {"What is love? Baby don't hurt me, don't hurt me, no more"},
                        {"Come with me if you want to live!"},
                        {"Madness...? This... is... SPARTA!!!"}
                };
        return Arrays.asList(data);
    }

    @Test
    public void testEncryptorIsNotNull() throws Exception {
        assertThat(cryptosystem.getEncryptor(), is(notNullValue()));
    }

    @Test
    public void testDecryptorIsNotNull() throws Exception {
        assertThat(cryptosystem.getDecryptor(), is(notNullValue()));
    }

    @Test
    public void testCiphertextIsNotNull() throws Exception {
        assertThat(ciphertext, is(notNullValue()));
    }

    @Test
    public void testActualOutputIsNotNull() throws Exception {
        assertThat(actualOutput, is(notNullValue()));
    }

    @Test
    public void testActualOutputIsValid() throws Exception {
        assertThat(actualOutput, is(input));
    }
}
