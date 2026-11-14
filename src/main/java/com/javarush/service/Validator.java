package com.javarush.service;

import java.nio.file.Files;
import java.nio.file.Path;

public class Validator {

    public boolean isValidKey(int key, char[] alphabet) {
        return (key % alphabet.length >= 0 && key % alphabet.length <= alphabet.length - 1);
    }

    public boolean isFileExists(String filePath) {
        return (Files.exists(Path.of(filePath)) && !Files.isDirectory(Path.of(filePath)));
    }

    public boolean isFileNotExists(String filePath) {
        return (!Files.exists(Path.of(filePath)) && !Files.isDirectory(Path.of(filePath)));
    }

}
