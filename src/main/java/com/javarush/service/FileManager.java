package com.javarush.service;

import com.javarush.exception.CipherException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    public String readFile(String filePath) throws CipherException {
        //String content = "";
        try {

            //Path path = Paths.get(filePath);
            return Files.readString(Paths.get(filePath), StandardCharsets.UTF_8).toLowerCase();
        } catch (IOException e) {
            throw new CipherException("Ошибка чтения из файла: " + filePath, e);
        }
        //return content;
    }

    public void writeFile(String content, String filePath) throws CipherException {
        //  записать текст в файл, исходный файл перезаписывать нельзя
        //throw new UnsupportedOperationException("Метод writeFile пока не написан");
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
