package com.asmierciak.cryptography.hashfunctions.sha.sha1;

import com.asmierciak.cryptography.hashfunctions.sha.MessageFiller;
import com.asmierciak.cryptography.hashfunctions.sha.ShaMessage;
import com.asmierciak.cryptography.hashfunctions.sha.ShaMessageChunk;
import com.asmierciak.util.bytes.ArraySplitter;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sha1Message implements ShaMessage {
    private final byte[] data;

    private final List<ShaMessageChunk> chunks = new ArrayList<>();

    private int[] hash;

    public Sha1Message(byte[] data) {
        if (data.length == 0)
        {
            throw new IllegalArgumentException("Input cannot be an empty array");
        }

        this.data = MessageFiller.fillMessage(data);
        splitDataIntoChunks();
    }

    private void splitDataIntoChunks() {
        byte[][] chunkData = ArraySplitter.split(data, Sha1MessageChunk.CHUNK_VALID_SIZE_IN_BYTES);
        for (byte[] aChunkData : chunkData) {
            chunks.add(new Sha1MessageChunk(aChunkData));
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
        byte[] hashBytes = new byte[hash.length * 4];
        for (int i = 0; i < hash.length; ++i) {
            byte[] singleInteger = ByteBuffer.allocate(4).putInt(hash[i]).array();
            System.arraycopy(singleInteger, 0, hashBytes, i * 4, 4);
        }
        return hashBytes;
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
        hash = Arrays.copyOf(Sha1MessageChunk.INITIAL_HASH, Sha1MessageChunk.INITIAL_HASH.length);
    }

    private void addHashFromChunk(ShaMessageChunk chunk) {
        int[] chunkHash = chunk.getHash();
        for (int i = 0; i < hash.length; ++i) {
            hash[i] += chunkHash[i];
        }
    }
}
