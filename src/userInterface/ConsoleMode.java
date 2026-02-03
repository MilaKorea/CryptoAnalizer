package userInterface;

import alphabets.Alphabet;
import exceptions.MyException;
import service.Validator;

import java.nio.file.Path;
import java.util.Scanner;

public class ConsoleMode implements UIMode {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public CryptoAnalyzerMode chooseCryptoMode() {
        while (true) {
            System.out.println("Choose crypto mode:");
            System.out.println("1 - Encryption");
            System.out.println("2 - Decryption with key");
            System.out.println("3 - Brute force");
            System.out.println("4 - Statistical decryption");
            System.out.print("Enter your choice (1-4): ");

            String input = scanner.nextLine().trim();
            try {
                return CryptoAnalyzerMode.choiceByUser(input);
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public Path inputFilePath() {
        System.out.print("Please enter the INPUT FILE PATH: ");

        while (true) {
            String filePath = scanner.nextLine();
            try {
                Path path = Validator.validateInputPath(filePath);
                System.out.println("Good job!");
                return path;
            } catch (MyException e) {
                System.out.println(e.getMessage());
                System.out.print("Please enter a correct file path: ");
            }
        }
    }

    @Override
    public Path outputFilePath(Path inputPath) {
        System.out.print("Please enter the OUTPUT FILE PATH: ");

        while (true) {
            String filePath = scanner.nextLine();
            try {
                Path outputPath = Validator.validateOutputPath(filePath);
                Validator.validateInputAndOutput(inputPath, outputPath);
                System.out.println("Good job!");
                return outputPath;
            } catch (MyException e) {
                System.out.println(e.getMessage());
                System.out.print("Please enter a correct file path: ");
            }
        }
    }


    @Override
    public int chooseKey() {
        System.out.print("Next step: enter the KEY (must be a number): ");

        while (true) {
            String number = scanner.nextLine().trim();
            try {
                int key = Integer.parseInt(number);
                Validator.validateKey(key, Alphabet.getRussianAlphabet());
                return key;
            } catch (NumberFormatException e) {
                System.out.print("Key must be a number. Please enter a correct KEY: ");
            } catch (MyException e) {
                System.out.println(e.getMessage());
                System.out.print("Please enter a correct KEY: ");
            }
        }
    }
}

