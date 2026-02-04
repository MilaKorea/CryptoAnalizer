package userInterface;

import alphabets.Alphabet;
import exceptions.MyException;
import service.Validator;

import java.nio.file.Path;

public abstract class AbstractUIMode implements UIMode {

    protected abstract String ask(String message);
    protected abstract void showError(String message);

    @Override
    public CryptoAnalyzerMode chooseCryptoMode() {
        while (true) {
            String input = ask(
                    "Choose crypto mode:\n" +
                            "1 - Encryption\n" +
                            "2 - Decryption with key\n" +
                            "3 - Brute force\n" +
                            "4 - Statistical decryption\n" +
                            "Enter your choice (1-4):"

            );

            try {
                return CryptoAnalyzerMode.choiceByUser(input.trim());
            } catch (MyException e) {
                showError(e.getMessage());
            }
        }
    }

    @Override
    public Path inputFilePath() {
        while (true) {
            String filePath = ask("Please enter the INPUT FILE PATH:");
            try {
                return Validator.validateInputPath(filePath);
            } catch (MyException e) {
                showError(e.getMessage());
            }
        }
    }

    @Override
    public Path outputFilePath(Path inputFilePath) {
        while (true) {
            String filePath = ask("Please enter the OUTPUT FILE PATH:");
            try {
                Path outputPath = Validator.validateOutputPath(filePath);
                Validator.validateInputAndOutput(inputFilePath, outputPath);
                return outputPath;
            } catch (MyException e) {
                showError(e.getMessage());
            }
        }
    }

    @Override
    public int chooseKey() {
        while (true) {
            String keyStr = ask("Enter the KEY (must be a number):");
            try {
                int key = Integer.parseInt(keyStr.trim());
                Validator.validateKey(key, Alphabet.getRussianAlphabet());
                return key;
            } catch (NumberFormatException e) {
                showError("Key must be a number.");
            } catch (MyException e) {
                showError(e.getMessage());
            }
        }
    }
}
