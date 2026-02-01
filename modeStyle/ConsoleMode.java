package com.javarush.task.jdk13.task53.task5307.modeStyle;

import com.javarush.task.jdk13.task53.task5307.alphabets.Alphabet;
import com.javarush.task.jdk13.task53.task5307.exceptions.MyExeption;
import com.javarush.task.jdk13.task53.task5307.service.Validator;

import java.nio.file.Path;
import java.util.Scanner;

public class ConsoleMode {
    Scanner scanner = new Scanner(System.in);
    Path filePath;
    int key;

    public void start() {

        System.out.println("Console mode selected.");

        System.out.print("Please enter the FILE PATH : ");
        filePath = getFilePath();
        System.out.println("Good job!");


        System.out.print(" Next step : enter  the KEY ( must be a number): ");
        key = getKey();
        System.out.println("");

        // TODO: вызови свой шифр
        // String result = Cipher.encrypt(text, key);
        //String result = text; // временно

        //System.out.println("Result: " + result);
    }

    private Path getFilePath() {
        Path path;
        while (true) {
            String filePath = scanner.nextLine();
            try {
                path = Validator.validationPath(filePath);
                break;
            } catch (MyExeption e) {
                System.out.println(e.getMessage());
                System.out.println("Please enter a correct file path: ");
            }
        }
        return path;
    }

    private int getKey() {
        int key;
        while (true) {
            String number = scanner.nextLine().trim();

            try {
                key = Integer.parseInt(number);
                Validator.validateKey(key, Alphabet.getRussianAlphabet());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Key must be a number. Please enter a correct KEY: ");
            } catch (MyExeption e) {
                System.out.println(e.getMessage());
                System.out.println("Please enter a correct KEY: ");
            }
        }
        return key;
    }
}
