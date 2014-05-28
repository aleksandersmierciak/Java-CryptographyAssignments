package com.asmierciak.cryptography.hashfunctions.sha.sha1;

import com.asmierciak.cryptography.hashfunctions.sha.ShaMessageChunk;

import java.util.Arrays;

public class Sha1MessageChunk implements ShaMessageChunk {
    public final static int CHUNK_VALID_SIZE_IN_BYTES = 64;

    private final static int WORD_SIZE_IN_BYTES = 4;

    private final static int WORD_EXTENSION_IN_BYTES = 64;

    private final byte[] data;

    private final int[] words =
            new int[CHUNK_VALID_SIZE_IN_BYTES / WORD_SIZE_IN_BYTES + WORD_EXTENSION_IN_BYTES];

    private int[] hash;

    public Sha1MessageChunk(byte[] data) {
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
    public int[] getHash()
    {
        return hash;
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
        if (previousChunkHash.length != 5) {
            throw new IllegalArgumentException(
                    "Hash given had " + previousChunkHash.length + " bytes; length of 20 bytes expected");
        }

        hash = previousChunkHash;
        int a = hash[0];
        int b = hash[1];
        int c = hash[2];
        int d = hash[3];
        int e = hash[4];

        int f = 0, k = 0;

        for (int i = 0; i < 80; ++i) {
            if (i <= 19) {
                f = (b & c) | ((~b) & d);
                k = 0x5A827999;
            } else if (i <= 39) {
                f = b ^ c ^ d;
                k = 0x6ED9EBA1;
            } else if (i <= 59) {
                f = (b & c) | (b & d) | (c & d);
                k = 0x8F1BBCDC;
            } else if (i <= 79) {
                f = b ^ c ^ d;
                k = 0xCA62C1D6;
            }

            int temp = Integer.rotateLeft(a, 5) + f + e + k + words[i];
            e = d;
            d = c;
            c = Integer.rotateLeft(b, 30);
            b = a;
            a = temp;
        }

        hash[0] = a;
        hash[1] = b;
        hash[2] = c;
        hash[3] = d;
        hash[4] = e;
    }
}
