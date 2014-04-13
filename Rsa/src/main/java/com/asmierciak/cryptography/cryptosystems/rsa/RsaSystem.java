package com.asmierciak.cryptography.cryptosystems.rsa;

import com.asmierciak.cryptography.ciphers.rsa.RsaDecryptor;
import com.asmierciak.cryptography.ciphers.rsa.RsaEncryptor;
import com.asmierciak.cryptography.cryptosystems.Cryptosystem;
import com.asmierciak.cryptography.ciphers.Decryptor;
import com.asmierciak.cryptography.ciphers.Encryptor;
import com.asmierciak.cryptography.keys.KeyGenerator;
import com.asmierciak.cryptography.keys.rsa.RsaKeyGenerator;

public class RsaSystem implements Cryptosystem {
    private Encryptor encryptor;
    private Decryptor decryptor;
    private KeyGenerator keyGenerator;

    public RsaSystem(RsaEncryptor encryptor, RsaDecryptor decryptor, RsaKeyGenerator keyGenerator) {
        this.encryptor = encryptor;
        this.decryptor = decryptor;
        this.keyGenerator = keyGenerator;
    }

    @Override
    public String encrypt(String plaintext) {
        return null;
    }

    @Override
    public String decrypt(String ciphertext) {
        return null;
    }
}
