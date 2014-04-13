package com.asmierciak.util.text;

public class TextMerger {
    public String merge(String[] input) {
        StringBuilder builder = new StringBuilder();
        for (String fragment : input) {
            builder.append(fragment);
        }
        return builder.toString();
    }
}
