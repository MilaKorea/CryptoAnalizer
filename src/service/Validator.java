package service;

import exceptions.MyException;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

/*
Валидация входных данных, таких как существование файла, допустимость ключа.
 */
public class Validator {

    public static void validateKey(int key, char[] alphabet) {
        int maxKey = alphabet.length - 1;
        if (key < 0 || key > maxKey) {
            throw new MyException(String.format("Key must be between 0 and %d.", maxKey));
        }
    }

    public static Path validateInputPath(String filePath) {
        if (filePath == null || filePath.isBlank()) {
            throw new MyException("Path is empty. Please enter the FILE PATH.");
        }
        Path path;
        try {
            path = Path.of(filePath.trim());
        } catch (InvalidPathException e) {
            throw new MyException("Invalid path.");
        }
        if (Files.notExists(path)) throw new MyException("File not found: " + path);
        if (Files.isRegularFile(path) == false) throw new MyException("Not a file: " + path);
        return path;
    }

    public static Path validateOutputPath(String filePath) {
        if (filePath == null || filePath.isBlank()) {
            throw new MyException("Output path is empty. Please enter OUTPUT FILE PATH.");
        }
        Path path;
        try {
            path = Path.of(filePath.trim());
        } catch (InvalidPathException e) {
            throw new MyException("Invalid output path format.");
        }
        if (Files.exists(path) && Files.isDirectory(path)) {
            throw new MyException("Output path points to a directory: " + path);
        }
        Path parent = path.getParent();
        if (parent != null && !Files.exists(parent)) {
            throw new MyException("Output directory does not exist: " + parent);
        }
        return path;
    }

    public static void validateInputAndOutput(Path input, Path output) {
        if (input.equals(output)) {
            throw new MyException("Input and output paths must be different.");
        }
    }

}

