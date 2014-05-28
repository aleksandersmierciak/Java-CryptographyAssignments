package com.asmierciak.cryptography.hashfunctions.sha;

public interface ShaMessage {
    void calculateHash();
    byte[] getHashBytes();
}
