package com.asmierciak.util.numbers;

import java.nio.ByteBuffer;

// http://stackoverflow.com/questions/4485128/how-do-i-convert-long-to-byte-and-back-in-java
public class LongConversions {
    private static ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);

    public static byte[] longToBytes(long x) {
        buffer.clear();
        buffer.putLong(0, x);
        return buffer.array();
    }

    public static long bytesToLong(byte[] bytes) {
        buffer.clear();
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();
        return buffer.getLong();
    }
}
