package service;

import converter.TextConverter;
import exceptions.MyException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileService {

    private static final int BUFFER_SIZE = 8192;

    public void processFile(Path inputPath, Path outputPath, TextConverter textConverter) {
        if (textConverter == null) {
            throw new MyException("FileConverter is null.");
        }
        char[] buffer = new char[BUFFER_SIZE];

        try (BufferedReader reader = Files.newBufferedReader(inputPath, StandardCharsets.UTF_8);
             BufferedWriter writer = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8)) {
            int count;
            while ((count = reader.read(buffer)) != -1) {
                String partOfText = new String(buffer, 0, count);
                String converted = textConverter.convert(partOfText);
                writer.write(converted);
            }
        } catch (IOException e) {
            throw new MyException("Failed to transform file: " + inputPath + " - " + outputPath, e);
        }
    }

    public String readSample(Path inputPath, int maxChars) {
        char[] buffer = new char[maxChars];

        try (Reader reader = Files.newBufferedReader(inputPath, StandardCharsets.UTF_8)) {
            int count = reader.read(buffer);
            if (count == -1) {
                return "";
            }
            return new String(buffer, 0, count);
        } catch (IOException e) {
            throw new MyException("Failed to read sample from file: " + inputPath, e);
        }
    }
}









