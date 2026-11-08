package com.javarush.core;

public class BruteForce {

    public String decryptByBruteForce(String encryptedText, char[] alphabet) {
        Cipher cipher = new Cipher(alphabet);
        int bestDecodingCodeByRating = 0;
        int[] whitespacesAfterDot = new int[alphabet.length];
        int[] whitespacesAfterComma = new int[alphabet.length];
        int[] bruteForceRating = new int[alphabet.length];
        int[] whitespaces = new int[alphabet.length];
        int alphabetCapacity = alphabet.length;
        String[] decryptedVariants = new String[alphabetCapacity];
        for (int code = 1; code < alphabet.length; code++) {

            decryptedVariants[code] = cipher.decrypt(encryptedText, code);
            for (int i = 0; i < decryptedVariants[code].length() - 1; i++) {
                if (decryptedVariants[code].charAt(i) == ',' && decryptedVariants[code].charAt(i + 1) == ' ') {
                    whitespacesAfterComma[code]++;
                } else if (decryptedVariants[code].charAt(i) == '.' && decryptedVariants[code].charAt(i + 1) == ' ') {
                    whitespacesAfterDot[code]++;
                } else if (decryptedVariants[code].charAt(i) == ' ') {
                    whitespaces[code]++;
                }
            }
            bruteForceRating[code] = whitespaces[code] + whitespacesAfterComma[code] + whitespacesAfterDot[code];
            if (bruteForceRating[code] > bruteForceRating[bestDecodingCodeByRating]) {
                bestDecodingCodeByRating = code;
            }
        }
        return decryptedVariants[bestDecodingCodeByRating];
    }
}
