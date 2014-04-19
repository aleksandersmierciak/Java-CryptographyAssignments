package com.asmierciak.cryptography.hashing;

import com.asmierciak.util.bytes.MessageSplitter;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private final byte[] data;

    private int length;

    private final List<MessageChunk> chunks = new ArrayList<>();

    private int[] hash;

    public Message(byte[] data) {
        if (data.length == 0)
        {
            throw new IllegalArgumentException("Input cannot be an empty array");
        }
        this.data = data;
        this.length = data.length;
        splitDataIntoChunks();
    }

    private void splitDataIntoChunks() {
        MessageSplitter splitter = new MessageSplitter();
        byte[][] chunkData = splitter.split(data, MessageChunk.CHUNK_VALID_SIZE_IN_BYTES);
        for (byte[] aChunkData : chunkData) {
            chunks.add(new MessageChunk(aChunkData));
        }
    }

    public int getLength() {
        return length;
    }

    public List<MessageChunk> getChunks() {
        return chunks;
    }

    public int[] getHash() {
        return hash;
    }

    public void calculateHash() {
        // TODO: implement creating SHA-1 hash
        initializeHash();
        for (MessageChunk chunk : chunks) {
            chunk.calculateHash(hash);
            addHashFromChunk(chunk);
        }
    }

    private void initializeHash() {
        // Values according to SHA-1 paper
        hash = new int[5];
        // Java integer is signed, so we have to emulate unsignedness
        hash[0] = (int)0x67452301L;
        hash[1] = (int)0xEFCDAB89L;
        hash[2] = (int)0x98BADCFEL;
        hash[3] = (int)0x10325476L;
        hash[4] = (int)0xC3D2E1F0L;
    }

    private void addHashFromChunk(MessageChunk chunk) {
        int[] chunkHash = chunk.getHash();
        for (int i = 0; i < hash.length; ++i) {
            hash[i] += chunkHash[i];
        }
    }
}
