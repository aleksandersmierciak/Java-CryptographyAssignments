package com.asmierciak.cryptography.hashfunctions.sha.sha2;

import com.asmierciak.cryptography.hashfunctions.sha.ShaMessageChunk;

import java.util.Arrays;

public class Sha2MessageChunk implements ShaMessageChunk {
    // Values according to SHA-2 paper
    public final static int[] INITIAL_HASH = new int[]{0x6a09e667, 0xbb67ae85, 0x3c6ef372, 0xa54ff53a, 0x510e527f, 0x9b05688c, 0x1f83d9ab, 0x5be0cd19};

    public final static int CHUNK_VALID_SIZE_IN_BYTES = 64;

    private final static int WORD_SIZE_IN_BYTES = 4;

    private final static int WORD_EXTENSION_IN_BYTES = 48;

    private final byte[] data;

    private final int[] words =
            new int[CHUNK_VALID_SIZE_IN_BYTES / WORD_SIZE_IN_BYTES + WORD_EXTENSION_IN_BYTES];

    public Sha2MessageChunk(byte[] data) {
        if (data.length != CHUNK_VALID_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                    "Input data size is " + data.length + ", different than " + CHUNK_VALID_SIZE_IN_BYTES);
        }

        this.data = data;
        initializeWords();
    }

    public int[] getWords() {
        return words;
    }

    @Override
    public int[] getHash() {
        return new int[8];
    }

    private void initializeWords() {
        readWords();
        extendWords();
    }

    private void readWords() {
        for (int i = 0; i < data.length / WORD_SIZE_IN_BYTES; ++i) {
            int startIndex = i * WORD_SIZE_IN_BYTES;
            byte[] array = Arrays.copyOfRange(data, startIndex, startIndex + WORD_SIZE_IN_BYTES);
            words[i] = java.nio.ByteBuffer.wrap(array).getInt();
        }
    }

    private void extendWords() {
        for (int i = 16; i < words.length; ++i) {
            int xorProduct = words[i-3] ^ words[i-8] ^ words[i-14] ^ words[i-16];
            words[i] = Integer.rotateLeft(xorProduct, 1);
        }
    }

    @Override
    public void calculateHash(int[] previousChunkHash) {
        if (previousChunkHash.length != 8) {
            throw new IllegalArgumentException(
                    "Hash given had " + previousChunkHash.length + " bytes; length of 32 bytes expected");
        }
    }
}
