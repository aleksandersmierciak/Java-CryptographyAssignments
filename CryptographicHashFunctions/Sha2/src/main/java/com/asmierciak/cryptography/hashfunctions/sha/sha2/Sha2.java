package com.asmierciak.cryptography.hashfunctions.sha.sha2;

import com.asmierciak.cryptography.hashfunctions.HashFunction;

public class Sha2 implements HashFunction {
    @Override
    public String hash(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        return "";
    }
}
