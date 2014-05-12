package com.asmierciak.cryptography.signatureschemes.elgamal;

import com.asmierciak.cryptography.signatureschemes.SignatureScheme;
import com.asmierciak.cryptography.signatureschemes.elgamal.keys.ElGamalKeyGenerator;
import com.asmierciak.cryptography.signatureschemes.elgamal.signing.ElGamalSignature;
import com.asmierciak.cryptography.signatureschemes.elgamal.signing.ElGamalSigner;
import com.asmierciak.cryptography.signatureschemes.elgamal.signing.ElGamalVerifier;
import com.asmierciak.cryptography.util.TextEncoder;

import java.math.BigInteger;

public class ElGamalScheme implements SignatureScheme {
    private final TextEncoder textEncoder;

    private final ElGamalSigner signer;

    private final ElGamalVerifier verifier;

    private ElGamalSignature signature;

    public ElGamalScheme(TextEncoder textEncoder, BigInteger seed) {
        if (textEncoder == null) {
            throw new IllegalArgumentException("Text encoder cannot be null");
        }
        if (seed == null) {
            throw new IllegalArgumentException("Seed cannot be null");
        }

        this.textEncoder = textEncoder;

        ElGamalKeyGenerator generator = new ElGamalKeyGenerator(seed);
        generator.generateKeys();

        signer = new ElGamalSigner(generator.getPublicKey(), generator.getPrivateKey());
        verifier = new ElGamalVerifier(generator.getPublicKey());
    }

    @Override
    public ElGamalSignature getSignature() {
        return signature;
    }

    public void setSignature(ElGamalSignature signature) {
        this.signature = signature;
    }

    @Override
    public void sign(String hash) {
        if (hash == null) {
            throw new IllegalArgumentException("Hash cannot be null");
        }

        BigInteger encodedHash = textEncoder.encode(hash);
        signer.generateSignature(encodedHash);
        signature = signer.getSignature();
    }

    @Override
    public boolean verify(String hash) {
        if (signature == null) {
            throw new IllegalArgumentException("Signature cannot be null");
        }
        if (hash == null) {
            throw new IllegalArgumentException("Hash cannot be null");
        }

        BigInteger encodedHash = textEncoder.encode(hash);
        return verifier.verify(signature, encodedHash);
    }
}
