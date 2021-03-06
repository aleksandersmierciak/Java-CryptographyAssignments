package com.asmierciak.cryptography.hashfunctions.sha.sha1;

import com.asmierciak.cryptography.hashfunctions.HashFunction;
import com.asmierciak.cryptography.hashfunctions.sha.ShaMessage;

import static com.asmierciak.util.numbers.HexConversions.bytesToHex;

public class Sha1 implements HashFunction {
    @Override
    public String hash(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        byte[] inputBytes = input.getBytes(utf8);
        ShaMessage message = new Sha1Message(inputBytes);
        message.calculateHash();
        byte[] outputBytes = message.getHashBytes();
        return bytesToHex(outputBytes);
    }
}
