package com.javarush.core;

import java.util.Arrays;

public class BruteForce {
    private static final int MAX_WORD_LENGTH = 40;

    public String decryptByBruteForce(String encryptedText, char[] alphabet) {
        Cipher cipher = new Cipher(alphabet);
        int bestDecodingCodeByRating = 0;
        int[] whitespacesAfterDot = new int[alphabet.length];
        int[] whitespacesAfterComma = new int[alphabet.length];
        int[] bruteForceRating = new int[alphabet.length];
        int[] whitespaces = new int[alphabet.length];
        boolean[] hasVeryLongWord = new boolean[alphabet.length];
        int[] wordsCount = new int[alphabet.length];
        int alphabetCapacity = alphabet.length;
        String[] decryptedVariants = new String[alphabetCapacity];
        for (int code = 1; code < alphabet.length; code++) {
            decryptedVariants[code] = cipher.decrypt(encryptedText, code);
            String[] words = decryptedVariants[code].split(" ");
            wordsCount[code] = words.length;
            for (String wordsHere : words) {
                if (wordsHere.length() > MAX_WORD_LENGTH) {
                    hasVeryLongWord[code] = true;
                }
            }
            for (int i = 0; i < decryptedVariants[code].length() - 1; i++) {
                if (decryptedVariants[code].charAt(i) == ',' && decryptedVariants[code].charAt(i + 1) == ' ') {
                    whitespacesAfterComma[code]++;
                } else if (decryptedVariants[code].charAt(i) == '.' && decryptedVariants[code].charAt(i + 1) == ' ') {
                    whitespacesAfterDot[code]++;
                } else if (decryptedVariants[code].charAt(i) == ' ') {
                    whitespaces[code]++;
                }
            }
            int notAWord = hasVeryLongWord[code] ? 0 : 1;
            bruteForceRating[code] = notAWord * (whitespaces[code] + whitespacesAfterComma[code] + whitespacesAfterDot[code] + wordsCount[code]);
            if (bruteForceRating[code] > bruteForceRating[bestDecodingCodeByRating]) {
                bestDecodingCodeByRating = code;
            }
        }
        return decryptedVariants[bestDecodingCodeByRating];
    }
}
