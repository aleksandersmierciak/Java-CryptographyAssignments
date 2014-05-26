package com.asmierciak.cryptography.signatureschemes.elgamal.signing;

import com.asmierciak.cryptography.signatureschemes.elgamal.keys.ElGamalPublicKey;
import com.asmierciak.cryptography.signatureschemes.signing.SignatureVerifier;

import java.math.BigInteger;

public class ElGamalVerifier implements SignatureVerifier {
    private final ElGamalPublicKey publicKey;

    private ElGamalSignature signature;

    public ElGamalVerifier(ElGamalPublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public void setSignature(ElGamalSignature signature) {
        this.signature = signature;
    }

    @Override
    public boolean verify(BigInteger input) {
        if (signature == null) {
            throw new IllegalArgumentException("Signature cannot be null; set it first");
        }
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        return checkPreconditions(signature.getR(), signature.getS()) &&
                validateSignature(signature, input);
    }

    public boolean verify(ElGamalSignature signature, BigInteger input) {
        this.signature = signature;
        return verify(input);
    }

    private boolean checkPreconditions(BigInteger r, BigInteger s) {
        BigInteger p = publicKey.getP();
        return (r.compareTo(BigInteger.ZERO) > 0 && r.compareTo(p) < 0 &&
                s.compareTo(BigInteger.ZERO) > 0 && s.compareTo(p.subtract(BigInteger.ONE)) < 0);
    }

    private boolean validateSignature(ElGamalSignature signature, BigInteger m) {
        BigInteger p = publicKey.getP();
        BigInteger g = publicKey.getG();
        BigInteger y = publicKey.getY();
        BigInteger r = signature.getR();
        BigInteger s = signature.getS();

        BigInteger leftSide = g.modPow(m, p);
        BigInteger rightSide = (y.modPow(r, p)).multiply(r.modPow(s, p)).mod(p);

        return leftSide.compareTo(rightSide) == 0;
    }
}
