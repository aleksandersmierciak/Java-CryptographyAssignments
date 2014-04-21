package com.asmierciak.cryptography.hashing;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static com.asmierciak.util.numbers.HexConversions.bytesToHex;

public class Sha1 {
    private static final Charset utf8 = StandardCharsets.UTF_8;

    public static String hash(String input) {
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
