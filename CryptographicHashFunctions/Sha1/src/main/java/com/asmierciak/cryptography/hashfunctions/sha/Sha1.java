package com.asmierciak.cryptography.hashfunctions.sha;

import com.asmierciak.cryptography.hashfunctions.HashFunction;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static com.asmierciak.util.numbers.HexConversions.bytesToHex;

public class Sha1 implements HashFunction {
    private static final Charset utf8 = StandardCharsets.UTF_8;

    @Override
    public String hash(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        byte[] inputBytes = input.getBytes(utf8);
        Message message = new Message(inputBytes);
        message.calculateHash();
        byte[] outputBytes = message.getHashBytes();
        return bytesToHex(outputBytes);
    }
}
