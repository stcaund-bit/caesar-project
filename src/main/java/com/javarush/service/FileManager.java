package com.javarush.service;

import com.javarush.exception.CipherException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    public String readFile(String filePath) throws CipherException {
        try {
            return Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new CipherException("Ошибка чтения из файла: " + filePath, e);
        }
    }

    public void writeFile(String content, String filePath) throws CipherException {
        try {
            if (Files.exists(Path.of(filePath))) {
                throw new CipherException("Файл существует: " + filePath);
            }
            Files.writeString(Path.of(filePath), content);
        } catch (IOException e) {
            throw new CipherException("Ошибка записи в файл: " + filePath, e);
        }
    }
}
