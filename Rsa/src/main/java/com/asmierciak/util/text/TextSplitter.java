package com.asmierciak.util.text;

public class TextSplitter {
    public String[] split(String input, int sizeLimit) {
        int partCount = (input.length() + sizeLimit - 1) / sizeLimit;
        String[] output = new String[partCount];
        for (int i = 0; i < partCount; ++i) {
            int end = Math.min((i + 1) * sizeLimit, input.length());
            output[i] = input.substring(i * sizeLimit, end);
        }
        return output;
    }
}
