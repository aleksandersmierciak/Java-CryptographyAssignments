package com.asmierciak.cryptography.hashfunctions.sha.sha2;

import com.asmierciak.cryptography.hashfunctions.sha.MessageFiller;
import com.asmierciak.cryptography.hashfunctions.sha.ShaMessage;
import com.asmierciak.cryptography.hashfunctions.sha.ShaMessageChunk;
import com.asmierciak.util.bytes.ArraySplitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sha2Message implements ShaMessage {
    private final byte[] data;

    private final List<ShaMessageChunk> chunks = new ArrayList<>();

    private int[] hash;

    public Sha2Message(byte[] data) {
        if (data.length == 0)
        {
            throw new IllegalArgumentException("Input cannot be an empty array");
        }

        this.data = MessageFiller.fillMessage(data);
        splitDataIntoChunks();
    }

    private void splitDataIntoChunks() {
        byte[][] chunkData = ArraySplitter.split(data, Sha2MessageChunk.CHUNK_VALID_SIZE_IN_BYTES);
        for (byte[] aChunkData : chunkData) {
            chunks.add(new Sha2MessageChunk(aChunkData));
        }
    }

    public int getLength() {
        return data.length;
    }

    public List<ShaMessageChunk> getChunks() {
        return chunks;
    }

    public int[] getHash() {
        return hash;
    }

    @Override
    public byte[] getHashBytes() {
        return new byte[0];
    }

    @Override
    public void calculateHash() {
        initializeHash();
        for (ShaMessageChunk chunk : chunks) {
            chunk.calculateHash(Arrays.copyOf(hash, hash.length));
            addHashFromChunk(chunk);
        }
    }

    private void initializeHash() {
        hash = Arrays.copyOf(Sha2MessageChunk.INITIAL_HASH, Sha2MessageChunk.INITIAL_HASH.length);
    }

    private void addHashFromChunk(ShaMessageChunk chunk) {
        int[] chunkHash = chunk.getHash();
        for (int i = 0; i < hash.length; ++i) {
            hash[i] += chunkHash[i];
        }
    }
}
