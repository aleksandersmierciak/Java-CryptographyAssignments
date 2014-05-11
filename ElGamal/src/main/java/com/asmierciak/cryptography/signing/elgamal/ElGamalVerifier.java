package com.asmierciak.cryptography.signing.elgamal;

import com.asmierciak.cryptography.keys.elgamal.ElGamalPublicKey;

import java.math.BigInteger;

public class ElGamalVerifier {
    private ElGamalPublicKey publicKey;

    public ElGamalVerifier(ElGamalPublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public boolean verify(ElGamalSignature signature, BigInteger input) {
        if (signature == null) {
            throw new IllegalArgumentException("Signature cannot be null");
        }
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        return checkPreconditions(signature.getR(), signature.getS()) &&
                validateSignature(signature, input);
    }

    private boolean checkPreconditions(BigInteger r, BigInteger s) {
        BigInteger p = publicKey.getP();
        return (r.compareTo(BigInteger.ZERO) > 0 && r.compareTo(p) < 0 &&
                s.compareTo(BigInteger.ZERO) > 0 && r.compareTo(p.subtract(BigInteger.ONE)) < 0);
    }

    private boolean validateSignature(ElGamalSignature signature, BigInteger m) {
        BigInteger p = publicKey.getP();
        BigInteger g = publicKey.getG();
        BigInteger y = publicKey.getY();
        BigInteger r = signature.getR();
        BigInteger s = signature.getS();

        BigInteger leftSide = g.modPow(m, p);
        BigInteger rightSide = (y.modPow(r, p)).multiply(r.modPow(s, p));

        return leftSide.compareTo(rightSide) == 0;
    }
}
