package com.asmierciak.util.text;

public class TextSplitter {

    public String[] split(String input, int limit) {
        int size = (input.length() + limit - 1) / limit;
        String[] output = new String[size];
        for (int i = 0; i < size; ++i) {
            int end = Math.min((i + 1) * limit, input.length());
            output[i] = input.substring(i * limit, end);
        }
        return output;
    }
}
