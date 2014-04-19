package com.asmierciak.cryptography.hashing;

import java.util.Arrays;

public class MessageChunk {
    public final static int CHUNK_VALID_SIZE_IN_BYTES = 64;

    private final static int WORD_SIZE_IN_BYTES = 4;

    private final static int WORD_EXTENSION_IN_BYTES = 64;

    private final byte[] data;

    private final int[] words =
            new int[CHUNK_VALID_SIZE_IN_BYTES / WORD_SIZE_IN_BYTES + WORD_EXTENSION_IN_BYTES];

    private int[] hash;

    public MessageChunk(byte[] data) {
        if (data.length != CHUNK_VALID_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                    "Input data size is " + data.length + ", different than " + CHUNK_VALID_SIZE_IN_BYTES);
        }

        this.data = data;
        readWords();
    }

    public int[] getWords() {
        return words;
    }

    public int[] getHash()
    {
        return hash;
    }

    private void readWords() {
        for (int i = 0; i < data.length / WORD_SIZE_IN_BYTES; ++i) {
            int startIndex = i * WORD_SIZE_IN_BYTES;
            byte[] array = Arrays.copyOfRange(data, startIndex, startIndex + WORD_SIZE_IN_BYTES);
            words[i] = java.nio.ByteBuffer.wrap(array).getInt();
        }
    }

    public void calculateHash(int[] previousChunkHash) {
        if (previousChunkHash.length != 5) {
            throw new IllegalArgumentException(
                    "Hash given had " + previousChunkHash.length + " bytes; length of 20 bytes expected");
        }

        hash = previousChunkHash;
        // TODO: implement creating SHA-1 hash
    }
}
