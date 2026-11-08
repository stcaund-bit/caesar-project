package com.javarush.core;

import java.util.Map;
import java.util.TreeMap;

public class Cipher {

    private final char[] alphabet;

    public Cipher(char[] alphabet) {
        this.alphabet = alphabet;
    }

    public String encrypt(String text, int shift) {
        //  сдвинуть каждый символ алфавита на shift позиций вперед по кругу
        //  символ, которого нет в алфавите, пропускаем
        //  позицию символа ищем быстрее, чем перебором всего массива
        //
        // throw new UnsupportedOperationException("Метод encrypt пока не написан");
        String encrypted = "";
        //System.out.println("Вошли в метод кодирования");
        TreeMap<Integer, Character> alphabetMap = new TreeMap<>();
        //StringBuilder builder = new StringBuilder();
        //System.out.println(alphabetMap);

        //инициализируем алфавит
        for (int i = 0; i < alphabet.length; i++) {
            //            System.out.println("алфавит: " + alphabet[i]);
            alphabetMap.put(i, alphabet[i]);
        }
        //System.out.println(alphabetMap);

        //делаем циклс кодировкой
        for (int i = 0; i < text.length(); i++) {
            if (alphabetMap.containsValue(text.charAt(i))) {
                int symbolCode = -1;
                // System.out.println("Кодируем: " + text.charAt(i));
                //symbolCode = alphabetMap.get;

                // ищем код символа в алфавите
                for (Map.Entry<Integer, Character> entry : alphabetMap.entrySet()) {
  //                  System.out.println("Char at " + i + " "+ text.charAt(i));
   //                 System.out.println("entry value " + entry.getValue());
                    if (text.charAt(i) == entry.getValue()) {
                        symbolCode = entry.getKey();
                    }
                }
                //System.out.println("Код символа: " + symbolCode);
                encrypted = encrypted + alphabetMap.get((symbolCode + shift) % alphabetMap.size());
                //System.out.println("инкриптед " + encrypted);
            }
        }
        return encrypted;
    }

    public String decrypt(String encryptedText, int shift) {
        // сдвинуть каждый символ на shift позиций назад по кругу
        //  символ, которого нет в алфавите, - ошибка: бросаем CipherException
        // throw new UnsupportedOperationException("Метод decrypt пока не написан");
        String decrypted = "";
        //System.out.println("Вошли в метод декодирования");
        TreeMap<Integer, Character> alphabetMap = new TreeMap<>();
        //StringBuilder builder = new StringBuilder();
  //      System.out.println(alphabetMap);

        for (int i = 0; i < alphabet.length; i++) {
  //          System.out.println("алфавит: " + alphabet[i]);
            alphabetMap.put(i, alphabet[i]);
        }
        //System.out.println(alphabetMap);


        // расшифровка
        for (int i = 0; i < encryptedText.length(); i++) {
            if (alphabetMap.containsValue(encryptedText.charAt(i))) {
                    int symbolCode = -1;
  //                  System.out.println("Декодируем: " + encryptedText.charAt(i));
                    // ищем код символа в алфавите
                    for (Map.Entry<Integer, Character> entry : alphabetMap.entrySet()) {
                        if (encryptedText.charAt(i) == entry.getValue()) {
                            symbolCode = entry.getKey();
                        }
                    }
   //             System.out.println("Декодируем: " + encryptedText.charAt(i));
   //             System.out.println("Тестируем: " + (symbolCode - shift + alphabetMap.size()) % alphabetMap.size());
                decrypted = decrypted + alphabetMap.get((symbolCode - shift + alphabetMap.size()) % alphabetMap.size());
    //           System.out.println("декриптед " + decrypted);
            }
        }
        return decrypted;
    }
}
