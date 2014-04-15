package com.asmierciak.cryptography.hashing;

import java.util.Arrays;

public class MessageChunk {
    public final static int CHUNK_VALID_SIZE_IN_BYTES = 64;

    private final static int CHUNK_WORD_SIZE_IN_BYTES = 4;

    private final byte[] data;

    private final byte[][] words = new byte[CHUNK_VALID_SIZE_IN_BYTES / CHUNK_WORD_SIZE_IN_BYTES][];

    public MessageChunk(byte[] data) {
        if (data.length != CHUNK_VALID_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                    "Input data size is " + data.length + ", different than " + CHUNK_VALID_SIZE_IN_BYTES);
        }

        this.data = data;
        readWords();
    }

    public byte[][] getWords() {
        return words;
    }

    private void readWords() {
        for (int i = 0; i < data.length / CHUNK_WORD_SIZE_IN_BYTES; ++i) {
            words[i] = Arrays.copyOfRange(data, i * CHUNK_WORD_SIZE_IN_BYTES, (i + 1) * CHUNK_WORD_SIZE_IN_BYTES);
        }
    }
}
