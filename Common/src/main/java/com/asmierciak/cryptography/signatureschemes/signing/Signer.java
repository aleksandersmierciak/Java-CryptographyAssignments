package com.asmierciak.cryptography.signatureschemes.signing;

import java.math.BigInteger;

public interface Signer {
    void generateSignature(BigInteger m);
}
