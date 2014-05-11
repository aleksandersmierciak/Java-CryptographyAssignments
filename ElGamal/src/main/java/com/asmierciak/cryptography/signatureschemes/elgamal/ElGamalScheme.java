package com.asmierciak.cryptography.signatureschemes.elgamal;

import com.asmierciak.cryptography.hashing.HashFunction;
import com.asmierciak.cryptography.keys.elgamal.ElGamalKeyGenerator;
import com.asmierciak.cryptography.signing.elgamal.ElGamalSignature;
import com.asmierciak.cryptography.signing.elgamal.ElGamalSigner;
import com.asmierciak.cryptography.signing.elgamal.ElGamalVerifier;
import com.asmierciak.cryptography.util.TextEncoder;

import java.math.BigInteger;

public class ElGamalScheme {
    private final HashFunction hashFunction;

    private final TextEncoder textEncoder;

    private final ElGamalSigner signer;

    private final ElGamalVerifier verifier;

    public ElGamalScheme(HashFunction hashFunction, TextEncoder textEncoder, BigInteger seed) {
        if (hashFunction == null) {
            throw new IllegalArgumentException("Hash function cannot be null");
        }
        if (textEncoder == null) {
            throw new IllegalArgumentException("Text encoder cannot be null");
        }
        if (seed == null) {
            throw new IllegalArgumentException("Seed cannot be null");
        }

        this.hashFunction = hashFunction;
        this.textEncoder = textEncoder;

        ElGamalKeyGenerator generator = new ElGamalKeyGenerator(seed);
        generator.generateKeys();

        signer = new ElGamalSigner(generator.getPublicKey(), generator.getPrivateKey());
        verifier = new ElGamalVerifier(generator.getPublicKey());
    }

    public ElGamalSignature sign(String message) {
        if (message == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        BigInteger encodedHash = textEncoder.encode(hashFunction.hash(message));
        signer.generateSignature(encodedHash);
        return signer.getSignature();
    }

    public boolean verify(ElGamalSignature signature, String message) {
        if (signature == null) {
            throw new IllegalArgumentException("Signature cannot be null");
        }
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }

        BigInteger encodedHash = textEncoder.encode(hashFunction.hash(message));
        return verifier.verify(signature, encodedHash);
    }
}
