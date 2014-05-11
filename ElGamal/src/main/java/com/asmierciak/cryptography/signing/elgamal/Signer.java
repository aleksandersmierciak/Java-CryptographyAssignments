package com.asmierciak.cryptography.signing.elgamal;

import java.math.BigInteger;

public interface Signer {
    void generateSignature(BigInteger m);
}
