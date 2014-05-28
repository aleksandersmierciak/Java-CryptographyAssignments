package com.asmierciak.cryptography.hashfunctions;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public interface HashFunction {
    static final Charset utf8 = StandardCharsets.UTF_8;
    String hash(String input);
}
