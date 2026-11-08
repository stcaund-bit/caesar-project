package com.javarush.core;

import java.util.Map;
import java.util.TreeMap;

public class Cipher {

    private final char[] alphabet;

    public Cipher(char[] alphabet) {
        this.alphabet = alphabet;
    }

    public String encrypt(String text, int shift) {
        text = text.toLowerCase();
        String encrypted = "";
        TreeMap<Integer, Character> alphabetMap = new TreeMap<>();
        for (int i = 0; i < alphabet.length; i++) {
            alphabetMap.put(i, alphabet[i]);
        }
        for (int i = 0; i < text.length(); i++) {
            if (alphabetMap.containsValue(text.charAt(i))) {
                int symbolCode = -1;
                for (Map.Entry<Integer, Character> entry : alphabetMap.entrySet()) {
                    if (text.charAt(i) == entry.getValue()) {
                        symbolCode = entry.getKey();
                    }
                }
                encrypted = encrypted + alphabetMap.get((symbolCode + shift) % alphabetMap.size());
            }
        }
        return encrypted;
    }

    public String decrypt(String encryptedText, int shift) {
        return encrypt(encryptedText, alphabet.length - shift % alphabet.length);
    }
}
