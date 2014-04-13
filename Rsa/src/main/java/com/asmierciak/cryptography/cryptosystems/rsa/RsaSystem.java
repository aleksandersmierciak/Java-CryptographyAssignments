package com.asmierciak.cryptography.cryptosystems.rsa;

import com.asmierciak.cryptography.ciphers.Decryptor;
import com.asmierciak.cryptography.ciphers.Encryptor;
import com.asmierciak.cryptography.ciphers.rsa.RsaDecryptor;
import com.asmierciak.cryptography.ciphers.rsa.RsaEncryptor;
import com.asmierciak.cryptography.cryptosystems.Cryptosystem;
import com.asmierciak.cryptography.util.TextDecoder;
import com.asmierciak.cryptography.util.TextEncoder;
import com.asmierciak.cryptography.util.ascii.AsciiDecoder;
import com.asmierciak.cryptography.util.ascii.AsciiEncoder;
import com.asmierciak.util.text.TextMerger;
import com.asmierciak.util.text.TextSplitter;

import java.math.BigInteger;

public class RsaSystem implements Cryptosystem {
    private final Encryptor encryptor;
    private final Decryptor decryptor;

    private final TextEncoder textEncoder;
    private final TextDecoder textDecoder;

    private static final int chunkSize = 10;

    public RsaSystem(RsaEncryptor encryptor, RsaDecryptor decryptor) {
        this.encryptor = encryptor;
        this.decryptor = decryptor;

        textEncoder = new AsciiEncoder();
        textDecoder = new AsciiDecoder();
    }

    public Encryptor getEncryptor() {
        return encryptor;
    }

    public Decryptor getDecryptor() {
        return decryptor;
    }

    @Override
    public BigInteger[] encrypt(String plaintext) {
        String[] inputChunks = splitIntoChunks(plaintext);
        BigInteger[] encodedChunks = encodeChunks(inputChunks);
        return encryptChunks(encodedChunks);
    }

    @Override
    public String decrypt(BigInteger[] ciphertext) {
        BigInteger[] decryptedEncodedChunks = decryptChunks(ciphertext);
        String[] decodedChunks = decodeChunks(decryptedEncodedChunks);
        return mergeChunks(decodedChunks);
    }

    private String[] splitIntoChunks(String input) {
        TextSplitter splitter = new TextSplitter();
        return splitter.split(input, chunkSize);
    }

    private BigInteger[] encodeChunks(String[] inputChunks) {
        BigInteger[] encodedChunks = new BigInteger[inputChunks.length];
        for (int i = 0; i < inputChunks.length; ++i) {
            encodedChunks[i] = textEncoder.encode(inputChunks[i]);
        }
        return encodedChunks;
    }

    private BigInteger[] encryptChunks(BigInteger[] chunks) {
        BigInteger[] encryptedChunks = new BigInteger[chunks.length];
        for (int i = 0; i < chunks.length; ++i) {
            encryptedChunks[i] = encryptor.encrypt(chunks[i]);
        }
        return encryptedChunks;
    }

    private BigInteger[] decryptChunks(BigInteger[] chunks) {
        BigInteger[] decryptedChunks = new BigInteger[chunks.length];
        for (int i = 0; i < chunks.length; ++i) {
            decryptedChunks[i] = decryptor.decrypt(chunks[i]);
        }
        return decryptedChunks;
    }

    private String[] decodeChunks(BigInteger[] chunks) {
        String[] decodedChunks = new String[chunks.length];
        for (int i = 0; i < chunks.length; ++i) {
            decodedChunks[i] = textDecoder.decode(chunks[i]);
        }
        return decodedChunks;
    }

    private String mergeChunks(String[] chunks) {
        TextMerger merger = new TextMerger();
        return merger.merge(chunks);
    }
}
