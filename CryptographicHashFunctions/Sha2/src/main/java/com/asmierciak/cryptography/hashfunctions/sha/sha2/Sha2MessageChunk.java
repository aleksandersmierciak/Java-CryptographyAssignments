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

    private int[] hash;

    private final int[] constants = new int[] {
        0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
        0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,
        0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
        0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,
        0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,
        0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
        0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,
        0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208, 0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2
    };

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
            int s0 = Integer.rotateRight(words[i - 15], 7) ^ Integer.rotateRight(words[i - 15], 18) ^ (words[i - 15] >>> 3);
            int s1 = Integer.rotateRight(words[i - 2], 17) ^ Integer.rotateRight(words[i - 2], 19) ^ (words[i - 2] >>> 10);
            words[i] = words[i - 16] + s0 + words[i - 7] + s1;
        }
    }

    @Override
    public void calculateHash(int[] previousChunkHash) {
        if (previousChunkHash.length != 8) {
            throw new IllegalArgumentException(
                    "Hash given had " + previousChunkHash.length + " bytes; length of 32 bytes expected");
        }

        hash = previousChunkHash;

        int a = hash[0];
        int b = hash[1];
        int c = hash[2];
        int d = hash[3];
        int e = hash[4];
        int f = hash[5];
        int g = hash[6];
        int h = hash[7];

        for (int i = 0; i < words.length; ++i) {
            int s1 = Integer.rotateRight(e, 6) ^ Integer.rotateRight(e, 11) ^ Integer.rotateRight(e, 25);
            int ch = (e & f) ^ ((~e) & g);
            int temp1 = h + s1 + ch + constants[i] + words[i];
            int s0 = Integer.rotateRight(a, 2) ^ Integer.rotateRight(a, 13) ^ Integer.rotateRight(a, 22);
            int maj = (a & b) ^ (a & c) ^ (b & c);
            int temp2 = s0 + maj;

            h = g;
            g = f;
            f = e;
            e = d + temp1;
            d = c;
            c = b;
            b = a;
            a = temp1 + temp2;
        }

        hash[0] = a;
        hash[1] = b;
        hash[2] = c;
        hash[3] = d;
        hash[4] = e;
        hash[5] = f;
        hash[6] = g;
        hash[7] = h;
    }
}
