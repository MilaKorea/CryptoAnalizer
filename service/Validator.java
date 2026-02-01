package com.javarush.task.jdk13.task53.task5307.service;

import com.javarush.task.jdk13.task53.task5307.exceptions.MyExeption;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

/*
Валидация входных данных, таких как существование файла, допустимость ключа.
 */
public class Validator {

    public static void validateKey(int key, char[] alphabet) {

        int maxKey = alphabet.length-1;

        if (key<0 || key> maxKey){
            throw new MyExeption(String.format("Key must be between 0 and %d.", maxKey));
        }
    }


    public  static Path validationPath(String filePath) {
        if (filePath == null || filePath.isBlank()) {
            throw new MyExeption("Path is empty. Please enter the FILE PATH.");
        }

        Path path;

        try {
            path = Path.of(filePath.trim());
        } catch (InvalidPathException e) {
            throw new MyExeption("Invalid path.");
        }
        if (!Files.exists(path)) throw new MyExeption("File not found: " + path);

        if (!Files.isRegularFile(path)) throw new MyExeption("Not a file: " + path);

        return path;
    }
}

