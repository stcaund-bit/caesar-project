package com.javarush.core;

public class BruteForce {

   // private

    public String decryptByBruteForce(String encryptedText, char[] alphabet) {
        // TODO перебрать все ключи от 1 до alphabet.length - 1
        //  для каждого ключа расшифровать текст и оценить, похож ли он на русский:
        //  пробел после точки и запятой, длина слов, частые короткие слова
        //  вернуть вариант с лучшей оценкой
        Cipher cipher = new Cipher(alphabet);
        int bestDecodingCodeByRating = 0;
        int[] whitespacesAfterDot = new int[alphabet.length];
        int[] whitespacesAfterComma = new int[alphabet.length];
        int[] bruteForceRating = new int[alphabet.length];
        //int[] maxWordLength = new int[alphabet.length];
        int[] whitespaces = new int[alphabet.length];
        int alphabetCapacity = alphabet.length;
        String[] decryptedVariants = new String[alphabetCapacity];
        System.out.println("Длина алфавита: " + alphabet.length);
        System.out.println("Длина зашифрованного текста: " + encryptedText.length());

        for (int code = 1; code < alphabet.length - 1; code++) {

            decryptedVariants[code] = cipher.decrypt(encryptedText, code);
            System.out.println("Brute forced code " + code + " encoded string: " + decryptedVariants[code]);

            for (int i = 0; i < decryptedVariants[code].length()-1; i++) {
                whitespacesAfterDot[code] = 0;
                whitespacesAfterComma[code] = 0;
                //decryptedVariants[code].indexOf(i);

                if (decryptedVariants[code].charAt(i) == ',' && decryptedVariants[code].charAt(i +1) == ' ') {
                    whitespacesAfterComma[code]++;
                } else if (decryptedVariants[code].charAt(i) == '.' && decryptedVariants[code].charAt(i+1) == ' ') {
                    whitespacesAfterDot[code]++;
                } else if (decryptedVariants[code].charAt(i) == ' ') {
                    whitespaces[code]++;
                }

                //maxWordLength[i] = 0;

            }
            bruteForceRating[code] = whitespaces[code] + whitespacesAfterComma[code] + whitespacesAfterDot[code];
            System.out.println("Рейтинг варианта: " + bruteForceRating[code]);
            System.out.println("Расшифрованный вариант: " + decryptedVariants[code]);
            if (bruteForceRating[code]>bruteForceRating[bestDecodingCodeByRating]) {
                bestDecodingCodeByRating = code;
                System.out.println("Лучший код для брутфорсинга = " + code);
            }
        }

        return decryptedVariants[bestDecodingCodeByRating];

        //throw new UnsupportedOperationException("Метод decryptByBruteForce пока не написан");
    }
}
