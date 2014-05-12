package com.asmierciak.cryptography.signatureschemes.signing;

import java.math.BigInteger;

public interface SignatureSigner {
    void generateSignature(BigInteger m);
}
