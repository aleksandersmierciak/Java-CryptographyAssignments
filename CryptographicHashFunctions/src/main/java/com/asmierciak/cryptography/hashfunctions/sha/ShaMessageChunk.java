package com.asmierciak.cryptography.hashfunctions.sha;

public interface ShaMessageChunk {
    void calculateHash(int[] previousChunkHash);
    int[] getHash();
}
