package com.javarush.task.jdk13.task53.task5307.service;

import com.javarush.task.jdk13.task53.task5307.exceptions.MyException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {

    public String readFile(Path path ) {
        try {
            return Files.readString(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new MyException("Failed to read file: " + path, e);
        }
    }

    public void writeFile(String content, String filePath) {
        Path path = Path.of(filePath.trim());
        try {
            Files.writeString(path, content, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new MyException("Failed to write file: " + path, e);
        }
    }
}







