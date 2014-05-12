package com.asmierciak.cryptography.signatureschemes;

import com.asmierciak.cryptography.signatureschemes.signing.Signature;

public interface SignatureScheme {
    void sign(String hash);
    boolean verify(String hash);

    Signature getSignature();
}
