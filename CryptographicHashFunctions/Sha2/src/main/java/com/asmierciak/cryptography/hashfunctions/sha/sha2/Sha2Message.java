package com.asmierciak.cryptography.hashfunctions.sha.sha2;

import com.asmierciak.cryptography.hashfunctions.sha.ShaMessage;
import com.asmierciak.cryptography.hashfunctions.sha.ShaMessageChunk;

import java.util.List;

public class Sha2Message implements ShaMessage {
    private int length;
    private List<ShaMessageChunk> chunks;
    private int[] hash;

    public Sha2Message(byte[] data) {
        if (data.length == 0)
        {
            throw new IllegalArgumentException("Input cannot be an empty array");
        }

    }

    @Override
    public void calculateHash() {

    }

    @Override
    public byte[] getHashBytes() {
        return new byte[0];
    }

    public int getLength() {
        return length;
    }

    public List<ShaMessageChunk> getChunks() {
        return chunks;
    }

    public int[] getHash() {
        return hash;
    }
}
