package com.asmierciak.cryptography.hashing;

import com.asmierciak.util.bytes.ArrayConversions;
import com.asmierciak.util.bytes.MessageSplitter;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private final byte[] data;

    private int length;

    private final List<MessageChunk> chunks = new ArrayList<>();

    private byte[] hash;

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

    public byte[] getHash() {
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
        // According to SHA-1 paper:
        // h0 = 0x67452301
        // h1 = 0xEFCDAB89
        // h2 = 0x98BADCFE
        // h3 = 0x10325476
        // h4 = 0xC3D2E1F0
        hash = ArrayConversions.binaryStringToByteArray(
                "01100111010001010010001100000001" +
                "11101111110011011010101110001001" +
                "10011000101110101101110011111110" +
                "00010000001100100101010001110110" +
                "11000011110100101110000111110000");
    }

    private void addHashFromChunk(MessageChunk chunk) {
        byte[] chunkHash = chunk.getHash();
        for (int i = 0; i < hash.length; ++i) {
            hash[i] += chunkHash[i];
        }
    }
}
