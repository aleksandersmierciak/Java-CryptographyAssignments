package com.asmierciak.cryptography.signatureschemes.signing;

import java.math.BigInteger;

public interface SignatureVerifier {
    boolean verify(BigInteger input);
}
