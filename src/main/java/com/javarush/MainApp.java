package com.javarush;

import com.javarush.core.BruteForce;
import com.javarush.core.Cipher;
import com.javarush.exception.CipherException;
import com.javarush.service.FileManager;
import com.javarush.service.Validator;


import java.util.Scanner;

public class MainApp {

    private static final char[] ALPHABET = {
            'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п',
            'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я',
            '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    private final Scanner scanner = new Scanner(System.in);
    private final FileManager fileManager = new FileManager();
    private final Validator validator = new Validator();
    private final Cipher cipher = new Cipher(ALPHABET);
    private final BruteForce bruteForce = new BruteForce();

    public static void main(String[] args) {
        new MainApp().run();
    }

    private void run() {

        System.out.println("Шифр Цезаря: шифрование и расшифровка файлов");

        while (true) {
            printMenu();
            String choice = ask("Выберите действие: ");
            try {
                switch (choice) {
                    case "1":
                        encryptFile();
                        break;
                    case "2":
                        decryptFile();
                        break;
                    case "3":
                        bruteForceFile();
                        break;
                    case "0":
                    case "exit":
                        System.out.println("До свидания!");
                        return;
                    default:
                        System.out.println("Нет такого пункта: " + choice);
                }
            } catch (CipherException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1. Зашифровать файл");
        System.out.println("2. Расшифровать файл с ключом");
        System.out.println("3. Взломать перебором ключей");
        System.out.println("0. Выход (или exit)");
    }

    private void encryptFile() throws CipherException {
        String fileFrom = ask("Введите путь к файлу, который следует зашифровать: ");
        if (!validator.isFileExists(fileFrom)) {
            throw new CipherException("Файл для считывания " + fileFrom + " не существует!");
        }
        String fileTo = ask("Введите путь к файлу, в который будет записан зашифрованный текст: ");
        if (!validator.isFileNotExists(fileTo)) {
            throw new CipherException("Файл для записи " + fileTo + " уже существует! Введите другой файл для записи");
        }

        int cipherCode;
        try {
            cipherCode = Integer.parseInt(ask("Введите ключ: "));
            if (!validator.isValidKey(cipherCode, ALPHABET)) {
                throw new CipherException("Введенный ключ за границами допустимого диапазона: " + cipherCode);
            }
        } catch (NumberFormatException e) {
            throw new CipherException("Введен некорректный ключ!", e);
        }
        String content = fileManager.readFile(fileFrom);
        System.out.println("Прочитан файл: " + fileFrom);
        String encoded = cipher.encrypt(content, cipherCode);
        fileManager.writeFile(encoded, fileTo);
        System.out.println("Записан файл: " + fileTo);
    }

    private void decryptFile() throws CipherException {
        String fileFrom = ask("Введите путь к файлу, который следует расшифровать: ");
        if (!validator.isFileExists(fileFrom)) {
            throw new CipherException("Файл для считывания с зашифрованным текстом " + fileFrom + " не существует!");
        }
        String fileTo = ask("Введите путь к файлу, в который будет записан расшифрованный текст: ");
        if (!validator.isFileNotExists(fileTo)) {
            throw new CipherException("Файл для записи " + fileFrom + " уже существует! Введите другой файл для записи");
        }
        int cipherCode;
        try {
            cipherCode = Integer.parseInt(ask("Введите ключ: "));
            if (!validator.isValidKey(cipherCode, ALPHABET)) {
                throw new CipherException("Введенный ключ за границами допустимого диапазона: " + cipherCode);
            }
        } catch (NumberFormatException e) {
            //System.out.println("Введен некорректный ключ!");
            throw new CipherException("Введен некорректный ключ!", e);
        }
        String content = fileManager.readFile(fileFrom);
        System.out.println("Прочитан файл: " + fileFrom);
        String decoded = cipher.decrypt(content, cipherCode);
        fileManager.writeFile(decoded, fileTo);
        System.out.println("Записан файл: " + fileTo);
    }

    private void bruteForceFile() throws CipherException {
        String fileFrom = ask("Введите путь к файлу, который следует расшифровать методом перебора ключей: ");
        if (!validator.isFileExists(fileFrom)) {
            throw new CipherException("Файл для считывания " + fileFrom + " не существует!");
        }
        String fileTo = ask("Введите путь к файлу, в который будет записан расшифрованный текст: ");
        if (!validator.isFileNotExists(fileTo)) {
            throw new CipherException("Файл для записи " + fileTo + " уже существует! Введите другой файл для записи");
        }
        String content = fileManager.readFile(fileFrom);
        System.out.println("Прочитан файл: " + fileFrom);
        String decodedByBruteForce = bruteForce.decryptByBruteForce(content, ALPHABET);
        fileManager.writeFile(decodedByBruteForce, fileTo);
        System.out.println("Записан файл: " + fileTo);
    }

    private String ask(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
