package com.example.okvedphonematcher.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringFilterUtils {

    public static String filterDigits(String rawCode) {
        StringBuilder digits = new StringBuilder();
        for (char c : rawCode.toCharArray()) {
            if (Character.isDigit(c)) {
                digits.append(c);
            }
        }
        return digits.toString();
    }
}
